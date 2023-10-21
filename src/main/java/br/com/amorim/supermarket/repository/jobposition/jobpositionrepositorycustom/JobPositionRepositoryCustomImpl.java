package br.com.amorim.supermarket.repository.jobposition.jobpositionrepositorycustom;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.employee.QEmployee;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.jobposition.QJobPosition;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor

@Repository
public class JobPositionRepositoryCustomImpl implements
        JobPositionRepositoryCustom {

    private EntityManager entityManager;
    private static final int START_CODE_ONE = 1;
    private static final int INCREASE_CODE_ONE = 1;

    @Override
    public BigInteger generateInternalCode(JobPosition jobPosition) {
        QJobPosition qJobPosition = QJobPosition.jobPosition;
        JPAQuery<JobPosition> query = new JPAQuery<>(entityManager);
        BigInteger jobPositionDataQuery = query.select(qJobPosition.code.max())
                .from(qJobPosition).fetchOne();

        if (jobPositionDataQuery != null) {
            return jobPositionDataQuery.add(BigInteger.valueOf(INCREASE_CODE_ONE));
        }

        return BigInteger.valueOf(START_CODE_ONE);
    }

    @Override
    public List<JobPosition> existsJobPositionInEmployee() {
        QJobPosition qJobPosition = QJobPosition.jobPosition;
        QEmployee qEmployee = QEmployee.employee;

        JPAQuery<JobPosition> queryJobPosition = new JPAQuery<>(entityManager);
        JPAQuery<Employee> queryEmployee = new JPAQuery<>(entityManager);
        return queryJobPosition.select(qJobPosition).from(qJobPosition).where(qJobPosition.id.notIn(
                queryEmployee.select(qEmployee.jobPosition.id).from(qEmployee).fetchAll()
        )).stream().toList();

    }
}
