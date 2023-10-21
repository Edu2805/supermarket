package br.com.amorim.supermarket.controller.person.dto;

import br.com.amorim.supermarket.controller.person.dto.response.PersonListOutputDTO;
import br.com.amorim.supermarket.model.person.Person;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@Component
public class ConvertPersonMapperImpl implements ConvertPersonMapper {

    private ModelMapper modelMapper;

    @Override
    public List<PersonListOutputDTO> peopleAvailable(List<Person> people) {
        List<PersonListOutputDTO> personListOutputDTOList = new ArrayList<>();
        if (!people.isEmpty()) {
            people.forEach(person -> personListOutputDTOList.add(modelMapper.map(person, PersonListOutputDTO.class)));
            return personListOutputDTOList;
        }
        return List.of();
    }
}
