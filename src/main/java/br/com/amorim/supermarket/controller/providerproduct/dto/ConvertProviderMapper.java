package br.com.amorim.supermarket.controller.providerproduct.dto;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

public interface ConvertProviderMapper {

    ProviderProduct createOrUpdateProviderProductMapper(ProviderProductDTO providerProductDTO);
}
