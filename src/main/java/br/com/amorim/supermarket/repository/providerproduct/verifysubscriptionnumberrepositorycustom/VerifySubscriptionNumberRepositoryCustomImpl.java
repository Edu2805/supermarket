package br.com.amorim.supermarket.repository.providerproduct.verifysubscriptionnumberrepositorycustom;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Repository
public class VerifySubscriptionNumberRepositoryCustomImpl implements VerifySubscriptionNumberRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean existsBySubscriptionNumber(ProviderProduct providerProduct) {
        if (existsSubscriptionNumberQuery(providerProduct.getSubscriptionNumber())) {
            throw new BusinessRuleException(getString(MessagesKeyType.PROVIDER_PRODUCT_SUBSCRIPTION_NUMBER_ALREADY_EXISTS.message));
        }
        return false;
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
