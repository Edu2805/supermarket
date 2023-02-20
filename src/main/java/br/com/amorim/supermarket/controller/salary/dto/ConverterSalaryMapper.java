package br.com.amorim.supermarket.controller.salary.dto;

import br.com.amorim.supermarket.model.salary.Salary;

public interface ConverterSalaryMapper {

    Salary createOrUpdateSalaryMapper(SalaryDTO salaryDTO);
}
