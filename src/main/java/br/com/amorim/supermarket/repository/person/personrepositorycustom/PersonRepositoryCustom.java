package br.com.amorim.supermarket.repository.person.personrepositorycustom;

import br.com.amorim.supermarket.model.person.Person;

import java.util.List;

public interface PersonRepositoryCustom {

    List<Person> existsJobPositionInEmployee();
}
