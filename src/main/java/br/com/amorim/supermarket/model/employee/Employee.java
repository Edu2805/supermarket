package br.com.amorim.supermarket.model.employee;

import br.com.amorim.supermarket.model.common.CommonIdEntity;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.subsection.SubSection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "employee")
public class Employee extends CommonIdEntity {

    @Column(name = "register_number", nullable = false)
    @NotNull(message = "{br.com.supermarket.EMPLOYEE_FIELD_REGISTER_NUMBER_IS_NOT_EMPTY}")
    private BigInteger registerNumber;

    @Column(name = "full_name", nullable = false, length = 100)
    @NotEmpty(message = "{br.com.supermarket.EMPLOYEE_FIELD_FULL_NAME_IS_NOT_EMPTY}")
    private String fullName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "sub_section")
    private SubSection subSection;

    @ManyToOne
    @JoinColumn(name = "job_position")
    private JobPosition jobPosition;

}
