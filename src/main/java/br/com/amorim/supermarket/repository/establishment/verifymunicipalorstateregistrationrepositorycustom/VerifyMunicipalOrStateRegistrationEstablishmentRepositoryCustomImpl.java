package br.com.amorim.supermarket.repository.establishment.verifymunicipalorstateregistrationrepositorycustom;

import br.com.amorim.supermarket.model.establishment.Establishment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@AllArgsConstructor

@Repository
public class VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustomImpl implements
        VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public int existsByMunicipalOrStateRegistration(Establishment establishment) {
        if (establishment.getId() == null &&
                existsMunicipalRegistration(establishment.getMunicipalRegistration())) {
            return 1;
        }
        if (establishment.getId() == null &&
                existsStateRegistration(establishment.getStateRegistration())) {
            return 2;
        }
        return 3;
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
