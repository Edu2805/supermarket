package br.com.amorim.supermarket.repository.productdata.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.productdata.ProductData;

import java.math.BigInteger;

public interface GenerateInternalCodeProductRepositoryCustom {

    BigInteger generateInternalCode(ProductData productData);
}
