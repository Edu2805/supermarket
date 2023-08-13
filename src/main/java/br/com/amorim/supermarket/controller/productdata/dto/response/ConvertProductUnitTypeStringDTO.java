package br.com.amorim.supermarket.controller.productdata.dto.response;

import br.com.amorim.supermarket.model.productdata.ProductData;

public interface ConvertProductUnitTypeStringDTO {

    ProductUnitTypeStringDTO mapper(ProductData productData);
}
