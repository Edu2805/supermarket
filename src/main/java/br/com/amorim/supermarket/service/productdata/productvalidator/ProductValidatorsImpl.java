package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

@Component
public class ProductValidatorsImpl implements ProductValidators{

    @Override
    public boolean whenCreateWithOutProviderAndSubSection(ProductData productData) {
        return productData.getProviderProduct() != null && productData.getSubSection() != null;
    }

    @Override
    public boolean whenCreateWithOutName (String name) {
        return name == null;
    }
}
