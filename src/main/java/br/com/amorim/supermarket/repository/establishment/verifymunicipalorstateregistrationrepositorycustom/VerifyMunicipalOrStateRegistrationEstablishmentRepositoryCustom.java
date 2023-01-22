package br.com.amorim.supermarket.repository.establishment.verifymunicipalorstateregistrationrepositorycustom;

import br.com.amorim.supermarket.model.establishment.Establishment;

public interface VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom {

    int existsByMunicipalOrStateRegistration (Establishment establishment);
}
