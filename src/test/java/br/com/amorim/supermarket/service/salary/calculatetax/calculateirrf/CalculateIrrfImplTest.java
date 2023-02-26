package br.com.amorim.supermarket.service.salary.calculatetax.calculateirrf;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateinss.CalculateInss;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateirrf.CalculateIrrfImpl;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CalculateIrrfImplTest {

    @InjectMocks
    private CalculateIrrfImpl calculateIrrf;
    @Mock
    private CalculateInss calculateInss;

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
    void shouldCalculateIrrfValueRangeOne() {
        salary.setGrossSalary(BigDecimal.valueOf(1000.00));
        salary.setInss(BigDecimal.valueOf(75.00));
        when(calculateInss.calculateInssValue(salary))
                .thenReturn(salary.getInss());
        var irrfValue = calculateIrrf.calculateIrrfValue(salary);

        assertEquals(BigDecimal.valueOf(0), irrfValue);
    }

    @Test
    void shouldCalculateIrrfValueRangeTwo() {
        salary.setGrossSalary(BigDecimal.valueOf(2500.00));
        salary.setInss(BigDecimal.valueOf(205.47));
        when(calculateInss.calculateInssValue(salary))
                .thenReturn(salary.getInss());
        var irrfValue = calculateIrrf.calculateIrrfValue(salary);

        assertEquals(BigDecimal.valueOf(29.29), irrfValue.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void shouldCalculateIrrfValueRangeThree() {
        salary.setGrossSalary(BigDecimal.valueOf(3500.00));
        salary.setInss(BigDecimal.valueOf(323.33));
        when(calculateInss.calculateInssValue(salary))
                .thenReturn(salary.getInss());
        var irrfValue = calculateIrrf.calculateIrrfValue(salary);

        assertEquals(BigDecimal.valueOf(121.70).setScale(2, RoundingMode.HALF_EVEN),
                irrfValue.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void shouldCalculateIrrfValueRangeFour() {
        salary.setGrossSalary(BigDecimal.valueOf(4400.00));
        salary.setInss(BigDecimal.valueOf(442.19));
        when(calculateInss.calculateInssValue(salary))
                .thenReturn(salary.getInss());
        var irrfValue = calculateIrrf.calculateIrrfValue(salary);

        assertEquals(BigDecimal.valueOf(254.38), irrfValue.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void shouldCalculateIrrfValueRangeFive() {
        salary.setGrossSalary(BigDecimal.valueOf(5600.00));
        salary.setInss(BigDecimal.valueOf(610.19));
        when(calculateInss.calculateInssValue(salary))
                .thenReturn(salary.getInss());
        var irrfValue = calculateIrrf.calculateIrrfValue(salary);

        assertEquals(BigDecimal.valueOf(502.84), irrfValue.setScale(2, RoundingMode.HALF_EVEN));
    }
}