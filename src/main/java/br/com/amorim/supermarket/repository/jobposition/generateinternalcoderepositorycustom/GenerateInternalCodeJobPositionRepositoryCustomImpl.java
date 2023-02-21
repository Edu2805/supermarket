package br.com.amorim.supermarket.repository.jobposition.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.jobposition.QJobPosition;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@AllArgsConstructor

@Repository
public class GenerateInternalCodeJobPositionRepositoryCustomImpl implements
        GenerateInternalCodeJobPositionRepositoryCustom {

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
}
