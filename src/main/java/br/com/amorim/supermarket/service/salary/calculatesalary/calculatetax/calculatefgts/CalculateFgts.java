package br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculatefgts;

import br.com.amorim.supermarket.model.salary.Salary;

import java.math.BigDecimal;

public interface CalculateFgts {

    BigDecimal calculateFgtsValue(Salary salary);
}
