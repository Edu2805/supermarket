package br.com.amorim.supermarket.service.person;

import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor

@Service
public class PersonServiceCrudServiceImpl implements PersonServiceCrudService {

    PersonRepository personRepository;
    private VerifyPageSize verifyPageSize;

    @Override
    public Page<Person> getAll(int page, int size) {
        if (page > 0) {
            page -= 1;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return personRepository.findAll(pageableRequest);
    }

    @Override
    public Person findById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Produto não encontrado");
                });
    }

    @Transactional
    @Override
    public Person save (Person person) {
        return personRepository.save(person);
    }

    @Transactional
    @Override
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
    @Override
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
