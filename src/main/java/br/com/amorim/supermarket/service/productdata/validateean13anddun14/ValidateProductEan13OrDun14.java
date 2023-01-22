package br.com.amorim.supermarket.service.productdata.validateean13anddun14;

import br.com.amorim.supermarket.model.productdata.ProductData;

public interface ValidateProductEan13OrDun14 {

    boolean validateBeforeSaveAndUpdate(ProductData productData);
    boolean validateBeforeUpdate(ProductData productData);
}
