package br.com.amorim.supermarket.controller.person.dto;

import br.com.amorim.supermarket.controller.person.dto.response.PersonListOutputDTO;
import br.com.amorim.supermarket.model.person.Person;

import java.util.List;

public interface ConvertPersonMapper {

    List<PersonListOutputDTO> peopleAvailable(List<Person> people);
}
