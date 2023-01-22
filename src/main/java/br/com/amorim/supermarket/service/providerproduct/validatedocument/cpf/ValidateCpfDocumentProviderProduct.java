package br.com.amorim.supermarket.service.providerproduct.validatedocument.cpf;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

public interface ValidateCpfDocumentProviderProduct {

    boolean isCpf(ProviderProduct providerProduct);
}
