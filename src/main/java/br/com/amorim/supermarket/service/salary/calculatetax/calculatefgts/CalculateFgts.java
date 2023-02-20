package br.com.amorim.supermarket.service.salary.calculatetax.calculatefgts;

import br.com.amorim.supermarket.model.salary.Salary;

import java.math.BigDecimal;

public interface CalculateFgts {

    BigDecimal calculateFgtsValue(Salary salary);
}
