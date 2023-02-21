package br.com.amorim.supermarket.controller.otheraddition.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OtherAdditionDTO {

    @NotBlank(message = "{br.com.supermarket.OTHER_ADDITION_DTO_FIELD_ADDITION_NAME_IS_NOT_EMPTY}")
    @Size(min = 2, max = 100, message = "{br.com.supermarket.OTHER_ADDITION_DTO_FIELD_NAME_IS_NOT_GREATER_THAN_100_AND_LESS_THEN_2}")
    private String additionName;
    @PositiveOrZero(message = "{br.com.supermarket.OTHER_ADDITION_DTO_FIELD_VALUE_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.supermarket.OTHER_ADDITION_DTO_FIELD_VALUE_INCORRECT_FORMAT}")
    private BigDecimal value;
}
