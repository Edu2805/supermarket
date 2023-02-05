package br.com.amorim.supermarket.model.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter

@MappedSuperclass
public abstract class CommonIdNameAndCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "{br.com.supermarket.MODEL_COMMON_FIELD_NAME_IS_NOT_EMPTY}")
    protected String name;

    @Column(nullable = false)
    @NotNull(message = "{br.com.supermarket.MODEL_COMMON_FIELD_INTERNAL_CODE_IS_NOT_EMPTY}")
    protected BigInteger code;
}
