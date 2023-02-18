package br.com.amorim.supermarket.service.employee.admitemployee;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.employee.EmployeeTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.person.PersonTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.userdata.UserDataTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdmitEmployeeImplTest {

    @InjectMocks
    private AdmitEmployeeImpl admitEmployee;
    @Mock
    private UserDataRepository userDataRepository;
    @Mock
    private PersonRepository personRepository;

    private Employee employee;
    private Person person;
    private UserData userData;

    private void startEmployee() {
        EmployeeTest employeeTest = new EmployeeTest();
        UserDataTest userDataTest = new UserDataTest();
        PersonTest personTest = new PersonTest();
        employee = employeeTest.generateEmployee();
        person = personTest.generatePerson();
        userData = userDataTest.generateUserData();
    }
    @BeforeEach
    void setUp() {
        openMocks(this);
        startEmployee();
    }

    @Test
    void shouldSetUserWithIsEmployeeTrue() {
        userData.setIsEmployee(false);
        when(personRepository.findById(employee.getPerson().getId()))
                .thenReturn(Optional.of(person));
        when(userDataRepository.findById(person.getUserData().getId()))
                .thenReturn(Optional.of(userData));
        admitEmployee.isEmployee(employee);
        assertTrue(userData.getIsEmployee());
    }

    @Test
    void shouldNotSetUserWithIsEmployeeTrueWhenPersonIsNotPresent() {
        userData.setIsEmployee(false);
        when(personRepository.findById(employee.getPerson().getId()))
                .thenReturn(Optional.empty());
        admitEmployee.isEmployee(employee);
        assertFalse(userData.getIsEmployee());
    }

    @Test
    void shouldNotSetUserWithIsEmployeeTrueWhenUserDataIsNotPresent() {
        userData.setIsEmployee(false);
        when(personRepository.findById(employee.getPerson().getId()))
                .thenReturn(Optional.of(person));
        when(userDataRepository.findById(person.getUserData().getId()))
                .thenReturn(Optional.empty());
        admitEmployee.isEmployee(employee);
        assertFalse(userData.getIsEmployee());
    }
}