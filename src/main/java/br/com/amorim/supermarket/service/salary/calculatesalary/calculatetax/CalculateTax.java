package br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax;

import br.com.amorim.supermarket.model.salary.Salary;

import java.math.BigDecimal;

public interface CalculateTax {

    void calculateNetSalary(Salary salary);
}
