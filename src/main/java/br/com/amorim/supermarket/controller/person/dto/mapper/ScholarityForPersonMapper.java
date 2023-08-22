package br.com.amorim.supermarket.controller.person.dto.mapper;

import br.com.amorim.supermarket.controller.person.dto.request.PersonScholarityStringDTO;
import br.com.amorim.supermarket.model.person.Person;

public interface ScholarityForPersonMapper {

    Person personMapper(PersonScholarityStringDTO personScholarityStringDTO);
}
