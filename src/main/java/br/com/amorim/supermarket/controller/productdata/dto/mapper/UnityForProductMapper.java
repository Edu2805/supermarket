package br.com.amorim.supermarket.controller.productdata.dto.mapper;

import br.com.amorim.supermarket.controller.productdata.dto.request.ProductUnityStringDTO;
import br.com.amorim.supermarket.model.productdata.ProductData;

public interface UnityForProductMapper {

    ProductData productMapper(ProductUnityStringDTO productUnityStringDTO);
}
