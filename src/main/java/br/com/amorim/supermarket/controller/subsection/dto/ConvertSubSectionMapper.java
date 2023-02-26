package br.com.amorim.supermarket.controller.subsection.dto;

import br.com.amorim.supermarket.model.subsection.SubSection;

public interface ConvertSubSectionMapper {

    SubSection createSubSectionMapper(SubSectionDTO subSectionDTO);
}
