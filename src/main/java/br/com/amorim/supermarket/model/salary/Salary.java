package br.com.amorim.supermarket.model.salary;

import br.com.amorim.supermarket.model.common.CommonIdEntity;
import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "salary")
public class Salary extends CommonIdEntity {

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

    @Column(precision = 10, scale = 2)
    private BigDecimal inss;

    @Column(precision = 10, scale = 2)
    private BigDecimal fgts;

    @Column(precision = 10, scale = 2)
    private BigDecimal irrf;
    @Column(name = "salary_advance", precision = 10, scale = 2)
    private BigDecimal salaryAdvance;

    @Column(length = 100)
    @NotEmpty(message = "{br.com.supermarket.SALARY_FIELD_BENEFITS_IS_NOT_EMPTY}")
    private String benefits;

    @Column(name = "competence_start", nullable = false)
    @NotNull(message = "{br.com.supermarket.SALARY_FIELD_COMPETENCE_START_IS_NOT_EMPTY}")
    private LocalDate competenceStart;

    @Column(name = "final_competence", nullable = false)
    @NotNull(message = "{br.com.supermarket.SALARY_FIELD_FINAL_COMPETENCE_IS_NOT_EMPTY}")
    private LocalDate finalCompetence;

    @OneToMany(mappedBy = "salary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OtherAddition> otherAdditions = new ArrayList<>();

    @OneToMany(mappedBy = "salary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OtherDiscount> otherDiscounts = new ArrayList<>();
}
