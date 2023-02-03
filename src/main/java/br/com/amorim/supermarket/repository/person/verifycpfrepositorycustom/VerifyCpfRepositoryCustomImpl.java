package br.com.amorim.supermarket.repository.person.verifycpfrepositorycustom;

import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.person.QPerson;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@AllArgsConstructor

@Repository
public class VerifyCpfRepositoryCustomImpl implements VerifyCpfRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean isCpfAlreadyExistsInTheDatabase(Person person) {
        QPerson qPerson = QPerson.person;
        JPAQuery<Person> query = new JPAQuery<>(entityManager);
        return !query.select(qPerson)
                .from(qPerson)
                .where(qPerson.cpf.eq(person.getCpf()))
                .fetch().isEmpty();
    }
}
