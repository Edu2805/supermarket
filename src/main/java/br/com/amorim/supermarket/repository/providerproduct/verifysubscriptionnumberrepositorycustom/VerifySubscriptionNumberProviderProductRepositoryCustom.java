package br.com.amorim.supermarket.repository.providerproduct.verifysubscriptionnumberrepositorycustom;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

public interface VerifySubscriptionNumberProviderProductRepositoryCustom {

    boolean existsBySubscriptionNumber (ProviderProduct providerProduct);
}
