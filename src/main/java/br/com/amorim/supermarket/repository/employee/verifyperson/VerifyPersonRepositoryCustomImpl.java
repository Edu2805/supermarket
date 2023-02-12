package br.com.amorim.supermarket.repository.employee.verifyperson;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.employee.QEmployee;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@AllArgsConstructor

@Repository
public class VerifyPersonRepositoryCustomImpl implements VerifyPersonRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean existsByEmployeePerson(Employee employee) {
        QEmployee qEmployee = QEmployee.employee;
        JPAQuery<Employee> query = new JPAQuery<>(entityManager);
        return !query.select(qEmployee)
                .from(qEmployee)
                .where(qEmployee.person.eq(employee.getPerson()))
                .fetch().isEmpty();
    }
}
