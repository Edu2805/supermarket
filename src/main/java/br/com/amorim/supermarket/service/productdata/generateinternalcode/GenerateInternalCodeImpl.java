package br.com.amorim.supermarket.service.productdata.generateinternalcode;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataReposotiry;

import java.math.BigInteger;

public class GenerateInternalCodeImpl implements GenerateInternalCode {

    private ProductDataReposotiry productDataReposotiry;

    @Override
    public BigInteger generate(ProductData productData) {
        return null;
    }
}
