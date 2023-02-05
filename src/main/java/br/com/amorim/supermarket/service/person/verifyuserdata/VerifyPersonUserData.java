package br.com.amorim.supermarket.service.person.verifyuserdata;

import br.com.amorim.supermarket.model.person.Person;

public interface VerifyPersonUserData {

    boolean verifyPersonUserDataBeforeSave(Person person);
    boolean verifyPersonUserDataBeforeUpdate(Person person);
}
