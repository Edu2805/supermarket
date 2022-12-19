package br.com.amorim.supermarket.model.jobposition;

import br.com.amorim.supermarket.model.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "job_position")
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 30)
    @NotEmpty(message = "Nome não pode estar vazio")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Código não pode estar vazio")
    private BigInteger code;

    @Column(length = 100)
    @NotEmpty(message = "Atribuições não pode estar vazio")
    private String assignments;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;
}
