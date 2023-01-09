package br.com.amorim.supermarket.model.salary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NotEmpty(message = "{br.com.supermarket.SALARY_FIELD_POSITION_IS_NOT_EMPTY}")
    private String position;

    @Column(nullable = false, length = 10)
    @NotEmpty(message = "{br.com.supermarket.SALARY_FIELD_RANGE_IS_NOT_EMPTY}")
    private String salaryRange;

    @Column(name = "gross_salary", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.SALARY_FIELD_GROSS_SALARY_IS_NOT_EMPTY}")
    private BigDecimal grossSalary;

    @Column(name = "net_salary", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.SALARY_FIELD_NET_SALARY_IS_NOT_EMPTY}")
    private BigDecimal netSalary;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.SALARY_FIELD_INSS_IS_NOT_EMPTY}")
    private BigDecimal inss;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.SALARY_FIELD_FGTS_IS_NOT_EMPTY}")
    private BigDecimal fgts;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.SALARY_FIELD_IRRF_IS_NOT_EMPTY}")
    private BigDecimal irrf;

    @Column(name = "salary_advance", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.SALARY_FIELD_SALARY_ADVANCE_IS_NOT_EMPTY}")
    private BigDecimal salaryAdvance;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "{br.com.supermarket.SALARY_FIELD_BENEFITS_IS_NOT_EMPTY}")
    private String benefits;
}
