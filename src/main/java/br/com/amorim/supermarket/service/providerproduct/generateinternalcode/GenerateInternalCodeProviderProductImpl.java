package br.com.amorim.supermarket.service.providerproduct.generateinternalcode;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.generateinternalcoderepositorycustom.GenerateInternalCodeProviderProductRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@AllArgsConstructor

@Component
public class GenerateInternalCodeProviderProductImpl implements GenerateInternalCodeProviderProduct {

    private GenerateInternalCodeProviderProductRepositoryCustom generateInternalCodeProviderProductRepositoryCustom;

    @Override
    public BigInteger generate(ProviderProduct providerProduct) {
        return generateInternalCodeProviderProductRepositoryCustom.generateInternalCode(providerProduct);
    }
}
