package br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.calculateotheraddition;

import br.com.amorim.supermarket.model.salary.Salary;

import java.math.BigDecimal;

public interface CalculateSalaryAddition {

    BigDecimal calculateAddition(Salary salary);
}
