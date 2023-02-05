package br.com.amorim.supermarket.repository.person.verifyuserdatarepositorycustom;

import br.com.amorim.supermarket.model.person.Person;

public interface VerifyUserDataRepositoryCustom {

    boolean isUserDataAlreadyExistsInTheDatabase(Person person);
}
