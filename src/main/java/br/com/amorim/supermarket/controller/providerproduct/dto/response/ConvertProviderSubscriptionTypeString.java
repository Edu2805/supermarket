package br.com.amorim.supermarket.controller.providerproduct.dto.response;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

public interface ConvertProviderSubscriptionTypeString {

    ProviderProductSubscriptionTypeStringDTO mapper(ProviderProduct providerProduct);
}
