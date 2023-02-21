package br.com.amorim.supermarket.service.salary.calculatediscountandaddition.verifysalaryadvance;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.salary.Salary;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Configuration
public class VerifySalaryAdvanceImpl implements VerifySalaryAdvance {

    private static final BigDecimal MAXIMUM_SALARY_PERCENTAGE_ALLOWED_FOR_SALARY_ADVANCE = BigDecimal.valueOf(0.4000);
    public static final int ZERO = 0;

    @Override
    public BigDecimal verifySalaryAdvancePercentageMaximumDiscount(Salary salary) {
        var salaryAdvance = salary.getSalaryAdvance();
        var grossSalary = salary.getGrossSalary();
        var verifyMaximumSalaryAdvance = (salaryAdvance.divide(grossSalary))
                .compareTo(MAXIMUM_SALARY_PERCENTAGE_ALLOWED_FOR_SALARY_ADVANCE) > ZERO;

        if (verifyMaximumSalaryAdvance) {
            throw new NotFoundException(getString(
                    MessagesKeyType.MINIMUM_SALARY_PERCENTAGE_ALLOWED_FOR_SALARY_ADVANCE.message));
        }
        return salaryAdvance;
    }
}
