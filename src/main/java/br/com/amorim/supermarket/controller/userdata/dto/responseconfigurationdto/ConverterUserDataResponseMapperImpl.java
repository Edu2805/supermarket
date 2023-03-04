package br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto;

import br.com.amorim.supermarket.model.userdata.UserData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConverterUserDataResponseMapperImpl implements ConverterUserDataResponseMapper {

    private ModelMapper modelMapper;

    @Override
    public UserDataResponseDTO getUserDataMapper(UserData userData) {
        return modelMapper.map(userData, UserDataResponseDTO.class);
    }
}
