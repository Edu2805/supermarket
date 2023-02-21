package br.com.amorim.supermarket.service.salary.verifytotaldiscount;

import br.com.amorim.supermarket.model.salary.Salary;

public interface VarifyTotalDiscounts {

    boolean isSeventyPercentSalaryDeduction(Salary salary);
}
