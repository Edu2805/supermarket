package br.com.amorim.supermarket.service.salary.verifytotaldiscount;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.salary.Salary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class VarifyTotalDiscountsImpl implements VarifyTotalDiscounts {

    private static final BigDecimal MINIMUM_SALARY_PERCENTAGE_ALLOWED_WITH_DISCOUNTS = BigDecimal.valueOf(0.3);
    public static final int ZERO = 0;

    /*
        A CLT dispõe em seu artigo 82, que o empregador que fornecer parte do salário mínimo como
        salário utilidade ou in natura, terá esta parte limitada a 70% (setenta por cento), ou seja, será garantido
        ao empregado o pagamento em dinheiro de no mínimo 30% (trinta por cento) do salário mínimo.
        https://www.planalto.gov.br/ccivil_03/decreto-lei/del5452compilado.htm
     */
    @Override
    public boolean isSeventyPercentSalaryDeduction(Salary salary) {
        var salaryAllowed = salary.getGrossSalary().multiply(MINIMUM_SALARY_PERCENTAGE_ALLOWED_WITH_DISCOUNTS);
        if (salary.getNetSalary().compareTo(salaryAllowed) < ZERO
                || salary.getNetSalary().compareTo(salaryAllowed) == ZERO) {
            throw new BusinessRuleException(getString(
                    MessagesKeyType.MINIMUM_SALARY_PERCENTAGE_ALLOWED_WITH_DISCOUNTS_BY_LAW.message));
        }
        return false;
    }
}
