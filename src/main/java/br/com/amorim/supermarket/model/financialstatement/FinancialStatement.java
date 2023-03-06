package br.com.amorim.supermarket.model.financialstatement;

import br.com.amorim.supermarket.model.common.CommonIdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "financial_statement")
public class FinancialStatement extends CommonIdEntity {

    @Column(name = "revenues", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.FINANCIAL_STATEMENT_FIELD_REVENUES_IS_NOT_EMPTY}")
    private BigDecimal revenues;
    @Column(name = "revenue_by_department", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.FINANCIAL_STATEMENT_FIELD_REVENUE_BY_DEPARTMENT_IS_NOT_EMPTY}")
    private BigDecimal revenueByDepartment;
    @Column(name = "revenue_by_mainSection", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.FINANCIAL_STATEMENT_FIELD_REVENUE_BY_MAIN_SECTION_IS_NOT_EMPTY}")
    private BigDecimal revenueByMainSection;
    @Column(name = "revenue_by_subsection", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.FINANCIAL_STATEMENT_FIELD_REVENUE_BY_SUB_SECTION_IS_NOT_EMPTY}")
    private BigDecimal revenueBySubSection;
    @Column(name = "expenses", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.FINANCIAL_STATEMENT_FIELD_EXPENSES_IS_NOT_EMPTY}")
    private BigDecimal expenses;
    @Column(name = "expenses_by_department", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.FINANCIAL_STATEMENT_FIELD_EXPENSES_BY_DEPARTMENT_IS_NOT_EMPTY}")
    private BigDecimal expensesByDepartment;
    @Column(name = "expenses_by_mainsection", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.FINANCIAL_STATEMENT_FIELD_EXPENSES_BY_MAIN_SECTION_IS_NOT_EMPTY}")
    private BigDecimal expensesByMainSection;
    @Column(name = "expenses_by_subsection", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.FINANCIAL_STATEMENT_FIELD_EXPENSES_BY_SUB_SECTION_IS_NOT_EMPTY}")
    private BigDecimal expensesBySubSection;
    @Column(name = "competence_start")
    @NotNull(message = "{br.com.supermarket.FINANCIAL_STATEMENT_FIELD_COMPETENCE_START_IS_NOT_EMPTY}")
    private LocalDate competenceStart;
    @Column(name = "end_competence")
    @NotNull(message = "{br.com.supermarket.FINANCIAL_STATEMENT_FIELD_END_COMPETENCE_IS_NOT_EMPTY}")
    private LocalDate endCompetence;

}
