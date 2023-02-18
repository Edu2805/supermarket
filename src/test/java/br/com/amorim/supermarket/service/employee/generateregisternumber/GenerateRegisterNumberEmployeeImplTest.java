package br.com.amorim.supermarket.service.employee.generateregisternumber;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.repository.employee.generateregisternumberemployeerepositorycustom.GenerateRegisterNumberEmployeeRepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.employee.EmployeeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GenerateRegisterNumberEmployeeImplTest {

    @InjectMocks
    private GenerateRegisterNumberEmployeeImpl generateRegisterNumberEmployee;
    @Mock
    private GenerateRegisterNumberEmployeeRepositoryCustom generateRegisterNumberEmployeeRepositoryCustom;
    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private static final BigInteger REGISTER_NUMBER_ONE = BigInteger.valueOf(1);
    private static final BigInteger REGISTER_NUMBER_TWO = BigInteger.valueOf(2);
    private static final BigInteger REGISTER_NUMBER_THREE = BigInteger.valueOf(3);

    private void startEmployee() {
        EmployeeTest employeeTest1 = new EmployeeTest();
        EmployeeTest employeeTest2 = new EmployeeTest();
        EmployeeTest employeeTest3 = new EmployeeTest();
        employee1 = employeeTest1.generateEmployee();
        employee2 = employeeTest2.generateEmployee();
        employee3 = employeeTest3.generateEmployee();

    }
    @BeforeEach
    void setUp() {
        openMocks(this);
        startEmployee();
    }

    @Test
    void shouldGenerateRegisterNumber() {
        when(generateRegisterNumberEmployeeRepositoryCustom.generateRegisterNumber(employee1))
                .thenReturn(REGISTER_NUMBER_ONE);
        when(generateRegisterNumberEmployeeRepositoryCustom.generateRegisterNumber(employee2))
                .thenReturn(REGISTER_NUMBER_TWO);
        when(generateRegisterNumberEmployeeRepositoryCustom.generateRegisterNumber(employee3))
                .thenReturn(REGISTER_NUMBER_THREE);
        var generateRegisterNumber = generateRegisterNumberEmployee.generate(employee3);

        assertEquals(REGISTER_NUMBER_THREE, generateRegisterNumber);
    }
}