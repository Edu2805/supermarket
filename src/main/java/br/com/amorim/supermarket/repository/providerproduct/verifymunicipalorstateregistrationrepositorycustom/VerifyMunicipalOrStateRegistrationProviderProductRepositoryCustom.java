package br.com.amorim.supermarket.repository.providerproduct.verifymunicipalorstateregistrationrepositorycustom;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

public interface VerifyMunicipalOrStateRegistrationProviderProductRepositoryCustom {

    boolean existsByMunicipalOrStateRegistration (ProviderProduct providerProduct);
}
