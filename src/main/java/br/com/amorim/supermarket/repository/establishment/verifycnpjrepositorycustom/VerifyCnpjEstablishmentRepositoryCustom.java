package br.com.amorim.supermarket.repository.establishment.verifycnpjrepositorycustom;

import br.com.amorim.supermarket.model.establishment.Establishment;

public interface VerifyCnpjEstablishmentRepositoryCustom {

    boolean existsByCnpj (Establishment establishment);
}
