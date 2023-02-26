package br.com.amorim.supermarket.repository.subsection.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.subsection.QSubSection;
import br.com.amorim.supermarket.model.subsection.SubSection;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@AllArgsConstructor

@Repository
public class GenerateInternalCodeSubSectionRepositoryCustomImpl implements
        GenerateInternalCodeSubSectionRepositoryCustom {

    private EntityManager entityManager;
    private static final int START_CODE_ONE = 1;
    private static final int INCREASE_CODE_ONE = 1;

    @Override
    public BigInteger generateInternalCode(SubSection subSection) {
        QSubSection qSubSection = QSubSection.subSection;
        JPAQuery<SubSection> query = new JPAQuery<>(entityManager);
        BigInteger subSectionDataQuery = query.select(qSubSection.code.max())
                .from(qSubSection).fetchOne();

        if (subSectionDataQuery != null) {
            return subSectionDataQuery.add(BigInteger.valueOf(INCREASE_CODE_ONE));
        }

        return BigInteger.valueOf(START_CODE_ONE);
    }
}
