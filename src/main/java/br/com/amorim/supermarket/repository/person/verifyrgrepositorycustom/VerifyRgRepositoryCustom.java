package br.com.amorim.supermarket.repository.person.verifyrgrepositorycustom;

import br.com.amorim.supermarket.model.person.Person;

public interface VerifyRgRepositoryCustom {

    boolean isRgAlreadyExistsInTheDatabase(Person person);
}
