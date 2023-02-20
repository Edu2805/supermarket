package br.com.amorim.supermarket.service.salary.verifyduplicatesalary;

import br.com.amorim.supermarket.model.salary.Salary;

public interface VerifyDuplicateSalary {

    boolean isDuplicateSalary(Salary salary);
}
