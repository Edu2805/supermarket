package br.com.amorim.supermarket.controller.subsection.dto;

import br.com.amorim.supermarket.model.subsection.SubSection;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertSubSectionMapperImpl implements ConvertSubSectionMapper {

    private ModelMapper modelMapper;

    @Override
    public SubSection createSubSectionMapper(SubSectionDTO subSectionDTO) {
        return modelMapper.map(subSectionDTO, SubSection.class);
    }
}
