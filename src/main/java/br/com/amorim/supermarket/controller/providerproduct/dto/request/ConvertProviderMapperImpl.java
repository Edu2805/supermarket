package br.com.amorim.supermarket.controller.providerproduct.dto.request;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertProviderMapperImpl implements ConvertProviderMapper {

    private SubscriptionTypeMapper subscriptionTypeMapper;

    @Override
    public ProviderProduct createOrUpdateProviderProductMapper(ProviderProductDTO providerProductDTO) {
        ProviderProduct providerProduct = new ProviderProduct();
        providerProduct.setAddress(providerProductDTO.getAddress());
        providerProduct.setSubscriptionNumber(providerProductDTO.getSubscriptionNumber());
        providerProduct.setSubscriptionType(subscriptionTypeMapper.mapperSubscriptionType(providerProductDTO.getSubscriptionType()));
        providerProduct.setPhone(providerProductDTO.getPhone());
        providerProduct.setName(providerProductDTO.getName());
        providerProduct.setStateRegistration(providerProductDTO.getStateRegistration());
        providerProduct.setMunicipalRegistration(providerProductDTO.getMunicipalRegistration());
        providerProduct.setResponsible(providerProductDTO.getResponsible());
        return providerProduct;
    }
}
