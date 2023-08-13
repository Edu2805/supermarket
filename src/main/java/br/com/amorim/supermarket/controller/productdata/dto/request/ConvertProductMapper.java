package br.com.amorim.supermarket.controller.productdata.dto.request;

import br.com.amorim.supermarket.model.productdata.ProductData;

public interface ConvertProductMapper {

    ProductData createOrUpdateProductMapper(ProductDTO productDTO);
}
