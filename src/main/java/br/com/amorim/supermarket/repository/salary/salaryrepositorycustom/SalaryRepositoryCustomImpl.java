package br.com.amorim.supermarket.repository.salary.salaryrepositorycustom;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.jobposition.QJobPosition;
import br.com.amorim.supermarket.model.salary.QSalary;
import br.com.amorim.supermarket.model.salary.Salary;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor

@Repository
public class SalaryRepositoryCustomImpl implements SalaryRepositoryCustom {

    private EntityManager entityManager;
    @Override
    public List<Salary> existsSalaryInJobPosition() {
        QSalary qSalary = QSalary.salary;
        QJobPosition qJobPosition = QJobPosition.jobPosition;

        JPAQuery<Salary> querySalary = new JPAQuery<>(entityManager);
        JPAQuery<JobPosition> queryJobPosition = new JPAQuery<>(entityManager);
        return querySalary.select(qSalary).from(qSalary).where(qSalary.id.notIn(
                queryJobPosition.select(qJobPosition.salary.id).from(qJobPosition).fetchAll()
        )).stream().toList();
    }
}
