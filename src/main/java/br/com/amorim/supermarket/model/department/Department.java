package br.com.amorim.supermarket.model.department;

import br.com.amorim.supermarket.model.common.CommonIdNameAndCodeEntity;
import br.com.amorim.supermarket.model.establishment.Establishment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "department")
public class Department extends CommonIdNameAndCodeEntity {

    @ManyToOne
    @JoinColumn(name = "establishment")
    private Establishment establishment;
}
