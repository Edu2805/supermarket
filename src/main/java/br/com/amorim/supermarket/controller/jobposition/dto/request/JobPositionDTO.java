package br.com.amorim.supermarket.controller.jobposition.dto.request;

import br.com.amorim.supermarket.model.salary.Salary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JobPositionDTO {

    @NotBlank(message = "{br.com.supermarket.JOB_POSITION_DTO_FIELD_ASSIGNMENTS_IS_NOT_EMPTY}")
    @Size(max = 100, message = "{br.com.supermarket.JOB_POSITION_DTO_FIELD_ASSIGNMENTS_CANNOT_BE_GREATER_THAN_100}")
    private String assignments;

    @NotNull(message = "{br.com.supermarket.JOB_POSITION_DTO_FIELD_SALARY_IS_NOT_NULL}")
    private Salary salary;
}
