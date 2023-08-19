package br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto;

import br.com.amorim.supermarket.model.userdata.UserData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class ConverterUserDataResponseMapperImpl implements ConverterUserDataResponseMapper {

    @Override
    public UserDataResponseDTO getUserDataMapper(UserData userData) {
        return mapperUserDataRoleString(userData);
    }

    private UserDataResponseDTO mapperUserDataRoleString(UserData userData) {
        var userDataResponseDTO = new UserDataResponseDTO();
        if (userData != null) {
            userDataResponseDTO.setUserName(userData.getUserName());
            userDataResponseDTO.setRole(getString(userData.getRole().name()));
            userDataResponseDTO.setRegistrationDate(userData.getRegistrationDate());
            userDataResponseDTO.setEmployee(userData.getIsEmployee());
        }
        return userDataResponseDTO;
    }
}
