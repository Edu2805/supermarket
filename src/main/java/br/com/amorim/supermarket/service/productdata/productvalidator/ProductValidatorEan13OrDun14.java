package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;

public interface ProductValidatorEan13OrDun14 {

    boolean validate (ProductData productData);
}
