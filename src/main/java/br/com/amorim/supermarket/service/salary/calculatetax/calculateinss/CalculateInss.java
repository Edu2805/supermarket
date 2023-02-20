package br.com.amorim.supermarket.service.salary.calculatetax.calculateinss;

import br.com.amorim.supermarket.model.salary.Salary;

import java.math.BigDecimal;

public interface CalculateInss {

    BigDecimal calculateInssValue(Salary salary);
}
