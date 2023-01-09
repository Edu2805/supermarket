package br.com.amorim.supermarket.model.employee;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.person.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
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

    @Column(name = "register_number", nullable = false)
    @NotNull(message = "{br.com.supermarket.EMPLOYEE_FIELD_REGISTER_NUMBER_IS_NOT_EMPTY}")
    private BigInteger registerNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "job_position")
    private JobPosition jobPosition;

}
