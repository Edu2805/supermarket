package br.com.amorim.supermarket.service.salary.calculatediscountandaddition;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatediscountandaddition.calculateotheraddition.CalculateSalaryAddition;
import br.com.amorim.supermarket.service.salary.calculatediscountandaddition.calculateotherdiscount.CalculateSalaryDiscount;
import br.com.amorim.supermarket.service.salary.calculatediscountandaddition.verifysalaryadvance.VerifySalaryAdvance;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

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
        salary.setNetSalary(subtractDiscounts);
    }

    @Override
    public void additions(Salary salary) {
        var additions = calculateSalaryAddition.calculateAddition(salary);
        var addAdditions = salary.getNetSalary().add(additions);
        salary.setNetSalary(addAdditions);
    }

    @Override
    public void salaryAdvance(Salary salary) {
        var discountSalaryAdvance = verifySalaryAdvance.verifySalaryAdvancePercentageMaximumDiscount(salary);
        var subtractSalaryAdvance = salary.getNetSalary().subtract(discountSalaryAdvance);
        salary.setNetSalary(subtractSalaryAdvance);
    }
}
