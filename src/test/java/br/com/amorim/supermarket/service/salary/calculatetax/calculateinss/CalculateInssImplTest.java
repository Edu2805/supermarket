package br.com.amorim.supermarket.service.salary.calculatetax.calculateinss;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateinss.CalculateInssImpl;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateinss.progressiverate.CalculateProgressiveRate;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CalculateInssImplTest {

    @InjectMocks
    private CalculateInssImpl calculateInss;
    @Mock
    private CalculateProgressiveRate calculateProgressiveRate;
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
    void shouldCalculateInssValueRangeOne() {
        var taxRangeOne = BigDecimal.valueOf(0.075);
        var expectedCalculate = BigDecimal.valueOf(75.00);
        salary.setGrossSalary(BigDecimal.valueOf(1000.00));
        salary.setInss(expectedCalculate);

        when(calculateProgressiveRate.progressiveCalculation(salary, taxRangeOne))
                .thenReturn(salary.getInss());

        var inssValue = calculateInss.calculateInssValue(salary);

        assertEquals(expectedCalculate, inssValue);
    }

    @Test
    void shouldCalculateInssValueRangeTwo() {
        var taxRangeTwo = BigDecimal.valueOf(0.09);
        var expectedCalculate = BigDecimal.valueOf(160.47);
        salary.setGrossSalary(BigDecimal.valueOf(2000.00));
        salary.setInss(expectedCalculate);

        when(calculateProgressiveRate.progressiveCalculation(salary, taxRangeTwo))
                .thenReturn(salary.getInss());

        var inssValue = calculateInss.calculateInssValue(salary);

        assertEquals(expectedCalculate, inssValue);
    }

    @Test
    void shouldCalculateInssValueRangeThree() {
        var taxRangeThree = BigDecimal.valueOf(0.12);
        var expectedCalculate = BigDecimal.valueOf(263.33);
        salary.setGrossSalary(BigDecimal.valueOf(3000.00));
        salary.setInss(expectedCalculate);

        when(calculateProgressiveRate.progressiveCalculation(salary, taxRangeThree))
                .thenReturn(salary.getInss());

        var inssValue = calculateInss.calculateInssValue(salary);

        assertEquals(expectedCalculate, inssValue);
    }

    @Test
    void shouldCalculateInssValueRangeFour() {
        var taxRangeFour = BigDecimal.valueOf(0.14);
        var expectedCalculate = BigDecimal.valueOf(806.19);
        salary.setGrossSalary(BigDecimal.valueOf(7000.00));
        salary.setInss(expectedCalculate);

        when(calculateProgressiveRate.progressiveCalculation(salary, taxRangeFour))
                .thenReturn(salary.getInss());

        var inssValue = calculateInss.calculateInssValue(salary);

        assertEquals(expectedCalculate, inssValue);
    }

    @Test
    void shouldCalculateInssValueRangeFive() {
        var taxRangeFive = BigDecimal.valueOf(0.14);
        var expectedCalculate = BigDecimal.valueOf(877.24);
        salary.setGrossSalary(BigDecimal.valueOf(7507.50));
        salary.setInss(expectedCalculate);

        when(calculateProgressiveRate.progressiveCalculation(salary, taxRangeFive))
                .thenReturn(salary.getInss());

        var inssValue = calculateInss.calculateInssValue(salary);

        assertEquals(expectedCalculate, inssValue);
    }

    @Test
    void shouldCalculateInssValueRangeIsGreaterThanRangeFive() {
        var taxRangeFive = BigDecimal.valueOf(0.14);
        var expectedCalculate = BigDecimal.valueOf(877.24);
        salary.setGrossSalary(BigDecimal.valueOf(8000.00));
        salary.setInss(expectedCalculate);

        when(calculateProgressiveRate.progressiveCalculation(salary, taxRangeFive))
                .thenReturn(salary.getInss());

        var inssValue = calculateInss.calculateInssValue(salary);

        assertEquals(expectedCalculate, inssValue);
    }
}