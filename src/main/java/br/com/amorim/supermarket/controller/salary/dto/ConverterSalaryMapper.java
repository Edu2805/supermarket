package br.com.amorim.supermarket.controller.salary.dto;

import br.com.amorim.supermarket.controller.salary.dto.request.SalaryDTO;
import br.com.amorim.supermarket.controller.salary.dto.response.SalaryListOutPutDTO;
import br.com.amorim.supermarket.model.salary.Salary;

import java.util.List;

public interface ConverterSalaryMapper {

    Salary createOrUpdateSalaryMapper(SalaryDTO salaryDTO);
    List<SalaryListOutPutDTO> salariesAvailable(List<Salary> salaries);
}
