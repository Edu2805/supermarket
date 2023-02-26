package br.com.amorim.supermarket.service.salary.calculatetax.calculateinss.progressiverate;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateinss.progressiverate.CalculateProgressiveRateImpl;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;

class CalculateProgressiveRateImplTest {

    @InjectMocks
    private CalculateProgressiveRateImpl calculateProgressiveRate;
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
    void shouldCalculateProgressiveInssTaxRangeOne() {
        var taxRangeOne = BigDecimal.valueOf(0.075);
        salary.setGrossSalary(BigDecimal.valueOf(1000.00));
        var expectedClaculate = BigDecimal.valueOf(75.00);

        var progressiveCalculation = calculateProgressiveRate.progressiveCalculation(salary, taxRangeOne);
        assertEquals(expectedClaculate.setScale(2, RoundingMode.HALF_EVEN),
                progressiveCalculation.setScale(2, RoundingMode.HALF_EVEN));

    }

    @Test
    void shouldCalculateProgressiveInssTaxRangeTwo() {
        var taxRangeTwo = BigDecimal.valueOf(0.09);
        salary.setGrossSalary(BigDecimal.valueOf(2000.00));
        var expectedClaculate = BigDecimal.valueOf(160.47);

        var progressiveCalculation = calculateProgressiveRate.progressiveCalculation(salary, taxRangeTwo);
        assertEquals(expectedClaculate.setScale(2, RoundingMode.HALF_EVEN),
                progressiveCalculation.setScale(2, RoundingMode.HALF_EVEN));

    }

    @Test
    void shouldCalculateProgressiveInssTaxRangeThree() {
        var taxRangeThree = BigDecimal.valueOf(0.12);
        salary.setGrossSalary(BigDecimal.valueOf(3000.00));
        var expectedClaculate = BigDecimal.valueOf(263.33);

        var progressiveCalculation = calculateProgressiveRate.progressiveCalculation(salary, taxRangeThree);
        assertEquals(expectedClaculate.setScale(2, RoundingMode.HALF_EVEN),
                progressiveCalculation.setScale(2, RoundingMode.HALF_EVEN));

    }

    @Test
    void shouldCalculateProgressiveInssTaxRangeFour() {
        var taxRangeFour = BigDecimal.valueOf(0.14);
        salary.setGrossSalary(BigDecimal.valueOf(7000.00));
        var expectedClaculate = BigDecimal.valueOf(806.19);

        var progressiveCalculation = calculateProgressiveRate.progressiveCalculation(salary, taxRangeFour);
        assertEquals(expectedClaculate.setScale(2, RoundingMode.HALF_EVEN),
                progressiveCalculation.setScale(2, RoundingMode.HALF_EVEN));

    }

    @Test
    void shouldCalculateProgressiveInssWhenTaxRangeIsEqualsRangeFive() {
        var taxRangeFive = BigDecimal.valueOf(0.14);
        salary.setGrossSalary(BigDecimal.valueOf(7507.50));
        var expectedClaculate = BigDecimal.valueOf(877.24);

        var progressiveCalculation = calculateProgressiveRate.progressiveCalculation(salary, taxRangeFive);
        assertEquals(expectedClaculate.setScale(2, RoundingMode.HALF_EVEN),
                progressiveCalculation.setScale(2, RoundingMode.HALF_EVEN));

    }

    @Test
    void shouldCalculateProgressiveInssWhenTaxRangeIsGreaterThanRangeFive() {
        var taxRangeFive = BigDecimal.valueOf(0.14);
        salary.setGrossSalary(BigDecimal.valueOf(8000.00));
        var expectedClaculate = BigDecimal.valueOf(877.24);

        var progressiveCalculation = calculateProgressiveRate.progressiveCalculation(salary, taxRangeFive);
        assertEquals(expectedClaculate.setScale(2, RoundingMode.HALF_EVEN),
                progressiveCalculation.setScale(2, RoundingMode.HALF_EVEN));

    }
}