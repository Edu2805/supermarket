package br.com.amorim.supermarket.controller.department.dto;

import br.com.amorim.supermarket.model.department.Department;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertDepartmentMapperImpl implements ConvertDepartmentMapper {

    private ModelMapper modelMapper;

    @Override
    public Department createDepartmentMapper(DepartmentDTO departmentDTO) {
        return modelMapper.map(departmentDTO, Department.class);
    }
}
