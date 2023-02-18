package br.com.amorim.supermarket.controller.employee.dto;

import br.com.amorim.supermarket.model.employee.Employee;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertEmployeeMapperImpl implements ConvertEmployeeMapper {

    private ModelMapper modelMapper;

    @Override
    public Employee createEmployeeMapper(EmployeeSaveDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }

    @Override
    public Employee updateEmployeeMapper(EmployeeUpdateDTO employeeUpdateDTO) {
        return modelMapper.map(employeeUpdateDTO, Employee.class);
    }
}
