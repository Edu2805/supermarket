package br.com.amorim.supermarket.repository.establishment.verifycnpjrepositorycustom;

import br.com.amorim.supermarket.model.establishment.Establishment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@AllArgsConstructor

@Repository
public class VerifyCnpjEstablishmentRepositoryCustomImpl implements VerifyCnpjEstablishmentRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean existsByCnpj(Establishment establishment) {
        if (establishment.getId() == null &&
            existsSubscriptionNumberQuery(establishment.getCnpj())) {
            return true;
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
