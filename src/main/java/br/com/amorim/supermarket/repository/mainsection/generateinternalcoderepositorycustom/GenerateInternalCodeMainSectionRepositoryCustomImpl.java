package br.com.amorim.supermarket.repository.mainsection.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.model.mainsection.QMainSection;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@AllArgsConstructor

@Repository
public class GenerateInternalCodeMainSectionRepositoryCustomImpl implements
        GenerateInternalCodeMainSectionRepositoryCustom {

    private EntityManager entityManager;
    private static final int START_CODE_ONE = 1;
    private static final int INCREASE_CODE_ONE = 1;

    @Override
    public BigInteger generateInternalCode(MainSection mainSection) {
        QMainSection qMainSection = QMainSection.mainSection;
        JPAQuery<MainSection> query = new JPAQuery<>(entityManager);
        BigInteger mainSectionDataQuery = query.select(qMainSection.code.max())
                .from(qMainSection).fetchOne();

        if (mainSectionDataQuery != null) {
            return mainSectionDataQuery.add(BigInteger.valueOf(INCREASE_CODE_ONE));
        }

        return BigInteger.valueOf(START_CODE_ONE);
    }
}
