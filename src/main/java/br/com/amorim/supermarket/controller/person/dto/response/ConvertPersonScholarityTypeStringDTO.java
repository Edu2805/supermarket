package br.com.amorim.supermarket.controller.person.dto.response;

import br.com.amorim.supermarket.model.person.Person;

public interface ConvertPersonScholarityTypeStringDTO {

    PersonScholarityTypeStringDTO mapper(Person person);
}
