package br.com.amorim.supermarket.model.financialstatement;

import br.com.amorim.supermarket.model.common.CommonIdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "financial_statement")
public class FinancialStatement extends CommonIdEntity {

    @Column(name = "revenues", precision = 10, scale = 2)
    private BigDecimal revenues;
    @Column(name = "revenue_by_department", precision = 10, scale = 2)
    private BigDecimal revenueByDepartment;
    @Column(name = "revenue_by_mainSection", precision = 10, scale = 2)
    private BigDecimal revenueByMainSection;
    @Column(name = "revenue_by_subsection", precision = 10, scale = 2)
    private BigDecimal revenueBySubSection;
    @Column(name = "expenses", precision = 10, scale = 2)
    private BigDecimal expenses;
    @Column(name = "expenses_by_department", precision = 10, scale = 2)
    private BigDecimal expensesByDepartment;
    @Column(name = "expenses_by_mainsection", precision = 10, scale = 2)
    private BigDecimal expensesByMainSection;
    @Column(name = "expenses_by_subsection", precision = 10, scale = 2)
    private BigDecimal expensesBySubSection;
    @Column(name = "competence_start")
    private LocalDate competenceStart;
    @Column(name = "end_competence")
    private LocalDate endCompetence;

}
