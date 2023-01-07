package br.com.amorim.supermarket.repository.productdata.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.model.productdata.QProductData;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.math.BigInteger;

/**
 * Classe que gera o código interno do produto
 */
@AllArgsConstructor

@Component
public class GenerateInternalCodeRepositoryCustomImpl implements GenerateInternalCodeRepositoryCustom {

    private EntityManager entityManager;

    /**
     * Classe que detém da lógica que gera um novo código interno com base
     * em um já existente na base de dados ou não
     * @param productData produto cadastrado
     * @return código interno do produto cadastrado
     */
    @Override
    public BigInteger generateInternalCode(ProductData productData) {
        QProductData qProductData = QProductData.productData;
        JPAQuery<ProductData> query = new JPAQuery<>(entityManager);
        BigInteger productDataQuery = query.select(qProductData.internalCode.max())
                .from(qProductData).fetchOne();

        if (productDataQuery != null) {
            return productDataQuery.add(BigInteger.valueOf(1));
        }

        return BigInteger.valueOf(1);
    }
}
