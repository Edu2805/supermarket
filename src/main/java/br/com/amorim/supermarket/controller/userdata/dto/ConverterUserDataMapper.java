package br.com.amorim.supermarket.controller.userdata.dto;

import br.com.amorim.supermarket.model.userdata.UserData;

public interface ConverterUserDataMapper {

    UserData createOrUpdateUserDataMapper(UserDataDTO userDataDTO);
}
