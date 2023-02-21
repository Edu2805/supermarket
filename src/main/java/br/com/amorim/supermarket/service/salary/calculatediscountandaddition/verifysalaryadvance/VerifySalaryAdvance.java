package br.com.amorim.supermarket.service.salary.calculatediscountandaddition.verifysalaryadvance;

import br.com.amorim.supermarket.model.salary.Salary;

import java.math.BigDecimal;

public interface VerifySalaryAdvance {

    BigDecimal verifySalaryAdvancePercentageMaximumDiscount(Salary salary);
}
