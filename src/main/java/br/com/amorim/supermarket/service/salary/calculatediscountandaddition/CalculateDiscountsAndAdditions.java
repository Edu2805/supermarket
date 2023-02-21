package br.com.amorim.supermarket.service.salary.calculatediscountandaddition;

import br.com.amorim.supermarket.model.salary.Salary;

public interface CalculateDiscountsAndAdditions {

    void discounts(Salary salary);
    void additions(Salary salary);
    void salaryAdvance(Salary salary);
}
