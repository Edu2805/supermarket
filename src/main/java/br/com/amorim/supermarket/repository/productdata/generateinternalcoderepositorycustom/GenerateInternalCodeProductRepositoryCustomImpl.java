package br.com.amorim.supermarket.repository.productdata.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.model.productdata.QProductData;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@AllArgsConstructor

@Repository
public class GenerateInternalCodeProductRepositoryCustomImpl implements GenerateInternalCodeProductRepositoryCustom {

    private EntityManager entityManager;
    private static final int START_CODE_ONE = 1;
    private static final int INCREASE_CODE_ONE = 1;

    @Override
    public BigInteger generateInternalCode(ProductData productData) {
        QProductData qProductData = QProductData.productData;
        JPAQuery<ProductData> query = new JPAQuery<>(entityManager);
        BigInteger productDataQuery = query.select(qProductData.code.max())
                .from(qProductData).fetchOne();

        if (productDataQuery != null) {
            return productDataQuery.add(BigInteger.valueOf(INCREASE_CODE_ONE));
        }

        return BigInteger.valueOf(START_CODE_ONE);
    }
}
