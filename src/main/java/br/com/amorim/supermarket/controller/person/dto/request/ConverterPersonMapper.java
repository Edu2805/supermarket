package br.com.amorim.supermarket.controller.person.dto.request;

import br.com.amorim.supermarket.model.person.Person;

public interface ConverterPersonMapper {

    Person createOrUpdatePersonMapper(PersonDTO personDTO);
}
