package br.com.amorim.supermarket.repository.providerproduct.verifymunicipalorstateregistrationrepositorycustom;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

public interface VerifyMunicipalOrStateRegistrationCustom {

    boolean existsByMunicipalOrStateRegistration (ProviderProduct providerProduct);
}
