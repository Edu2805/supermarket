package br.com.amorim.supermarket.repository.establishment.verifymunicipalorstateregistrationrepositorycustom;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.establishment.Establishment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Repository
public class VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustomImpl implements
        VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean existsByMunicipalOrStateRegistration(Establishment establishment) {
        if (establishment.getId() == null &&
                existsMunicipalRegistration(establishment.getMunicipalRegistration())) {
            throw new BusinessRuleException(
                    getString(MessagesKeyType.ESTABLISHMENT_MUNICIPAL_REGISTER_ALREADY_EXISTS.message));
        }
        if (establishment.getId() == null &&
                existsStateRegistration(establishment.getStateRegistration())) {
            throw new BusinessRuleException(
                    getString(MessagesKeyType.ESTABLISHMENT_STATE_REGISTER_ALREADY_EXISTS.message));
        }
        return false;
    }

    private boolean existsMunicipalRegistration(String municipalRegistration) {
        String query = "SELECT CASE WHEN count(p) > 0 " +
                "THEN true ELSE false END FROM Establishment AS p ";
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
                "THEN true ELSE false END FROM Establishment AS p ";
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
