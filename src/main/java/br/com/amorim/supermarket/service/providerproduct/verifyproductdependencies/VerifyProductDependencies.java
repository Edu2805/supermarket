package br.com.amorim.supermarket.service.providerproduct.verifyproductdependencies;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

public interface VerifyProductDependencies {

    void existsByProductsInProvider(ProviderProduct providerProduct);
}
