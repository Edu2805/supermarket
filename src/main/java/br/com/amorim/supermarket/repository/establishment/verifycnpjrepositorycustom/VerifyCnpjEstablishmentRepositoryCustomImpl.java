package br.com.amorim.supermarket.repository.establishment.verifycnpjrepositorycustom;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.establishment.Establishment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Repository
public class VerifyCnpjEstablishmentRepositoryCustomImpl implements VerifyCnpjEstablishmentRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean existsByCnpj(Establishment establishment) {
        if (establishment.getId() == null &&
            existsSubscriptionNumberQuery(establishment.getCnpj())) {
            throw new BusinessRuleException(getString(
                    MessagesKeyType.ESTABLISHMENT_CNPJ_ALREADY_EXISTS.message));
        }
        return false;
    }

    private boolean existsSubscriptionNumberQuery(String cnpj) {
        String query = "SELECT CASE WHEN count(p) > 0 " +
                "THEN true ELSE false END FROM Establishment AS p ";
        String conditional = "where";

        if (cnpj != null) {
            query += conditional + " p.cnpj = :cnpj";
        }
        var createQuery = entityManager.createQuery(query, Boolean.class);

        if (cnpj != null) {
            createQuery.setParameter("cnpj", cnpj);
            return createQuery.getSingleResult();
        }
        return false;
    }
}
