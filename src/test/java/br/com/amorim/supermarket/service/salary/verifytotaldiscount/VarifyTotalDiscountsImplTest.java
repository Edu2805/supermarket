package br.com.amorim.supermarket.service.salary.verifytotaldiscount;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.openMocks;

class VarifyTotalDiscountsImplTest {

    @InjectMocks
    private VarifyTotalDiscountsImpl varifyTotalDiscounts;
    private Salary salary;

    private void startSalary() {
        var salaryTest = new SalaryTest();
        salary = salaryTest.generateSalary();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startSalary();
    }

    @Test
    void shouldReturnFalseIfDeductionsIsLessThanSeventyPercentOfSalary() {
        salary.setGrossSalary(BigDecimal.valueOf(1000.00));
        salary.setNetSalary(BigDecimal.valueOf(301.00));

        var isLessThanSeventyPercent = varifyTotalDiscounts.isSeventyPercentSalaryDeduction(salary);

        assertFalse(isLessThanSeventyPercent);
    }

    @Test
    void shouldReturnMessageExceptionIfDeductionsIsGreaterThanSeventyPercentOfSalary() {
        var expectedMessage = getString(
                MessagesKeyType.MINIMUM_SALARY_PERCENTAGE_ALLOWED_WITH_DISCOUNTS_BY_LAW.message);
        salary.setGrossSalary(BigDecimal.valueOf(1000.00));
        salary.setNetSalary(BigDecimal.valueOf(300.00));

        var messageException = assertThrows(BusinessRuleException.class, () ->
                        varifyTotalDiscounts.isSeventyPercentSalaryDeduction(salary)
                ).getMessage();

        assertEquals(expectedMessage, messageException);
    }
}