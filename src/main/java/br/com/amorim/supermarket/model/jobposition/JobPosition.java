package br.com.amorim.supermarket.model.jobposition;

import br.com.amorim.supermarket.model.common.CommonIdNameAndCodeEntity;
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "job_position")
public class JobPosition extends CommonIdNameAndCodeEntity {

    @Column(length = 100)
    @NotEmpty(message = "{br.com.supermarket.JOB_POSITION_FIELD_ASSIGNMENTS_IS_NOT_EMPTY}")
    private String assignments;

    @ManyToOne
    @JoinColumn(name = "salary")
    private Salary salary;
}
