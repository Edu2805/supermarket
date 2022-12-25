package br.com.amorim.supermarket.service.person;

import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor

@Service
public class PersonService {

    PersonRepository personRepository;

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person findById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Produto não encontrado");
                });
    }

    @Transactional
    public Person save (Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public void update (Person person, UUID id) {
        personRepository.findById(id)
                .map(existingPerson -> {
                    person.setId(existingPerson.getId());
                    personRepository.save(person);
                    return existingPerson;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Produto não encontrado"));

    }

    @Transactional
    public void delete (UUID id) {
        personRepository.findById(id)
                .map(person -> {
                    personRepository.delete(person);
                    return person;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Produto não encontrado"));
    }
}
