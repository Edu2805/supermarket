package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

@Component
public class WhenCreateWithOutNameImpl implements ValidateFieldWhenCreate {

    @Override
    public boolean validate(ProductData productData) {
        return productData.getName() == null;
    }
}
