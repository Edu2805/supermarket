package br.com.amorim.supermarket.repository.providerproduct.verifysubscriptionnumberrepositorycustom;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@AllArgsConstructor

@Repository
public class VerifySubscriptionNumberProviderProductRepositoryCustomImpl implements
        VerifySubscriptionNumberProviderProductRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean existsBySubscriptionNumber(ProviderProduct providerProduct) {
        return providerProduct.getId() == null &&
                existsSubscriptionNumberQuery(providerProduct.getSubscriptionNumber());
    }

    private boolean existsSubscriptionNumberQuery(String subscriptionNumber) {

        String query = "SELECT CASE WHEN count(p) > 0 " +
                "THEN true ELSE false END FROM ProviderProduct AS p ";
        String conditional = "where";

        if (subscriptionNumber != null) {
            query += conditional + " p.subscriptionNumber = :subscriptionNumber";
        }
        var createQuery = entityManager.createQuery(query, Boolean.class);

        if (subscriptionNumber != null) {
            createQuery.setParameter("subscriptionNumber", subscriptionNumber);
            return createQuery.getSingleResult();
        }
        return false;
    }
}
