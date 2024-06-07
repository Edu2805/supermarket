package br.com.amorim.supermarket.controller.employee.dto.mapper;

import br.com.amorim.supermarket.controller.employee.dto.EmployeeDetailsOutputDTO;
import br.com.amorim.supermarket.model.employee.Employee;

public interface EmployeeMapper {

    EmployeeDetailsOutputDTO toEmployeeDetailsOutputDTO(Employee employee);
}
