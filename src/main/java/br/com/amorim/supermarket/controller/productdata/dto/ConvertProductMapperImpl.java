package br.com.amorim.supermarket.controller.productdata.dto;

import br.com.amorim.supermarket.model.productdata.ProductData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Classe que gera o DTO de ProductData
 */

@AllArgsConstructor

@Component
public class ConvertProductMapperImpl implements ConvertProductMapper{

    private ModelMapper modelMapper;

    /**
     * Classe que irá converter o ProductData para o seu DTO para cadastro de
     * produtos
     * @param productDTO produto cadastrado
     * @return ProductData mapeado para ProductDTO
     */
    @Override
    public ProductData createProductMapper(ProductDTO productDTO) {
        return modelMapper.map(productDTO, ProductData.class);
    }
}
