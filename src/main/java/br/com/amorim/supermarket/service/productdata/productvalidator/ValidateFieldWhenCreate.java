package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;

public interface ValidateFieldWhenCreate {

    boolean validate(ProductData productData);
}
