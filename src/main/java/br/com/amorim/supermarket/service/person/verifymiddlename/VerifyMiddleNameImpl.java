package br.com.amorim.supermarket.service.person.verifymiddlename;

import br.com.amorim.supermarket.model.person.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class VerifyMiddleNameImpl implements VerifyMiddleName {

    @Override
    public void verifyIfMiddleNameIsNull(Person person) {
        var personMiddleName = person.getMiddleName() == null ? "" : person.getMiddleName();
        person.setMiddleName(personMiddleName);
    }
}
