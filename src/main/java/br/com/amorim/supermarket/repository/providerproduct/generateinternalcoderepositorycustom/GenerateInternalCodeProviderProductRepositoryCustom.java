package br.com.amorim.supermarket.repository.providerproduct.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

import java.math.BigInteger;

public interface GenerateInternalCodeProviderProductRepositoryCustom {

    BigInteger generateInternalCode(ProviderProduct providerProduct);
}
