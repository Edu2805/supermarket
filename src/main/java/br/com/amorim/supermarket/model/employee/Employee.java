package br.com.amorim.supermarket.model.employee;

import br.com.amorim.supermarket.common.enums.Scholarity;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.userdata.UserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 30)
    @NotEmpty(message = "Primeiro nome não pode estar vazio")
    private String firstName;

    @Column(name = "middle_name", length = 30)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 30)
    @NotEmpty(message = "Último nome não pode estar vazio")
    private String lastName;

    @Column(name = "register_number", nullable = false)
    @NotNull(message = "Registro do funcionário não pode estar vazio")
    private BigInteger registerNumber;

    @Column(nullable = false, length = 11, unique = true)
    @NotEmpty(message = "CNPJ não pode estar vazio")
    @UniqueElements(message = "Já existe um colaborador com esse CPF")
    private String cpf;

    @Column(length = 15)
    private String rg;

    @Column(nullable = false, length = 20)
    @NotEmpty(message = "Nacionalidade nome não pode estar vazio")
    private String nationality;

    @Column(nullable = false, length = 20)
    @NotEmpty(message = "Naturalidade nome não pode estar vazio")
    private String naturalness;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "scholarity", nullable = false)
    private Scholarity scholarity;

    @Column(name = "dependents", nullable = false)
    private Boolean dependents;

    @Column(name = "father_name", length = 50)
    private String fatherName;

    @Column(name = "mother_name", nullable = false, length = 50)
    @NotEmpty(message = "Nome da mãe não pode estar vazio")
    private String motherName;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "job_position")
    private JobPosition jobPosition;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_data")
    private UserData userData;
}
