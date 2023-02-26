package br.com.amorim.supermarket.service.salary.calculatesalary;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.CalculateDiscountsAndAdditions;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.CalculateTax;
import br.com.amorim.supermarket.service.salary.verifytotaldiscount.VarifyTotalDiscounts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class CalculateSalaryImpl implements CalculateSalary {

    private CalculateTax calculateTax;
    private CalculateDiscountsAndAdditions calculateDiscountsAndAdditions;
    private VarifyTotalDiscounts varifyTotalDiscounts;

    @Override
    public void calculate(Salary salary) {
        calculateTax.calculateNetSalary(salary);
        calculateDiscountsAndAdditions.additions(salary);
        calculateDiscountsAndAdditions.discounts(salary);
        calculateDiscountsAndAdditions.salaryAdvance(salary);
        varifyTotalDiscounts.isSeventyPercentSalaryDeduction(salary);
    }
}
