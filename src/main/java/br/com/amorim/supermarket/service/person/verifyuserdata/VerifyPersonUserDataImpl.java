package br.com.amorim.supermarket.service.person.verifyuserdata;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.repository.person.verifyuserdatarepositorycustom.VerifyUserDataRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyPersonUserDataImpl implements VerifyPersonUserData {

    private VerifyUserDataRepositoryCustom verifyUserDataRepositoryCustom;
    private PersonRepository personRepository;

    @Override
    public boolean verifyPersonUserDataBeforeSave(Person person) {
        if (verifyUserDataRepositoryCustom.isUserDataAlreadyExistsInTheDatabase(person)) {
            throw new BusinessRuleException(getString(
                    MessagesKeyType.PERSON_USER_DATA_ALREADY_EXISTS_WHEN_SAVE.message));
        }
        return false;
    }

    @Override
    public boolean verifyPersonUserDataBeforeUpdate(Person person) {
        var getPeople = personRepository.findAll();
        getPeople.forEach(personExistent -> {
            if (personExistent.getUserData() != null && (
                    personExistent.getUserData().getId().equals(person.getUserData().getId()))) {
                if (!personExistent.getId().equals(person.getId())) {
                    throw new BusinessRuleException(getString(
                            MessagesKeyType.PERSON_USER_DATA_ALREADY_EXISTS_WHEN_UPDATE.message));
                }
            }
        });
        return false;
    }
}
