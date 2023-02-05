package br.com.amorim.supermarket.repository.providerproduct.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.model.providerproduct.QProviderProduct;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@AllArgsConstructor

@Repository
public class GenerateInternalCodeProviderProductRepositoryCustomImpl implements
        GenerateInternalCodeProviderProductRepositoryCustom {

    private EntityManager entityManager;
    private static final int START_CODE_ONE = 1;
    private static final int INCREASE_CODE_ONE = 1;

    @Override
    public BigInteger generateInternalCode(ProviderProduct providerProduct) {
        QProviderProduct qProviderProduct = QProviderProduct.providerProduct;
        JPAQuery<ProviderProduct> query = new JPAQuery<>(entityManager);
        BigInteger providerProductDataQuery = query.select(qProviderProduct.code.max())
                .from(qProviderProduct).fetchOne();

        if (providerProductDataQuery != null) {
            return providerProductDataQuery.add(BigInteger.valueOf(INCREASE_CODE_ONE));
        }

        return BigInteger.valueOf(START_CODE_ONE);
    }
}
