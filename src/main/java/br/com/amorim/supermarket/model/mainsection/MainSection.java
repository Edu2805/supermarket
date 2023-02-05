package br.com.amorim.supermarket.model.mainsection;

import br.com.amorim.supermarket.model.common.CommonIdNameAndCodeEntity;
import br.com.amorim.supermarket.model.department.Department;
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
@Table(name = "main_section")
public class MainSection extends CommonIdNameAndCodeEntity {

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;
}
