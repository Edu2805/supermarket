package br.com.amorim.supermarket.service.salary.calculatediscountandaddition.calculateotherdiscount;

import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.otherdiscount.OtherDiscountRepository;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.calculateotherdiscount.CalculateSalaryDiscountImpl;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.otherdiscount.OtherDiscountTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CalculateSalaryDiscountImplTest {

    @InjectMocks
    private CalculateSalaryDiscountImpl calculateSalaryDiscount;
    @Mock
    private OtherDiscountRepository otherDiscountRepository;
    private Salary salary;
    private OtherDiscount otherDiscount1;
    private OtherDiscount otherDiscount2;

    private void startSalary() {
        var salaryTest = new SalaryTest();
        var otherDiscountTest1 = new OtherDiscountTest();
        var otherDiscountTest2 = new OtherDiscountTest();
        otherDiscount1 = otherDiscountTest1.generateOtherDiscount();
        otherDiscount2 = otherDiscountTest2.generateOtherDiscount();
        salary = salaryTest.generateSalary();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startSalary();
    }

    @Test
    void shouldSumTotalDiscountsWhenOtherDiscountExists() {
        var expectedValue = BigDecimal.valueOf(400.00);
        otherDiscount1.setDiscountValue(BigDecimal.valueOf(100.00));
        otherDiscount2.setDiscountValue(BigDecimal.valueOf(300.00));
        salary.setOtherDiscounts(List.of(otherDiscount1, otherDiscount2));

        when(otherDiscountRepository.findById(otherDiscount1.getId()))
                .thenReturn(Optional.of(otherDiscount1));
        when(otherDiscountRepository.findById(otherDiscount2.getId()))
                .thenReturn(Optional.of(otherDiscount2));
        var calculateSum = calculateSalaryDiscount.calculateDiscount(salary);

        assertEquals(expectedValue, calculateSum);
    }

    @Test
    void shouldSubtractZeroWhenOtherDiscountNotExists() {
        var expectedValue = BigDecimal.ZERO;
        salary.setOtherDiscounts(List.of());
        when(otherDiscountRepository.findById(otherDiscount1.getId()))
                .thenReturn(Optional.empty());
        var calculateSum = calculateSalaryDiscount.calculateDiscount(salary);

        assertEquals(expectedValue, calculateSum);
    }
}