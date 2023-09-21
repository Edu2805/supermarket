package br.com.amorim.supermarket.controller.salary.dto;

import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalaryDTO {

    @NotBlank(message = "{br.com.supermarket.SALARY_DTO_FIELD_POSITION_IS_NOT_EMPTY}")
    @Size(min = 2, max = 30, message = "{br.com.supermarket.SALARY_DTO_FIELD_POSITION_IS_NOT_GREATER_THAN_30_AND_LESS_THEN_2}")
    private String position;

    @NotBlank(message = "{br.com.supermarket.SALARY_DTO_FIELD_SALARY_RANGE_IS_NOT_EMPTY}")
    @Size(min = 2, max = 10, message = "{br.com.supermarket.SALARY_DTO_FIELD_SALARY_RANGE_IS_NOT_GREATER_THAN_10_AND_LESS_THEN_2}")
    private String salaryRange;

    @NotNull(message = "{br.com.supermarket.SALARY_DTO_FIELD_GROSS_SALARY_IS_NOT_NULL}")
    @Positive(message = "{br.com.supermarket.SALARY_DTO_FIELD_GROSS_SALARY_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.supermarket.SALARY_DTO_FIELD_GROSS_SALARY_INCORRECT_FORMAT}")
    private BigDecimal grossSalary;

    @PositiveOrZero(message = "{br.com.supermarket.SALARY_DTO_FIELD_SALARY_ADVANCE_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.supermarket.SALARY_DTO_FIELD_SALARY_ADVANCE_INCORRECT_FORMAT}")
    private BigDecimal salaryAdvance;

    @NotBlank(message = "{br.com.supermarket.SALARY_DTO_FIELD_BENEFITS_IS_NOT_EMPTY}")
    @Size(min = 2, max = 100, message = "{br.com.supermarket.SALARY_DTO_FIELD_BENEFITS_IS_NOT_GREATER_THAN_100_AND_LESS_THEN_2}")
    private String benefits;

    @NotNull(message = "{br.com.supermarket.SALARY_DTO_FIELD_COMPETENCE_START_IS_NOT_EMPTY}")
    private LocalDate competenceStart;

    @NotNull(message = "{br.com.supermarket.SALARY_DTO_FIELD_FINAL_COMPETENCE_IS_NOT_EMPTY}")
    private LocalDate finalCompetence;

    private List<OtherAddition> otherAdditions = new ArrayList<>();

    private List<OtherDiscount> otherDiscounts = new ArrayList<>();
}
