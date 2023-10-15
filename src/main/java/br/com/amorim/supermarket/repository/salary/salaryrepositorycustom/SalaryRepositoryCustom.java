package br.com.amorim.supermarket.repository.salary.salaryrepositorycustom;

import br.com.amorim.supermarket.model.salary.Salary;

import java.util.List;

public interface SalaryRepositoryCustom {

    List<Salary> existsSalaryInJobPosition();
}
