package br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateirrf;

import br.com.amorim.supermarket.model.salary.Salary;

import java.math.BigDecimal;

public interface CalculateIrrf {

    BigDecimal calculateIrrfValue(Salary salary);
}
