package br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.calculateotheraddition.CalculateSalaryAddition;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.calculateotherdiscount.CalculateSalaryDiscount;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.verifysalaryadvance.VerifySalaryAdvance;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.math.RoundingMode;

@AllArgsConstructor

@Configuration
public class CalculateDiscountsAndAdditionsImpl implements CalculateDiscountsAndAdditions {

    private CalculateSalaryDiscount calculateSalaryDiscount;
    private CalculateSalaryAddition calculateSalaryAddition;
    private VerifySalaryAdvance verifySalaryAdvance;

    @Override
    public void discounts(Salary salary) {
        var discounts = calculateSalaryDiscount.calculateDiscount(salary);
        var subtractDiscounts = salary.getNetSalary().subtract(discounts);
        salary.setNetSalary(subtractDiscounts.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Override
    public void additions(Salary salary) {
        var additions = calculateSalaryAddition.calculateAddition(salary);
        var addAdditions = salary.getNetSalary().add(additions);
        salary.setNetSalary(addAdditions.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Override
    public void salaryAdvance(Salary salary) {
        var discountSalaryAdvance = verifySalaryAdvance.verifySalaryAdvancePercentageMaximumDiscount(salary);
        if (discountSalaryAdvance != null) {
            var subtractSalaryAdvance = salary.getNetSalary().subtract(discountSalaryAdvance);
            salary.setNetSalary(subtractSalaryAdvance.setScale(2, RoundingMode.HALF_EVEN));
        }
    }
}
