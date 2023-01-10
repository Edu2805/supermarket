package br.com.amorim.supermarket.repository.productdata.verifyean13ordun14repositorycustom;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Repository
public class VerifyEan13OrDun14RepositoryCustomImpl implements VerifyEan13OrDun14RepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean existsByEan13OrDun14(ProductData productData) {
        if (existsByEan13(productData.getEan13())) {
            throw new BusinessRuleException(getString(MessagesKeyType.PRODUCT_DATA_EAN13_ALREADY_EXISTS.message));
        }
        if (existsByDun14(productData.getDun14())) {
            throw new BusinessRuleException(getString(MessagesKeyType.PRODUCT_DATA_DUN14_ALREADY_EXISTS.message));
        }
        return false;
    }

    private boolean existsByDun14(String dun14) {

        String query = "SELECT CASE WHEN count(p) > 0 " +
                "THEN true ELSE false END FROM ProductData AS p ";
        String conditional = "where";

        if (dun14 != null) {
            query += conditional + " p.dun14 = :dun14";
        }
        var createQuery = entityManager.createQuery(query, Boolean.class);

        if (dun14 != null) {
            createQuery.setParameter("dun14", dun14);
            return createQuery.getSingleResult();
        }
        return false;
    }

    private boolean existsByEan13(String ean13) {
        String query = "SELECT CASE WHEN count(p) > 0 " +
                "THEN true ELSE false END FROM ProductData AS p ";
        String conditional = "where";

        if (ean13 != null) {
            query += conditional + " p.ean13 = :ean13";
        }
        var createQuery = entityManager.createQuery(query, Boolean.class);

        if (ean13 != null) {
            createQuery.setParameter("ean13", ean13);
            return createQuery.getSingleResult();
        }
        return false;
    }
}
