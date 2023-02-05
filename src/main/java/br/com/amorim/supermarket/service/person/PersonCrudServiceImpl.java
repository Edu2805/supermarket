package br.com.amorim.supermarket.service.person;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.service.person.verifycpf.VerifyPersonCpf;
import br.com.amorim.supermarket.service.person.verifyrg.VerifyPersonRg;
import br.com.amorim.supermarket.service.person.verifyuserdata.VerifyPersonUserData;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class PersonCrudServiceImpl implements PersonCrudService {

    private PersonRepository personRepository;
    private VerifyPageSize verifyPageSize;
    private VerifyPersonCpf verifyPersonCpf;
    private VerifyPersonRg verifyPersonRg;
    private VerifyPersonUserData verifyPersonUserData;

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
                    throw new NotFoundException(
                            getString(MessagesKeyType.PERSON_DATA_NOT_FOUND.message));
                });
    }

    @Transactional
    @Override
    public Person save (Person person) {
        verifyPersonCpf.verifyPersonCpfBeforeSave(person);
        verifyPersonRg.verifyPersonRgBeforeSave(person);
        verifyPersonUserData.verifyPersonUserDataBeforeSave(person);
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
                        new NotFoundException(
                                getString(MessagesKeyType.PERSON_DATA_NOT_FOUND.message)));

    }

    @Transactional
    @Override
    public void delete (UUID id) {
        personRepository.findById(id)
                .map(person -> {
                    personRepository.delete(person);
                    return person;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.PERSON_DATA_NOT_FOUND.message)));
    }
}
