package br.com.amorim.supermarket.controller.establishment.dto;

import br.com.amorim.supermarket.model.establishment.Establishment;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertEstablishmentMapperImpl implements ConvertEstablishmentMapper {

    private ModelMapper modelMapper;

    @Override
    public Establishment createOrUpdateEstablishmentMapper(EstablishmentDTO establishmentDTO) {
        return modelMapper.map(establishmentDTO, Establishment.class);
    }
}
