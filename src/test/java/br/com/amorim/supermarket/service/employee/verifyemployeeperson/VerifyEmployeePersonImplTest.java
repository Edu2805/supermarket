package br.com.amorim.supermarket.service.employee.verifyemployeeperson;

import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.repository.employee.verifyperson.VerifyPersonRepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.employee.EmployeeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VerifyEmployeePersonImplTest {

    @InjectMocks
    private VerifyEmployeePersonImpl verifyEmployeePerson;
    @Mock
    private VerifyPersonRepositoryCustom verifyPersonRepositoryCustom;
    private Employee employee;
    private static final String PERSON_ALREADY_EXISTS_MESSAGE = "A pessoa informada já existe na lista de funcionários.";

    private void startEmployee() {
        EmployeeTest employeeTest = new EmployeeTest();
        employee = employeeTest.generateEmployee();

    }
    @BeforeEach
    void setUp() {
        openMocks(this);
        startEmployee();
    }

    @Test
    void shouldReturnFalseWhenPersonNotExistsWithForAnotherEmployee() {
        when(verifyPersonRepositoryCustom.existsByEmployeePerson(employee))
                .thenReturn(false);
        var verify = verifyEmployeePerson.verifyEmployeePerson(employee);
        assertFalse(verify);
    }

    @Test
    void shouldReturnAExceptionWhenPersonAlreadyExistsWithForAnotherEmployee() {
        when(verifyPersonRepositoryCustom.existsByEmployeePerson(employee))
                .thenReturn(true);
        var messageException = assertThrows(BusinessRuleException.class, () ->
                verifyEmployeePerson.verifyEmployeePerson(employee)
        ).getMessage();

        assertEquals(PERSON_ALREADY_EXISTS_MESSAGE, messageException);
    }
}