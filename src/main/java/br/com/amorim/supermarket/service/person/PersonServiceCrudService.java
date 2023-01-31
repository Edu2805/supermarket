package br.com.amorim.supermarket.service.person;

import br.com.amorim.supermarket.model.person.Person;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PersonServiceCrudService {

    Page<Person> getAll(int page, int size);
    Person findById(UUID id);
    Person save (Person person);
    void update (Person person, UUID id);
    void delete (UUID id);
}
