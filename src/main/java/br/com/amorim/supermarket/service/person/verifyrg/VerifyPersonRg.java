package br.com.amorim.supermarket.service.person.verifyrg;

import br.com.amorim.supermarket.model.person.Person;

public interface VerifyPersonRg {

    boolean verifyPersonRgBeforeSave(Person person);
    boolean verifyPersonRgBeforeUpdate(Person person);
}
