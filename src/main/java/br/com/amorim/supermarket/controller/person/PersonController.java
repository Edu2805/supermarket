package br.com.amorim.supermarket.controller.person;

import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.service.person.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor

@RestController
@RequestMapping("person")
public class PersonController {

    private PersonService personService;

    @GetMapping
    public List<Person> findAll () {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public Person getById (@PathVariable UUID id) {
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Person save (@RequestBody @Valid Person person) {
        return personService.save(person);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid Person person, @PathVariable UUID id) {
        personService.update(person, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        personService.delete(id);
    }
}
