package br.com.amorim.supermarket.controller.productdata.dto;

import br.com.amorim.supermarket.model.productdata.ProductData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertProductMapperImpl implements ConvertProductMapper {

    private ModelMapper modelMapper;

    @Override
    public ProductData createProductMapper(ProductDTO productDTO) {
        return modelMapper.map(productDTO, ProductData.class);
    }
}
