package br.com.amorim.supermarket.testutils.generateentitiesunittests.employee;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.jobposition.JobPositionTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.person.PersonTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;

import java.math.BigInteger;
import java.util.UUID;

public class EmployeeTest {

    public Employee generateEmployee() {
        PersonTest personTest = new PersonTest();
        JobPositionTest jobPositionTest = new JobPositionTest();
        SubSectionTest subSectionTest = new SubSectionTest();

        Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setFullName("Nome Teste");
        employee.setRegisterNumber(BigInteger.valueOf(1));
        employee.setPerson(personTest.generatePerson());
        employee.setJobPosition(jobPositionTest.generateJobPosition());
        employee.setSubSection(subSectionTest.generateSubsection());
        return employee;
    }
}
