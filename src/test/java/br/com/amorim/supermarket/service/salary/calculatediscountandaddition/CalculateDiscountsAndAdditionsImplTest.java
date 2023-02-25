package br.com.amorim.supermarket.service.salary.calculatediscountandaddition;

import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.CalculateDiscountsAndAdditionsImpl;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.calculateotheraddition.CalculateSalaryAddition;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.calculateotherdiscount.CalculateSalaryDiscount;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.verifysalaryadvance.VerifySalaryAdvance;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.otheraddition.OtherAdditionTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.otherdiscount.OtherDiscountTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CalculateDiscountsAndAdditionsImplTest {

    @InjectMocks
    private CalculateDiscountsAndAdditionsImpl calculateDiscountsAndAdditions;
    @Mock
    private CalculateSalaryDiscount calculateSalaryDiscount;
    @Mock
    private CalculateSalaryAddition calculateSalaryAddition;
    @Mock
    private VerifySalaryAdvance verifySalaryAdvance;

    private Salary salary;
    private OtherDiscount otherDiscount1;
    private OtherDiscount otherDiscount2;
    private OtherAddition otherAddition1;
    private OtherAddition otherAddition2;

    private void startSalary() {
        var salaryTest = new SalaryTest();
        var otherDiscountTest1 = new OtherDiscountTest();
        var otherDiscountTest2 = new OtherDiscountTest();
        var otherAdditionTest1 = new OtherAdditionTest();
        var otherAdditionTest2 = new OtherAdditionTest();
        otherDiscount1 = otherDiscountTest1.generateOtherDiscount();
        otherDiscount2 = otherDiscountTest2.generateOtherDiscount();
        otherAddition1 = otherAdditionTest1.generateOtherAddition();
        otherAddition2 = otherAdditionTest2.generateOtherAddition();
        salary = salaryTest.generateSalary();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startSalary();
    }

    @Test
    void shouldSubtractDiscounts() {
        var netSalaryExpected = BigDecimal.valueOf(800.00).setScale(2, RoundingMode.HALF_EVEN);;
        otherDiscount1.setDiscountValue(BigDecimal.valueOf(100.00));
        otherDiscount2.setDiscountValue(BigDecimal.valueOf(100.00));
        var sumDiscounts = otherDiscount1.getDiscountValue().add(otherDiscount2.getDiscountValue());
        salary.setNetSalary(BigDecimal.valueOf(1000.00));
        salary.setOtherDiscounts(List.of(otherDiscount1, otherDiscount2));

        when(calculateSalaryDiscount.calculateDiscount(salary))
                .thenReturn(sumDiscounts);
        calculateDiscountsAndAdditions.discounts(salary);

        assertEquals(netSalaryExpected, salary.getNetSalary());
    }

    @Test
    void shouldSumAdditions() {
        var netSalaryExpected = BigDecimal.valueOf(1200.00).setScale(2, RoundingMode.HALF_EVEN);
        otherAddition1.setAdditionValue(BigDecimal.valueOf(100.00));
        otherAddition2.setAdditionValue(BigDecimal.valueOf(100.00));
        var sumAdditions = otherAddition1.getAdditionValue().add(otherAddition2.getAdditionValue());
        salary.setNetSalary(BigDecimal.valueOf(1000.00));
        salary.setOtherAdditions(List.of(otherAddition1, otherAddition2));

        when(calculateSalaryAddition.calculateAddition(salary))
                .thenReturn(sumAdditions);
        calculateDiscountsAndAdditions.additions(salary);

        assertEquals(netSalaryExpected, salary.getNetSalary());
    }

    @Test
    void shouldSubtractSalaryAdvance() {
        var netSalaryExpected = BigDecimal.valueOf(800.00).setScale(2, RoundingMode.HALF_EVEN);;
        salary.setSalaryAdvance(BigDecimal.valueOf(200.00));
        var salaryAdvance = salary.getSalaryAdvance();
        salary.setNetSalary(BigDecimal.valueOf(1000.00));

        when(verifySalaryAdvance.verifySalaryAdvancePercentageMaximumDiscount(salary))
                .thenReturn(salaryAdvance);
        calculateDiscountsAndAdditions.salaryAdvance(salary);

        assertEquals(netSalaryExpected, salary.getNetSalary());
    }
}