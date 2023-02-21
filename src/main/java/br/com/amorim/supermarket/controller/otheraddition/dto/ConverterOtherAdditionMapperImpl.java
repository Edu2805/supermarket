package br.com.amorim.supermarket.controller.otheraddition.dto;

import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConverterOtherAdditionMapperImpl implements ConverterOtherAdditionMapper {

    private ModelMapper modelMapper;

    @Override
    public OtherAddition createOrUpdateOtherAdditionMapper(OtherAdditionDTO otherAdditionDTO) {
        return modelMapper.map(otherAdditionDTO, OtherAddition.class);
    }
}
