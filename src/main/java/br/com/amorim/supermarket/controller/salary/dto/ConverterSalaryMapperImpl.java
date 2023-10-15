package br.com.amorim.supermarket.controller.salary.dto;

import br.com.amorim.supermarket.controller.salary.dto.request.SalaryDTO;
import br.com.amorim.supermarket.controller.salary.dto.response.SalaryListOutPutDTO;
import br.com.amorim.supermarket.model.salary.Salary;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@Component
public class ConverterSalaryMapperImpl implements ConverterSalaryMapper {

    private ModelMapper modelMapper;
    @Override
    public Salary createOrUpdateSalaryMapper(SalaryDTO salaryDTO) {
        return modelMapper.map(salaryDTO, Salary.class);
    }

    @Override
    public List<SalaryListOutPutDTO> salariesAvailable(List<Salary> salaries) {
        List<SalaryListOutPutDTO> salaryListOutPutDTOList = new ArrayList<>();
        if (!salaries.isEmpty()) {
            salaries.forEach(salary -> salaryListOutPutDTOList.add(modelMapper.map(salary, SalaryListOutPutDTO.class)));
            return salaryListOutPutDTOList;
        }
        return List.of();
    }
}
