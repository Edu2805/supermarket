package br.com.amorim.supermarket.service.providerproduct.verifysubscriptionnumber;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

public interface VerifySubscriptionNumberProviderProduct {

    boolean verifySubscriptionNumberBeforeSave(ProviderProduct providerProduct);
    boolean verifySubscriptionNumberBeforeUpdate(ProviderProduct providerProduct);
}
