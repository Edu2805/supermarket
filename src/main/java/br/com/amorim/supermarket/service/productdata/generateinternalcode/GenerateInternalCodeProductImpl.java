package br.com.amorim.supermarket.service.productdata.generateinternalcode;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.generateinternalcoderepositorycustom.GenerateInternalCodeProductRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@AllArgsConstructor

@Component
public class GenerateInternalCodeProductImpl implements GenerateInternalCodeProduct {

    private GenerateInternalCodeProductRepositoryCustom productDataRepositoryCustom;

    @Override
    public BigInteger generate(ProductData productData) {
        return productDataRepositoryCustom.generateInternalCode(productData);
    }
}
