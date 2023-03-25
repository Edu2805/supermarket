package br.com.amorim.supermarket.model.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter

@MappedSuperclass
public abstract class CommonIdAndNameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "{br.com.supermarket.MODEL_COMMON_FIELD_NAME_IS_NOT_EMPTY}")
    protected String name;
}
