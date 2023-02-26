package br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateinss.progressiverate;

import br.com.amorim.supermarket.model.salary.Salary;

import java.math.BigDecimal;

public interface CalculateProgressiveRate {

    BigDecimal progressiveCalculation(Salary salary, BigDecimal taxRange);
}
