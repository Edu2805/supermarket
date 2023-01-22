package br.com.amorim.supermarket.repository.productdata.verifyean13ordun14repositorycustom;

import br.com.amorim.supermarket.model.productdata.ProductData;

public interface VerifyEan13OrDun14RepositoryCustom {

    int existsByEan13OrDun14 (ProductData productData);
}
