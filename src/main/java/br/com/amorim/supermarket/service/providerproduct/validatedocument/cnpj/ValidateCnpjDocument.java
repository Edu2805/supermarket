package br.com.amorim.supermarket.service.providerproduct.validatedocument.cnpj;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

public interface ValidateCnpjDocument {

    boolean isCnpj(ProviderProduct providerProduct);
}
