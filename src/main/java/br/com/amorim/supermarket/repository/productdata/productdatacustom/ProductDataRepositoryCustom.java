package br.com.amorim.supermarket.repository.productdata.productdatacustom;

import br.com.amorim.supermarket.model.productdata.ProductData;

public interface ProductDataRepositoryCustom {

    boolean existsByInternalCode (ProductData productData);
}
