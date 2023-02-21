package br.com.amorim.supermarket.model.otheraddition;

import br.com.amorim.supermarket.model.common.CommonIdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "other_addition")
public class OtherAddition extends CommonIdEntity {

    @Column(name = "addition_name", nullable = false, length = 100)
    @NotEmpty(message = "{br.com.supermarket.OTHER_ADDITION_FIELD_DISCOUNT_NAME_IS_NOT_EMPTY}")
    private String additionName;
    @Column(name = "addition_value", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.OTHER_ADDITION_FIELD_VALUE_IS_NOT_EMPTY}")
    private BigDecimal additionValue;
}
