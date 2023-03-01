package br.com.amorim.supermarket.service.person.updatefullnameemployee;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.employee.EmployeeRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.employee.EmployeeTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.person.PersonTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PersonUpdateUserNameInEmployeeImplTest {

    @InjectMocks
    private PersonUpdateUserNameInEmployeeImpl personUpdateUserNameInEmployee;
    @Mock
    private EmployeeRepository employeeRepository;

    private Person person;
    private Employee employee;

    private void startPersonAndEmployee() {
        var personTest = new PersonTest();
        var employeeTest = new EmployeeTest();
        person = personTest.generatePerson();
        employee = employeeTest.generateEmployee();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startPersonAndEmployee();
    }

    @Test
    void shouldUpdateFullNameEmployeeWhenPersonMiddleNameIsNullOrEmpty() {
        person.setMiddleName(null);
        when(employeeRepository.findByPerson(person))
                .thenReturn(Optional.of(employee));
        when(employeeRepository.findById(employee.getId()))
                .thenReturn(Optional.of(employee));

        personUpdateUserNameInEmployee.updateFullNameEmployee(person);

        var fullName = person.getFirstName() + " " + person.getLastName();

        assertEquals(employee.getFullName(), fullName);
    }

    @Test
    void shouldUpdateFullNameEmployeeWhenPersonMiddleIsDifferentOfNullOrEmpty() {
        when(employeeRepository.findByPerson(person))
                .thenReturn(Optional.of(employee));
        when(employeeRepository.findById(employee.getId()))
                .thenReturn(Optional.of(employee));

        personUpdateUserNameInEmployee.updateFullNameEmployee(person);

        var fullName = person.getFirstName() + " " + person.getMiddleName() + " " + person.getLastName();

        assertEquals(employee.getFullName(), fullName);
    }
}