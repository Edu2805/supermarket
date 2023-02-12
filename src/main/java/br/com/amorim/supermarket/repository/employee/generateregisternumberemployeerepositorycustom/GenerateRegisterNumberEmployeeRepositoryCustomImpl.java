package br.com.amorim.supermarket.repository.employee.generateregisternumberemployeerepositorycustom;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.employee.QEmployee;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@AllArgsConstructor

@Repository
public class GenerateRegisterNumberEmployeeRepositoryCustomImpl implements
        GenerateRegisterNumberEmployeeRepositoryCustom {

    private EntityManager entityManager;
    private static final int START_CODE_ONE = 1;
    private static final int INCREASE_CODE_ONE = 1;

    @Override
    public BigInteger generateRegisterNumber(Employee employee) {
        QEmployee qEmployee = QEmployee.employee;
        JPAQuery<Employee> query = new JPAQuery<>(entityManager);
        BigInteger employeeDataQuery = query.select(qEmployee.registerNumber.max())
                .from(qEmployee).fetchOne();

        if (employeeDataQuery != null) {
            return employeeDataQuery.add(BigInteger.valueOf(INCREASE_CODE_ONE));
        }

        return BigInteger.valueOf(START_CODE_ONE);
    }
}
