package br.com.amorim.supermarket.service.establishment.verifycnpjestablishment;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.repository.establishment.verifycnpjrepositorycustom.VerifyCnpjEstablishmentRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyCnpjEstablishmentImpl implements VerifyCnpjEstablishment{

    private VerifyCnpjEstablishmentRepositoryCustom verifyCnpjEstablishmentRepositoryCustom;
    private EstablishmentRepository establishmentRepository;

    @Override
    public boolean verifyCnpjEstablishmentBeforeSave(Establishment establishment) {
        if (verifyCnpjEstablishmentRepositoryCustom.existsByCnpj(establishment)) {
            throw new BusinessRuleException(getString(
                    MessagesKeyType.ESTABLISHMENT_CNPJ_ALREADY_EXISTS.message));
        }
        return false;
    }

    @Override
    public boolean verifyCnpjEstablishmentBeforeUpdate(Establishment establishment) {
        var getProducts = establishmentRepository.findAll();
        getProducts.forEach(establishmentExistent -> {
            if (establishmentExistent.getCnpj() != null && (
                    establishmentExistent.getCnpj().equals(establishment.getCnpj()))) {
                if (!establishmentExistent.getId().equals(establishment.getId())) {
                    throw new BusinessRuleException(getString(
                            MessagesKeyType.ESTABLISHMENT_CNPJ_ALREADY_EXISTS.message));
                }
            }
        });
        return false;
    }
}
