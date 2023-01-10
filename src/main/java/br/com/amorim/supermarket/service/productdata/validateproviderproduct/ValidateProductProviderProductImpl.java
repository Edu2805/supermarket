package br.com.amorim.supermarket.service.productdata.validateproviderproduct;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class ValidateProductProviderProductImpl implements ValidateProductProviderProduct {

    private ProviderProductRepository providerProductRepository;

    @Override
    public boolean validate(ProductData productData) {
        Optional<ProviderProduct> providerProduct = providerProductRepository
                .findById(productData.getProviderProduct().getId());
        if (providerProduct.isEmpty()) {
            throw new NotFoundException(getString(MessagesKeyType.PRODUCT_DATA_PROVIDER_PRODUCT_NON_EXISTENT.message));
        }
        return false;
    }
}
