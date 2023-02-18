package br.com.amorim.supermarket.service.employee;

import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.employee.EmployeeRepository;
import br.com.amorim.supermarket.service.employee.admitemployee.AdmitEmployee;
import br.com.amorim.supermarket.service.employee.employeefullname.EmployeeFullName;
import br.com.amorim.supermarket.service.employee.generateregisternumber.GenerateRegisterNumberEmployee;
import br.com.amorim.supermarket.service.employee.verifyemployeeperson.VerifyEmployeePerson;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.employee.EmployeeTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.person.PersonTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.userdata.UserDataTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class EmployeeCrudServiceImplTest {

    @InjectMocks
    private EmployeeCrudServiceImpl employeeCrudService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private GenerateRegisterNumberEmployee generateRegisterNumberEmployee;
    @Mock
    private VerifyEmployeePerson verifyEmployeePerson;
    @Mock
    private EmployeeFullName employeeFullName;
    @Mock
    private AdmitEmployee admitEmployee;
    private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "O funcionário informado não existe.";
    private Employee employee;
    private Person person;
    private UserData userData;
    ArgumentCaptor<UUID> knownIdCapture;

    private void startEmployee() {
        EmployeeTest employeeTest = new EmployeeTest();
        UserDataTest userDataTest = new UserDataTest();
        PersonTest personTest = new PersonTest();
        employee = employeeTest.generateEmployee();
        person = personTest.generatePerson();
        userData = userDataTest.generateUserData();
        knownIdCapture = ArgumentCaptor.forClass(UUID.class);
    }
    @BeforeEach
    void setUp() {
        openMocks(this);
        startEmployee();
    }

    @Test
    void shouldFindByIdWithSuccess() {
        when(employeeRepository.findById(employee.getId()))
                .thenReturn(Optional.of(employee));
        var findEmployee = employeeCrudService.findById(employee.getId());

        assertNotNull(findEmployee);
        assertEquals(findEmployee.getId(), employee.getId());
    }

    @Test
    void shouldNotFindByIdWhenEmployeeNotExists() {
        when(employeeRepository.findById(employee.getId()))
                .thenReturn(Optional.empty());
        var exceptionMessage = assertThrows(NotFoundException.class, () ->
                employeeCrudService.findById(employee.getId())
        ).getMessage();

        assertEquals(EMPLOYEE_NOT_FOUND_MESSAGE, exceptionMessage);
    }

    @Test
    void shouldSaveWithSuccess() {
        when(employeeRepository.save(employee))
                .thenReturn(employee);
        var saveEmployee = employeeCrudService.save(employee);

        assertNotNull(saveEmployee);
        assertEquals(Employee.class, saveEmployee.getClass());
    }

    @Test
    void shouldUpdateWithSuccess() {
        when(employeeRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        employeeCrudService.update(employee, employee.getId());

        assertEquals(employee.getId(), knownIdCapture.getValue());
        assertEquals(employee, employeeRepository.save(employee));
        assertEquals(employee.getRegisterNumber(),
                employeeRepository.save(employee).getRegisterNumber());
        assertEquals(employee.getClass(),
                employeeRepository.save(employee).getClass());
    }

    @Test
    void shouldNotUpdateWhenEmployeeNotExists() {
        when(employeeRepository.findById(employee.getId()))
                .thenReturn(Optional.empty());
        var exceptionMessage = assertThrows(NotFoundException.class, () ->
                employeeCrudService.update(employee, employee.getId())
        ).getMessage();

        assertEquals(EMPLOYEE_NOT_FOUND_MESSAGE, exceptionMessage);
    }

    @Test
    void shouldDeleteWithSuccess() {
        when(employeeRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(employee);

        employeeCrudService.delete(employee.getId());

        verify(employeeRepository, times(1)).delete(employee);
    }

    @Test
    void shouldBeExactlyIdPassedAsAnArgumentWhenDelete() {
        when(employeeRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(employee);

        employeeCrudService.delete(employee.getId());

        assertEquals(employee.getId(), knownIdCapture.getValue());
    }

    @Test
    void shouldNotDeleteWhenEmployeeNotExists() {
        when(employeeRepository.findById(employee.getId()))
                .thenReturn(Optional.empty());
        doNothing().when(employeeRepository).delete(employee);

        var exceptionMessage = assertThrows(NotFoundException.class, () ->
                employeeCrudService.delete(employee.getId())
        ).getMessage();

        assertEquals(EMPLOYEE_NOT_FOUND_MESSAGE, exceptionMessage);
        verify(employeeRepository, never()).delete(employee);
    }
}