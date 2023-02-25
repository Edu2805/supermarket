package br.com.amorim.supermarket.service.salary.calculatediscountandaddition.verifysalaryadvance;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.verifysalaryadvance.VerifySalaryAdvanceImpl;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.openMocks;

class VerifySalaryAdvanceImplTest {

    @InjectMocks
    private VerifySalaryAdvanceImpl verifySalaryAdvance;
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
    void shouldSetSalaryAdvancePercentageDiscountWhenSalaryAdvanceDiscountIsLessThanFortyPercent() {
        var expectedDiscount = BigDecimal.valueOf(400.00);
        salary.setGrossSalary(BigDecimal.valueOf(1000.00));
        salary.setSalaryAdvance(BigDecimal.valueOf(400.00));
        var salaryAdvanceDiscount = verifySalaryAdvance.verifySalaryAdvancePercentageMaximumDiscount(salary);
        assertEquals(expectedDiscount, salaryAdvanceDiscount);
    }

    @Test
    void shouldNotSetSalaryAdvancePercentageDiscountWhenSalaryAdvanceDiscountIsGreaterThanFortyPercent() {
        var messageExpected = getString(
                MessagesKeyType.MINIMUM_SALARY_PERCENTAGE_ALLOWED_FOR_SALARY_ADVANCE.message);
        salary.setGrossSalary(BigDecimal.valueOf(1000.00));
        salary.setSalaryAdvance(BigDecimal.valueOf(500.00));
        var messageException = assertThrows(BusinessRuleException.class, () ->
                        verifySalaryAdvance.verifySalaryAdvancePercentageMaximumDiscount(salary)
                ).getMessage();
        assertEquals(messageExpected, messageException);
    }
}