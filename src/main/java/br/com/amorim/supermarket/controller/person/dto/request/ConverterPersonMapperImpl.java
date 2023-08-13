package br.com.amorim.supermarket.controller.person.dto.request;

import br.com.amorim.supermarket.model.person.Person;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConverterPersonMapperImpl implements ConverterPersonMapper {

    private ModelMapper modelMapper;

    @Override
    public Person createOrUpdatePersonMapper(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
