package br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto;

import br.com.amorim.supermarket.model.userdata.UserData;

public interface ConverterUserDataResponseMapper {

    UserDataResponseDTO getUserDataMapper(UserData userData);
}
