package br.com.amorim.supermarket.controller.employee.dto.mapper;

import br.com.amorim.supermarket.controller.employee.dto.EmployeeDetailsOutputDTO;
import br.com.amorim.supermarket.controller.person.dto.response.ConvertPersonScholarityTypeStringDTO;
import br.com.amorim.supermarket.model.employee.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    private ConvertPersonScholarityTypeStringDTO convertPersonScholarityTypeStringDTO;

    @Override
    public EmployeeDetailsOutputDTO toEmployeeDetailsOutputDTO(Employee employee) {
        var employeeDetailsOutputDTO = new EmployeeDetailsOutputDTO();
        employeeDetailsOutputDTO.setId(employee.getId());
        employeeDetailsOutputDTO.setFullName(employee.getPerson().getFirstName() + " "
                + employee.getPerson().getMiddleName() + " " + employee.getPerson().getLastName());
        employeeDetailsOutputDTO.setJobPosition(employee.getJobPosition());
        employeeDetailsOutputDTO.setPersonScholarityTypeStringDTO(convertPersonScholarityTypeStringDTO.mapper(employee.getPerson()));
        employeeDetailsOutputDTO.setRegisterNumber(employee.getRegisterNumber());
        employeeDetailsOutputDTO.setSubSection(employee.getSubSection());
        return employeeDetailsOutputDTO;
    }

}
