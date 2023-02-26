package br.com.amorim.supermarket.controller.mainsection.dto;

import br.com.amorim.supermarket.model.mainsection.MainSection;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertMainSectionMapperImpl implements ConvertMainSectionMapper {

    private ModelMapper modelMapper;
    @Override
    public MainSection createMainSectionMapper(MainSectionDTO mainSectionDTO) {
        return modelMapper.map(mainSectionDTO, MainSection.class);
    }
}
