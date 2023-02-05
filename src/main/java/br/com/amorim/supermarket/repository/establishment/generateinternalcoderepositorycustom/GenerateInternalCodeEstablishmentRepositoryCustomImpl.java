package br.com.amorim.supermarket.repository.establishment.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.model.establishment.QEstablishment;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@AllArgsConstructor

@Repository
public class GenerateInternalCodeEstablishmentRepositoryCustomImpl implements
        GenerateInternalCodeEstablishmentRepositoryCustom{

    private EntityManager entityManager;
    private static final int START_CODE_ONE = 1;
    private static final int INCREASE_CODE_ONE = 1;

    @Override
    public BigInteger generateInternalCode(Establishment establishment) {
        QEstablishment qEstablishment = QEstablishment.establishment;
        JPAQuery<Establishment> query = new JPAQuery<>(entityManager);
        BigInteger establishmentDataQuery = query.select(qEstablishment.code.max())
                .from(qEstablishment).fetchOne();

        if (establishmentDataQuery != null) {
            return establishmentDataQuery.add(BigInteger.valueOf(INCREASE_CODE_ONE));
        }

        return BigInteger.valueOf(START_CODE_ONE);
    }
}
