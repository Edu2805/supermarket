package br.com.amorim.supermarket.service.person.verifycpf;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.repository.person.verifycpfrepositorycustom.VerifyCpfRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyPersonCpfImpl implements VerifyPersonCpf {

    private VerifyCpfRepositoryCustom verifyCpfRepositoryCustom;
    private PersonRepository personRepository;

    @Override
    public boolean verifyPersonCpfBeforeSave(Person person) {
        if (verifyCpfRepositoryCustom.isCpfAlreadyExistsInTheDatabase(person)) {
            throw new BusinessRuleException(getString(
                    MessagesKeyType.PERSON_CPF_ALREADY_EXISTS_WHEN_SAVE.message));
        }
        return false;
    }

    @Override
    public boolean verifyPersonCpfBeforeUpdate(Person person) {
        var getPeople = personRepository.findAll();
        getPeople.forEach(personExistent -> {
            if (personExistent.getCpf() != null && (
                    personExistent.getCpf().equals(person.getCpf()))) {
                if (!personExistent.getId().equals(person.getId())) {
                    throw new BusinessRuleException(getString(
                            MessagesKeyType.PERSON_CPF_ALREADY_EXISTS_WHEN_UPDATE.message));
                }
            }
        });
        return false;
    }
}
