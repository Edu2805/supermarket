package br.com.amorim.supermarket.service.salary.verifyduplicatesalary;

import br.com.amorim.supermarket.model.salary.Salary;

public interface VerifyDuplicateSalary {

    boolean isDuplicateSalaryBeforeSave(Salary salary);
    boolean isDuplicateSalaryBeforeUpdate(Salary salary);
}
