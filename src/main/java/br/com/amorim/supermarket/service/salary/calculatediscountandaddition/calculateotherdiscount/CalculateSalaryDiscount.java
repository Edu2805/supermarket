package br.com.amorim.supermarket.service.salary.calculatediscountandaddition.calculateotherdiscount;

import br.com.amorim.supermarket.model.salary.Salary;

import java.math.BigDecimal;

public interface CalculateSalaryDiscount {

    BigDecimal calculateDiscount(Salary salary);
}
