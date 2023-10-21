package br.com.amorim.supermarket.service.person;

import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.service.common.genericcrudservice.CrudServiceCommon;

import java.util.List;
import java.util.UUID;

public interface PersonCrudService extends CrudServiceCommon<Person, UUID> {

    List<Person> isThereAJPersonAlreadyRegisteredForTheEmployee();

}
