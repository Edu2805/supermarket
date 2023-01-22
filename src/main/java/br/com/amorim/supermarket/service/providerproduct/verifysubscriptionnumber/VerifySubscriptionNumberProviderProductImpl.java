package br.com.amorim.supermarket.service.providerproduct.verifysubscriptionnumber;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.repository.providerproduct.verifysubscriptionnumberrepositorycustom.VerifySubscriptionNumberProviderProductRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifySubscriptionNumberProviderProductImpl implements VerifySubscriptionNumberProviderProduct {

    private VerifySubscriptionNumberProviderProductRepositoryCustom verifySubscriptionNumberCustom;
    private ProviderProductRepository providerProductRepository;

    @Override
    public boolean verifySubscriptionNumberBeforeSave(ProviderProduct providerProduct) {
        return verifySubscriptionNumberCustom.existsBySubscriptionNumber(providerProduct);
    }

    @Override
    public boolean verifySubscriptionNumberBeforeUpdate(ProviderProduct providerProduct) {
        var getProducts = providerProductRepository.findAll();
        getProducts.forEach(provider -> {
            if (provider.getSubscriptionNumber() != null && (
                    provider.getSubscriptionNumber().equals(providerProduct.getSubscriptionNumber()))) {
                if (!provider.getId().equals(providerProduct.getId())) {
                    throw new BusinessRuleException(getString(
                            MessagesKeyType.PROVIDER_PRODUCT_SUBSCRIPTION_NUMBER_ALREADY_EXISTS_WHEN_UPDATE.message));
                }
            }
        });
        return false;
    }

}
