package br.com.amorim.supermarket.service.establishment.validatecnpj;

import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.verifycnpjrepositorycustom.VerifyCnpjEstablishmentRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ValidateCnpjDocumentEstablishmentImpl implements ValidateCnpjDocumentEstablishment {

    private VerifyCnpjEstablishmentRepositoryCustom verifyCnpjRepositoryCustom;

    @Override
    public boolean verifySubscriptionNumber(Establishment establishment) {
        return verifyCnpjRepositoryCustom.existsByCnpj(establishment);
    }
}
