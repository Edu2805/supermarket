package br.com.amorim.supermarket.repository.person.verifyrgrepositorycustom;

import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.person.QPerson;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@AllArgsConstructor

@Repository
public class VerifyRgRepositoryCustomImpl implements VerifyRgRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean isRgAlreadyExistsInTheDatabase(Person person) {
        QPerson qPerson = QPerson.person;
        JPAQuery<Person> query = new JPAQuery<>(entityManager);
        if (person.getRg() == null) return false;

        return !query.select(qPerson)
                .from(qPerson)
                .where(qPerson.rg.eq(person.getRg()))
                .fetch().isEmpty();
    }
}
