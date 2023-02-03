package br.com.amorim.supermarket.service.person.verifycpf;

import br.com.amorim.supermarket.model.person.Person;

public interface VerifyPersonCpf {

    boolean verifyPersonCpfBeforeSave(Person person);
    boolean verifyPersonCpfBeforeUpdate(Person person);
}
