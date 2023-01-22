package br.com.amorim.supermarket.service.providerproduct.verifysubscriptionnumber;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.verifysubscriptionnumberrepositorycustom.VerifySubscriptionNumberProviderProductRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class VerifySubscriptionNumberProviderProductImpl implements VerifySubscriptionNumberProviderProduct {

    private VerifySubscriptionNumberProviderProductRepositoryCustom verifySubscriptionNumberCustom;

    @Override
    public boolean verifySubscriptionNumber(ProviderProduct providerProduct) {
        return verifySubscriptionNumberCustom.existsBySubscriptionNumber(providerProduct);
    }
}
