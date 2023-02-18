package br.com.amorim.supermarket.controller.employee.dto;

import br.com.amorim.supermarket.model.employee.Employee;

public interface ConvertEmployeeMapper {

    Employee createEmployeeMapper(EmployeeSaveDTO employeeDTO);
    Employee updateEmployeeMapper(EmployeeUpdateDTO employeeUpdateDTO);
}
