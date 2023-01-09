package br.com.amorim.supermarket.model.person;

import br.com.amorim.supermarket.common.enums.ScholarityType;
import br.com.amorim.supermarket.model.userdata.UserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 30)
    @NotEmpty(message = "{br.com.supermarket.EMPLOYEE_FIELD_FIRST_NAME_IS_NOT_EMPTY}")
    private String firstName;

    @Column(name = "middle_name", length = 30)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 30)
    @NotEmpty(message = "{br.com.supermarket.EMPLOYEE_FIELD_LAST_NAME_IS_NOT_EMPTY}")
    private String lastName;

    @Column(nullable = false, length = 11, unique = true)
    @NotEmpty(message = "{br.com.supermarket.EMPLOYEE_FIELD_CPF_IS_NOT_EMPTY}")
    @CPF(message = "{br.com.supermarket.EMPLOYEE_FIELD_INVALID_CPF}")
    private String cpf;

    @Column(length = 15)
    private String rg;

    @Column(length = 20)
    private String nationality;

    @Column(length = 20)
    private String naturalness;

    @Column(name = "birth_date", nullable = false)
    @NotNull(message = "{br.com.supermarket.EMPLOYEE_FIELD_BIRTH_DATE_IS_NOT_EMPTY}")
    private LocalDate birthDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "scholarity")
    private ScholarityType scholarity;

    @Column(name = "dependents")
    private Boolean dependents;

    @Column(name = "father_name", length = 50)
    private String fatherName;

    @Column(name = "mother_name", nullable = false, length = 50)
    @NotEmpty(message = "{br.com.supermarket.EMPLOYEE_FIELD_MOTHER_NAME_IS_NOT_EMPTY}")
    private String motherName;

    @Column(name = "email", nullable = false, length = 50)
    @NotEmpty(message = "{br.com.supermarket.EMPLOYEE_FIELD_EMAIL_IS_NOT_EMPTY}")
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_data")
    private UserData userData;

}
