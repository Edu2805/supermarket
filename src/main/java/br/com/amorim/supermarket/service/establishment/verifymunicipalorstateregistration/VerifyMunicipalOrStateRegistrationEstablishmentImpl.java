package br.com.amorim.supermarket.service.establishment.verifymunicipalorstateregistration;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.repository.establishment.verifymunicipalorstateregistrationrepositorycustom.VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyMunicipalOrStateRegistrationEstablishmentImpl implements
        VerifyMunicipalOrStateRegistrationEstablishment {

    private VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom verifyMunicipalOrStateRegistrationRepositoryCustom;
    private EstablishmentRepository establishmentRepository;

    @Override
    public boolean verifyMunicipalOrStateRegistrationBeforeSave(Establishment establishment) {

        if (verifyMunicipalOrStateRegistrationRepositoryCustom
                .existsByMunicipalOrStateRegistration(establishment) == 1) {
            throw new BusinessRuleException(
                    getString(MessagesKeyType.ESTABLISHMENT_MUNICIPAL_REGISTER_ALREADY_EXISTS.message));
        } else if (verifyMunicipalOrStateRegistrationRepositoryCustom
                .existsByMunicipalOrStateRegistration(establishment) == 2) {
            throw new BusinessRuleException(
                    getString(MessagesKeyType.ESTABLISHMENT_STATE_REGISTER_ALREADY_EXISTS.message));
        }
        return false;
    }

    @Override
    public boolean verifyMunicipalOrStateRegistrationBeforeUpdate(Establishment establishment) {
        var getProducts = establishmentRepository.findAll();
        getProducts.forEach(establishmentExistent -> {
            if (establishmentExistent.getMunicipalRegistration() != null && (
                    establishmentExistent.getMunicipalRegistration().equals(establishment.getMunicipalRegistration()))) {
                if (!establishmentExistent.getId().equals(establishment.getId())) {
                    throw new BusinessRuleException(getString(
                            MessagesKeyType.ESTABLISHMENT_MUNICIPAL_REGISTER_ALREADY_EXISTS.message));
                }
            }
            if (establishmentExistent.getStateRegistration() != null && (
                    establishmentExistent.getStateRegistration().equals(establishment.getStateRegistration()))) {
                if (!establishmentExistent.getId().equals(establishment.getId())) {
                    throw new BusinessRuleException(getString(
                            MessagesKeyType.ESTABLISHMENT_STATE_REGISTER_ALREADY_EXISTS.message));
                }
            }
        });
        return false;
    }
}
