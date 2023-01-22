package br.com.amorim.supermarket.service.establishment.verifymunicipalorstateregistration;

import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.verifymunicipalorstateregistrationrepositorycustom.VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class VerifyMunicipalOrStateRegistrationEstablishmentImpl implements
        VerifyMunicipalOrStateRegistrationEstablishment {

    private VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom verifyMunicipalOrStateRegistrationRepositoryCustom;

    @Override
    public boolean verifyMunicipalOrStateRegistration(Establishment establishment) {
        return verifyMunicipalOrStateRegistrationRepositoryCustom
                .existsByMunicipalOrStateRegistration(establishment);
    }
}
