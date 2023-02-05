package br.com.amorim.supermarket.repository.providerproduct.verifymunicipalorstateregistrationrepositorycustom;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@AllArgsConstructor

@Repository
public class VerifyMunicipalOrStateRegistrationProviderProductRepositoryCustomImpl implements
        VerifyMunicipalOrStateRegistrationProviderProductRepositoryCustom {

    private EntityManager entityManager;
    private static final int RETURN_EXISTS_MUNICIPAL_REGISTRATION = 1;
    private static final int RETURN_EXISTS_STATE_REGISTRATION = 2;
    private static final int NEITHER_THE_MUNICIPAL_REGISTRATION_OR_THE_STATE_REGISTRATION_IS_RETURNED = 3;

    @Override
    public int existsByMunicipalOrStateRegistration(ProviderProduct providerProduct) {
        if (providerProduct.getId() == null &&
                existsMunicipalRegistration(providerProduct.getMunicipalRegistration())) {
            return RETURN_EXISTS_MUNICIPAL_REGISTRATION;
        }
        if (providerProduct.getId() == null &&
                existsStateRegistration(providerProduct.getStateRegistration())) {
            return RETURN_EXISTS_STATE_REGISTRATION;
        }
        return NEITHER_THE_MUNICIPAL_REGISTRATION_OR_THE_STATE_REGISTRATION_IS_RETURNED;
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
