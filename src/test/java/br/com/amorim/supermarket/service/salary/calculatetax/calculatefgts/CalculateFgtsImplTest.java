package br.com.amorim.supermarket.service.salary.calculatetax.calculatefgts;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculatefgts.CalculateFgtsImpl;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;

class CalculateFgtsImplTest {

    @InjectMocks
    private CalculateFgtsImpl calculateFgts;
    private Salary salary;

    private void startSalary() {
        SalaryTest salaryTest = new SalaryTest();
        salary = salaryTest.generateSalary();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startSalary();
    }

    @Test
    void shouldCalculateFgtsValue() {
        salary.setGrossSalary(BigDecimal.valueOf(1000.00));
        var fgtsValueExpected = BigDecimal.valueOf(0.08).multiply(salary.getGrossSalary());

        var calculate = calculateFgts.calculateFgtsValue(salary);

        assertEquals(fgtsValueExpected, calculate);
    }
}