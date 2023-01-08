package br.com.amorim.supermarket.repository.productdata.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.productdata.ProductData;

import java.math.BigInteger;

public interface GenerateInternalCodeRepositoryCustom {

    BigInteger generateInternalCode(ProductData productData);
}
