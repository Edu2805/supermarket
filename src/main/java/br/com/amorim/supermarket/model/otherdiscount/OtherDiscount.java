package br.com.amorim.supermarket.model.otherdiscount;

import br.com.amorim.supermarket.model.common.CommonIdEntity;
import br.com.amorim.supermarket.model.salary.Salary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "other_discount")
public class OtherDiscount extends CommonIdEntity {

    @Column(name = "discount_name", nullable = false, length = 100)
    @NotEmpty(message = "{br.com.supermarket.SALARY_FIELD_POSITION_IS_NOT_EMPTY}")
    private String discountName;
    @Column(name = "value", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.SALARY_FIELD_NET_SALARY_IS_NOT_EMPTY}")
    private BigDecimal value;
}
