package br.com.amorim.supermarket.repository.establishment.verifymanagerestablishmentrepositorycustom;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.establishment.Establishment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Repository
public class VerifyManagerEstablishmentRepositoryCustomImpl implements
        VerifyManagerEstablishmentRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean existsByManager(Establishment establishment) {
        if (establishment.getId() == null &&
                existsManagerQuery(establishment.getManager())) {
            throw new BusinessRuleException("Gestor já existe");
        }
        return false;
    }

    private boolean existsManagerQuery(String manager) {
        String query = "SELECT CASE WHEN count(p) > 0 " +
                "THEN true ELSE false END FROM Establishment AS p ";
        String conditional = "where";

        if (manager != null) {
            query += conditional + " p.manager = :manager";
        }
        var createQuery = entityManager.createQuery(query, Boolean.class);

        if (manager != null) {
            createQuery.setParameter("manager", manager);
            return createQuery.getSingleResult();
        }
        return false;
    }
}
