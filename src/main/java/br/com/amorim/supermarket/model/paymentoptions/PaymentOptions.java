package br.com.amorim.supermarket.model.paymentoptions;

import br.com.amorim.supermarket.model.common.CommonIdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "payment_options")
@NoArgsConstructor
public class PaymentOptions extends CommonIdEntity {

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "{br.com.supermarket.MODEL_COMMON_FIELD_NAME_IS_NOT_EMPTY}")
    protected String name;
}
