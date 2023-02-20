package br.com.amorim.supermarket.controller.salary.dto;

import br.com.amorim.supermarket.model.salary.Salary;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConverterSalaryMapperImpl implements ConverterSalaryMapper {

    private ModelMapper modelMapper;
    @Override
    public Salary createOrUpdateSalaryMapper(SalaryDTO salaryDTO) {
        return modelMapper.map(salaryDTO, Salary.class);
    }
}
