package br.com.amorim.supermarket.controller.mainsection.dto;

import br.com.amorim.supermarket.model.department.Department;
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
public class MainSectionDTO {

    @NotBlank(message = "{br.com.supermarket.MAIN_SECTION_DTO_FIELD_NAME_IS_NOT_EMPTY}")
    @Size(min = 2, max = 50, message = "{br.com.supermarket.MAIN_SECTION_DTO_FIELD_NAME_IS_NOT_GREATER_THAN_50_AND_LESS_THEN_2}")
    private String name;
    @NotNull(message = "{br.com.supermarket.MAIN_SECTION_DTO_FIELD_ESTABLISHMENT_IS_NOT_EMPTY}")
    private Department department;
}
