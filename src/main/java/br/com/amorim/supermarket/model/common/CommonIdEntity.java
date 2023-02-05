package br.com.amorim.supermarket.model.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter

@MappedSuperclass
public abstract class CommonIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;
}
