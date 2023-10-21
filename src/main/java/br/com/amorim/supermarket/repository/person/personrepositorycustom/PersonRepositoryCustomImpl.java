package br.com.amorim.supermarket.repository.person.personrepositorycustom;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.employee.QEmployee;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.person.QPerson;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor

@Repository
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public List<Person> existsJobPositionInEmployee() {
        QPerson qPerson = QPerson.person;
        QEmployee qEmployee = QEmployee.employee;

        JPAQuery<JobPosition> queryJobPosition = new JPAQuery<>(entityManager);
        JPAQuery<Employee> queryEmployee = new JPAQuery<>(entityManager);
        return queryJobPosition.select(qPerson).from(qPerson).where(qPerson.id.notIn(
                queryEmployee.select(qEmployee.person.id).from(qEmployee).fetchAll()
        )).stream().toList();
    }
}
