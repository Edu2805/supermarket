package br.com.amorim.supermarket.service.providerproduct.generateinternalcode;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

import java.math.BigInteger;

public interface GenerateInternalCodeProviderProduct {

    BigInteger generate (ProviderProduct providerProduct);
}
