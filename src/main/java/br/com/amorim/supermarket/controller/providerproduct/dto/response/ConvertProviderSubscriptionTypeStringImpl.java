package br.com.amorim.supermarket.controller.providerproduct.dto.response;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class ConvertProviderSubscriptionTypeStringImpl implements ConvertProviderSubscriptionTypeString {

    @Override
    public ProviderProductSubscriptionTypeStringDTO mapper(ProviderProduct providerProduct) {
        return mapperProvider(providerProduct);
    }

    private ProviderProductSubscriptionTypeStringDTO mapperProvider(ProviderProduct providerProduct) {
        var providerProductDTO = new ProviderProductSubscriptionTypeStringDTO();
        providerProductDTO.setId(providerProduct.getId());
        providerProductDTO.setName(providerProduct.getName());
        providerProductDTO.setCode(providerProduct.getCode());
        providerProductDTO.setPhone(providerProduct.getPhone());
        providerProductDTO.setSubscriptionNumber(providerProduct.getSubscriptionNumber());
        providerProductDTO.setSubscriptionType(getString(providerProduct.getSubscriptionType().name()));
        providerProductDTO.setMunicipalRegistration(providerProduct.getMunicipalRegistration());
        providerProductDTO.setStateRegistration(providerProduct.getStateRegistration());
        providerProductDTO.setAddress(providerProduct.getAddress());
        providerProductDTO.setResponsible(providerProduct.getResponsible());
        return providerProductDTO;
    }
}
