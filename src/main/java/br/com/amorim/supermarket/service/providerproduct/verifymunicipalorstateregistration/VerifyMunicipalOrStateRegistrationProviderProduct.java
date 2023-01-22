package br.com.amorim.supermarket.service.providerproduct.verifymunicipalorstateregistration;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

public interface VerifyMunicipalOrStateRegistrationProviderProduct {

    boolean verifyMunicipalOrStateRegistrationBeforeSave(ProviderProduct providerProduct);
    boolean verifyMunicipalOrStateRegistrationBeforeUpdate(ProviderProduct providerProduct);
}
