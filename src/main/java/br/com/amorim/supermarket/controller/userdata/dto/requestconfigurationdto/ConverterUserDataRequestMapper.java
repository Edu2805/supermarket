package br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto;

import br.com.amorim.supermarket.model.userdata.UserData;

public interface ConverterUserDataRequestMapper {

    UserData createOrUpdateUserDataMapper(UserDataDTO userDataDTO);

    UserData getByUserNameMapper(GetUserByUserNameDTO getUserByUserNameDTO);
}
