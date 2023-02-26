package br.com.amorim.supermarket.controller.mainsection.dto;

import br.com.amorim.supermarket.model.mainsection.MainSection;

public interface ConvertMainSectionMapper {

    MainSection createMainSectionMapper(MainSectionDTO mainSectionDTO);
}
