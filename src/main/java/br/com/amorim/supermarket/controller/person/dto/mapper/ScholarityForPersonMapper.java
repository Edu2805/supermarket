package br.com.amorim.supermarket.controller.person.dto.mapper;

import br.com.amorim.supermarket.controller.person.dto.request.PersonDTO;
import br.com.amorim.supermarket.controller.person.dto.request.PersonScholarityStringDTO;

public interface ScholarityForPersonMapper {

    PersonDTO personMapper(PersonScholarityStringDTO personScholarityStringDTO);
}
