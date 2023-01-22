package br.com.amorim.supermarket.service.establishment.verifymunicipalorstateregistration;

import br.com.amorim.supermarket.model.establishment.Establishment;

public interface VerifyMunicipalOrStateRegistrationEstablishment {

    boolean verifyMunicipalOrStateRegistrationBeforeSave(Establishment establishment);
    boolean verifyMunicipalOrStateRegistrationBeforeUpdate(Establishment establishment);
}
