package br.com.amorim.supermarket.service.employee.employeefullname;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.employee.EmployeeTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.person.PersonTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeFullNameImplTest {

    @InjectMocks
    private EmployeeFullNameImpl employeeFullName;
    @Mock
    private PersonRepository personRepository;

    private Employee employee;
    private Person person;

    private void startEmployee() {
        EmployeeTest employeeTest = new EmployeeTest();
        PersonTest personTest = new PersonTest();
        employee = employeeTest.generateEmployee();
        person = personTest.generatePerson();
    }
    @BeforeEach
    void setUp() {
        openMocks(this);
        startEmployee();
    }

    @Test
    void shouldFillEmployeeFullNameIfMiddleNameIsPresent() {
        var personFirstName = person.getFirstName();
        var personMiddleName = person.getMiddleName();
        var personLastName = person.getLastName();
        var fullName = personFirstName + " " + personMiddleName + " " + personLastName;

        when(personRepository.findById(employee.getPerson().getId()))
                .thenReturn(Optional.of(person));
        var fillMiddleName = employeeFullName.fillEmployeeFullName(employee);

        assertEquals(fullName, fillMiddleName);
    }

    @Test
    void shouldFillEmployeeFullNameIfMiddleNameIsNotPresent() {
        var personFirstName = person.getFirstName();
        person.setMiddleName("");
        var personLastName = person.getLastName();
        var fullName = personFirstName + " " + personLastName;

        when(personRepository.findById(employee.getPerson().getId()))
                .thenReturn(Optional.of(person));
        var fillMiddleName = employeeFullName.fillEmployeeFullName(employee);

        assertEquals(fullName, fillMiddleName);
    }
}