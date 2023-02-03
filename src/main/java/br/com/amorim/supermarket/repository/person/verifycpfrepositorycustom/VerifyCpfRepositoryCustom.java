package br.com.amorim.supermarket.repository.person.verifycpfrepositorycustom;

import br.com.amorim.supermarket.model.person.Person;

public interface VerifyCpfRepositoryCustom {

    boolean isCpfAlreadyExistsInTheDatabase(Person person);
}
