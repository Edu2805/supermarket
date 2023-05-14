package br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto;

import br.com.amorim.supermarket.model.userdata.UserData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConverterUserDataRequestMapperImpl implements ConverterUserDataRequestMapper {

    private ModelMapper modelMapper;

    @Override
    public UserData createOrUpdateUserDataMapper(UserDataDTO userDataDTO) {
        return modelMapper.map(userDataDTO, UserData.class);
    }

    @Override
    public UserData getByUserNameMapper(GetUserByUserNameDTO getUserByUserNameDTO) {
        return modelMapper.map(getUserByUserNameDTO, UserData.class);
    }
}
