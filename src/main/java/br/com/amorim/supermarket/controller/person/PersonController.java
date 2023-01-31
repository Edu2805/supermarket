package br.com.amorim.supermarket.controller.person;

import br.com.amorim.supermarket.controller.person.dto.ConverterPersonMapperImpl;
import br.com.amorim.supermarket.controller.person.dto.PersonDTO;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.service.person.PersonCrudServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor

@RestController
@RequestMapping("person")
public class PersonController {

    private PersonCrudServiceImpl personService;
    private ConverterPersonMapperImpl converterPersonMapper;

    @GetMapping
    public Page<Person> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                 @RequestParam(
                                         value = "size",
                                         required = false,
                                         defaultValue = "20") int size) {
        return personService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public Person getById (@PathVariable UUID id) {
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Person save (@RequestBody @Valid PersonDTO personDTO) {
        var newPerson = converterPersonMapper.createOrUpdatePersonMapper(personDTO);
        return personService.save(newPerson);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid PersonDTO personDTO, @PathVariable UUID id) {
        var newPerson = converterPersonMapper.createOrUpdatePersonMapper(personDTO);
        personService.update(newPerson, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        personService.delete(id);
    }
}
