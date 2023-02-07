package br.com.amorim.supermarket.controller.userdata.dto;

import br.com.amorim.supermarket.model.userdata.UserData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConverterUserDataMapperImpl implements ConverterUserDataMapper {

    private ModelMapper modelMapper;

    @Override
    public UserData createOrUpdateUserDataMapper(UserDataDTO userDataDTO) {
        return modelMapper.map(userDataDTO, UserData.class);
    }
}
