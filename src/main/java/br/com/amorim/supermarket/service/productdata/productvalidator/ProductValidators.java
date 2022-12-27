package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;

public interface ProductValidators {

    boolean whenCreateWithOutProviderAndSubSection(ProductData productData);

    boolean whenCreateWithOutName (String name);
}
