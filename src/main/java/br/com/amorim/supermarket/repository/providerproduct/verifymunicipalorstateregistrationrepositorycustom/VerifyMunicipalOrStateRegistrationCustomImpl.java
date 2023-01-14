package br.com.amorim.supermarket.repository.providerproduct.verifymunicipalorstateregistrationrepositorycustom;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Repository
public class VerifyMunicipalOrStateRegistrationCustomImpl implements VerifyMunicipalOrStateRegistrationCustom{

    private EntityManager entityManager;

    @Override
    public boolean existsByMunicipalOrStateRegistration(ProviderProduct providerProduct) {
        if (existsMunicipalRegistration(providerProduct.getMunicipalRegistration())) {
            throw new BusinessRuleException(getString(MessagesKeyType.PROVIDER_PRODUCT_MUNICIPAL_REGISTER_ALREADY_EXISTS.message));
        }
        if (existsStateRegistration(providerProduct.getStateRegistration())) {
            throw new BusinessRuleException(getString(MessagesKeyType.PROVIDER_PRODUCT_STATE_REGISTER_ALREADY_EXISTS.message));
        }
        return false;
    }

    private boolean existsMunicipalRegistration(String municipalRegistration) {

        String query = "SELECT CASE WHEN count(p) > 0 " +
                "THEN true ELSE false END FROM ProviderProduct AS p ";
        String conditional = "where";

        if (municipalRegistration != null) {
            query += conditional + " p.municipalRegistration = :municipalRegistration";
        }
        var createQuery = entityManager.createQuery(query, Boolean.class);

        if (municipalRegistration != null) {
            createQuery.setParameter("municipalRegistration", municipalRegistration);
            return createQuery.getSingleResult();
        }
        return false;
    }

    private boolean existsStateRegistration (String stateRegistration) {
        String query = "SELECT CASE WHEN count(p) > 0 " +
                "THEN true ELSE false END FROM ProviderProduct AS p ";
        String conditional = "where";

        if (stateRegistration != null) {
            query += conditional + " p.stateRegistration = :stateRegistration";
        }
        var createQuery = entityManager.createQuery(query, Boolean.class);

        if (stateRegistration != null) {
            createQuery.setParameter("stateRegistration", stateRegistration);
            return createQuery.getSingleResult();
        }
        return false;
    }
}