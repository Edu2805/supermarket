package br.com.amorim.supermarket.service.productdata.generateinternalcode;

import br.com.amorim.supermarket.model.productdata.ProductData;

import java.math.BigInteger;

public interface GenerateInternalCode {

    BigInteger generate (ProductData productData);
}
