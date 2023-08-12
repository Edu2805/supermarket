package br.com.amorim.supermarket.controller.person.dto;

import br.com.amorim.supermarket.common.enums.ScholarityType;
import br.com.amorim.supermarket.model.attatchment.Attachment;
import br.com.amorim.supermarket.model.userdata.UserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {

    @NotBlank(message = "{br.com.supermarket.PERSON_DTO_FIELD_NAME_IS_NOT_EMPTY}")
    @Size(min = 2, max = 30, message = "{br.com.supermarket.PERSON_DTO_FIELD_NAME_IS_NOT_GREATER_THAN_30_AND_LESS_THEN_2}")
    private String firstName;

    @Size(min = 2, max = 30, message = "{br.com.supermarket.PERSON_DTO_FIELD_MIDDLE_NAME_IS_NOT_GREATER_THAN_30_AND_LESS_THEN_2}")
    private String middleName;

    @NotBlank(message = "{br.com.supermarket.PERSON_DTO_FIELD_LAST_NAME_IS_NOT_EMPTY}")
    @Size(min = 2, max = 30, message = "{br.com.supermarket.PERSON_DTO_FIELD_LAST_NAME_IS_NOT_GREATER_THAN_30_AND_LESS_THEN_2}")
    private String lastName;

    @Size(min = 11, max = 11, message = "{br.com.supermarket.PERSON_DTO_FIELD_CPF_MUST_HAVE_A_MAXIMUM_11_DIGITS}")
    @NotBlank(message = "{br.com.supermarket.PERSON_DTO_FIELD_CPF_CANNOT_BE_EMPTY}")
    @CPF(message = "{br.com.supermarket.PERSON_DTO_FIELD_INVALID_CPF}")
    private String cpf;

    @Size(min = 4, max = 15, message = "{br.com.supermarket.PERSON_DTO_FIELD_RG_IS_NOT_GREATER_THAN_15_AND_LESS_THEN_4}")
    private String rg;

    @Size(min = 3, max = 20, message = "{br.com.supermarket.PERSON_DTO_FIELD_NATIONALITY_IS_NOT_GREATER_THAN_20_AND_LESS_THEN_3}")
    private String nationality;

    @Size(min = 3, max = 20, message = "{br.com.supermarket.PERSON_DTO_FIELD_NATURALNESS_IS_NOT_GREATER_THAN_20_AND_LESS_THEN_3}")
    private String naturalness;

    @NotNull(message = "{br.com.supermarket.PERSON_DTO_FIELD_BIRTH_DATE_CANNOT_BE_EMPTY}")
    private LocalDate birthDate;

    private ScholarityType scholarity;

    private Boolean dependents;

    @Size(min = 2, max = 50, message = "{br.com.supermarket.PERSON_DTO_FIELD_FATHER_NAME_IS_NOT_GREATER_THAN_50_AND_LESS_THEN_2}")
    private String fatherName;

    @NotBlank(message = "{br.com.supermarket.PERSON_DTO_FIELD_MOTHER_NAME_IS_NOT_EMPTY}")
    @Size(min = 2, max = 50, message = "{br.com.supermarket.PERSON_DTO_FIELD_MOTHER_NAME_IS_NOT_GREATER_THAN_50_AND_LESS_THEN_2}")
    private String motherName;

    @NotNull(message = "{br.com.supermarket.PERSON_DTO_FIELD_USER_DATA_IS_NOT_EMPTY}")
    private UserData userData;

    private Attachment personPhoto;
}
