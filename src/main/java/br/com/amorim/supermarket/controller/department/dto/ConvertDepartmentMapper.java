package br.com.amorim.supermarket.controller.department.dto;

import br.com.amorim.supermarket.model.department.Department;

public interface ConvertDepartmentMapper {

    Department createDepartmentMapper(DepartmentDTO departmentDTO);
}
