package br.com.amorim.supermarket.controller.productdata.dto;

import br.com.amorim.supermarket.model.productdata.ProductData;

public interface ConvertProductMapper {

    ProductData createProductMapper(ProductDTO productDTO);
}
