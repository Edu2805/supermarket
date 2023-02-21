package br.com.amorim.supermarket.controller.otherdiscount.dto;

import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConverterOtherDiscountMapperImpl implements ConverterOtherDiscountMapper {

    private ModelMapper modelMapper;

    @Override
    public OtherDiscount createOrUpdateOtherDiscountMapper(OtherDiscountDTO otherDiscountDTO) {
        return modelMapper.map(otherDiscountDTO, OtherDiscount.class);
    }
}
