package br.com.amorim.supermarket.model.salary;

import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
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
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 30)
    @NotEmpty(message = "Posição/cargo não pode estar vazio")
    private String position;

    @Column(nullable = false, length = 10)
    @NotEmpty(message = "Faixa salarial não pode estar vazio")
    private String salaryRange;

    @Column(name = "gross_salary", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Salário bruto não pode estar vazio")
    private BigDecimal grossSalary;

    @Column(name = "net_salary", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Salário líquido não pode estar vazio")
    private BigDecimal netSalary;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "INSS não pode estar vazio")
    private BigDecimal inss;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "FGTS não pode estar vazio")
    private BigDecimal fgts;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "IRRF não pode estar vazio")
    private BigDecimal irrf;

    @Column(name = "salary_advance", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Adiantamento salarial não pode estar vazio")
    private BigDecimal salaryAdvance;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "Beneficio não pode estar vazio")
    private String benefits;

    @ManyToOne
    @JoinColumn(name = "job_position")
    private JobPosition jobPosition;
}
