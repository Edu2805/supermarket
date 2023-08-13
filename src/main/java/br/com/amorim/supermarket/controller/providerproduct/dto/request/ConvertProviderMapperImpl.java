package br.com.amorim.supermarket.controller.providerproduct.dto.request;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertProviderMapperImpl implements ConvertProviderMapper {

    private ModelMapper modelMapper;

    @Override
    public ProviderProduct createOrUpdateProviderProductMapper(ProviderProductDTO providerProductDTO) {
        return modelMapper.map(providerProductDTO, ProviderProduct.class);
    }
}
