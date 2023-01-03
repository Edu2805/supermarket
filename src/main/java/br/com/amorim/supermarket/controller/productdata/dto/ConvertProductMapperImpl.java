package br.com.amorim.supermarket.controller.productdata.dto;

import br.com.amorim.supermarket.model.productdata.ProductData;

/**
 * Interface que irá fazer o mapper de ProductData para seu DTO
 */

public interface ConvertProductMapperImpl {

    /**
     * Assinutura para o método que irá fazer o mapper de ProductData para seu DTO
     * @param productDTO produto cadastrado
     * @return DTO do tipo ProductData
     */
    ProductData createProductMapper(ProductDTO productDTO);
}
