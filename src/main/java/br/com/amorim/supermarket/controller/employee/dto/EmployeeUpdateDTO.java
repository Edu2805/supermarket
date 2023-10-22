package br.com.amorim.supermarket.controller.employee.dto;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.subsection.SubSection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeUpdateDTO {

    @NotNull(message = "{br.com.supermarket.EMPLOYEE_DTO_FIELD_PERSON_IS_NOT_EMPTY}")
    private Person person;
    @NotNull(message = "{br.com.supermarket.EMPLOYEE_DTO_FIELD_SUB_SECTION_IS_NOT_EMPTY}")
    private SubSection subSection;
    @NotNull(message = "{br.com.supermarket.EMPLOYEE_DTO_FIELD_JOB_POSITION_IS_NOT_EMPTY}")
    private JobPosition jobPosition;
}
