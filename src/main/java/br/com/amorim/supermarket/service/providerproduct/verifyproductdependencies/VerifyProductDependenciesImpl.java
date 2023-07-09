package br.com.amorim.supermarket.service.providerproduct.verifyproductdependencies;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyProductDependenciesImpl implements VerifyProductDependencies {

    private ProductDataRepository productDataRepository;

    @Override
    public void existsByProductsInProvider(ProviderProduct providerProduct) {
        var existsByProviderProduct = productDataRepository.existsByProviderProduct(providerProduct);
        if (existsByProviderProduct) {
            throw new NotFoundException(getString(
                    MessagesKeyType.ALREADY_EXISTS_PRODUCTS_FOR_THE_PROVIDER.message));
        }
    }
}
