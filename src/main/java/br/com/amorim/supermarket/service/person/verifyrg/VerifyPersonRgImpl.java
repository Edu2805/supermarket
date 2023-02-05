package br.com.amorim.supermarket.service.person.verifyrg;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.repository.person.verifyrgrepositorycustom.VerifyRgRepositoryCustomImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyPersonRgImpl implements VerifyPersonRg {

    private VerifyRgRepositoryCustomImpl verifyRgRepositoryCustom;
    private PersonRepository personRepository;

    @Override
    public boolean verifyPersonRgBeforeSave(Person person) {
        if (verifyRgRepositoryCustom.isRgAlreadyExistsInTheDatabase(person)) {
            throw new BusinessRuleException(getString(
                    MessagesKeyType.PERSON_RG_ALREADY_EXISTS_WHEN_SAVE.message));
        }
        return false;
    }

    @Override
    public boolean verifyPersonRgBeforeUpdate(Person person) {
        var getPeople = personRepository.findAll();
        getPeople.forEach(personExistent -> {
            if (personExistent.getRg() != null && (
                    personExistent.getRg().equals(person.getRg()))) {
                if (!personExistent.getId().equals(person.getId())) {
                    throw new BusinessRuleException(getString(
                            MessagesKeyType.PERSON_RG_ALREADY_EXISTS_WHEN_UPDATE.message));
                }
            }
        });
        return false;
    }
}
