package br.com.amorim.supermarket.service.person;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.attatchment.Attachment;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.attachment.AttachmentRepository;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.service.common.utils.ImageUtil;
import br.com.amorim.supermarket.service.person.getemailuser.PersonEmailUser;
import br.com.amorim.supermarket.service.person.updatefullnameemployee.PersonUpdateUserNameInEmployee;
import br.com.amorim.supermarket.service.person.verifycpf.VerifyPersonCpf;
import br.com.amorim.supermarket.service.person.verifymiddlename.VerifyMiddleName;
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

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private PersonRepository personRepository;
    private AttachmentRepository attachmentRepository;
    private VerifyPageSize verifyPageSize;
    private VerifyPersonCpf verifyPersonCpf;
    private VerifyPersonRg verifyPersonRg;
    private VerifyPersonUserData verifyPersonUserData;
    private PersonEmailUser personEmailUser;
    private VerifyMiddleName verifyMiddleName;
    private PersonUpdateUserNameInEmployee personUpdateUserNameInEmployee;

    @Override
    public Page<Person> getAll(int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
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
        verifyFieldsBeforeSave(person);
        setPhotoAndInsert(person);
        return personRepository.save(person);
    }

    private void setPhotoAndInsert(Person person) {
        if (person.getPersonPhoto() != null) {
            var imageData = person.getPersonPhoto().getImageData();
            person.getPersonPhoto().setImageData(ImageUtil.compressFile(imageData));
            attachmentRepository.save(person.getPersonPhoto());
        }
    }

    private void verifyFieldsBeforeSave(Person person) {
        verifyMiddleName.verifyIfMiddleNameIsNull(person);
        personEmailUser.fillEmailPerson(person);
        verifyPersonCpf.verifyPersonCpfBeforeSave(person);
        verifyPersonRg.verifyPersonRgBeforeSave(person);
        verifyPersonUserData.verifyPersonUserDataBeforeSave(person);
    }

    @Transactional
    @Override
    public void update (Person person, UUID id) {
        personRepository.findById(id)
                .map(existingPerson -> {
                    person.setId(existingPerson.getId());
                    verifyFieldsBeforeUpdate(person);
                    setPhotoAndUpdate(person, existingPerson);
                    personRepository.save(person);
                    return existingPerson;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.PERSON_DATA_NOT_FOUND.message)));

    }

    private void setPhotoAndUpdate(Person personUpdatePhoto, Person personExistentPhoto) {
        if (personUpdatePhoto.getPersonPhoto() != null) {
            changePerson(personExistentPhoto.getPersonPhoto());
            var imageData = personUpdatePhoto.getPersonPhoto().getImageData();
            personUpdatePhoto.getPersonPhoto().setImageData(ImageUtil.compressFile(imageData));
            attachmentRepository.save(personUpdatePhoto.getPersonPhoto());
        }
    }

    private void changePerson(Attachment attachment) {
        if (attachment != null) {
            attachmentRepository.delete(attachment);
        }
    }

    private void verifyFieldsBeforeUpdate(Person person) {
        verifyPersonCpf.verifyPersonCpfBeforeUpdate(person);
        personEmailUser.fillEmailPerson(person);
        verifyPersonRg.verifyPersonRgBeforeUpdate(person);
        personUpdateUserNameInEmployee.updateFullNameEmployee(person);
        verifyPersonUserData.verifyPersonUserDataBeforeUpdate(person);
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
