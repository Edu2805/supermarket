package br.com.amorim.supermarket.repository.productdata.verifyean13ordun14repositorycustom;

import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.verifyean13ordun14repositorycustom.existsbyean13OrDun14RepositoryCustom.ExistsByDun14RepositoryCustom;
import br.com.amorim.supermarket.repository.productdata.verifyean13ordun14repositorycustom.existsbyean13OrDun14RepositoryCustom.ExistsByEan13RepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@AllArgsConstructor

@Repository
public class VerifyEan13OrDun14RepositoryCustomImpl implements
        VerifyEan13OrDun14RepositoryCustom,
        ExistsByEan13RepositoryCustom,
        ExistsByDun14RepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean existsByEan13OrDun14(ProductData productData) {
        if (existsByEan13(productData.getEan13())) {
            throw new BusinessRuleException(
                        "Já existe um produto cadastrado com o mesmo EAN 13.");
        }
        if (existsByDun14(productData.getDun14())) {
            throw new BusinessRuleException(
                    "Já existe um produto cadastrado com o mesmo DUN 14.");
        }
        return false;
    }

    @Override
    public boolean existsByDun14(String dun14) {

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

    @Override
    public boolean existsByEan13(String ean13) {
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
