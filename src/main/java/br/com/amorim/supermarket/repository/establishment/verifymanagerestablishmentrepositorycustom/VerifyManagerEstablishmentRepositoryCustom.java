package br.com.amorim.supermarket.repository.establishment.verifymanagerestablishmentrepositorycustom;

import br.com.amorim.supermarket.model.establishment.Establishment;

public interface VerifyManagerEstablishmentRepositoryCustom {

    boolean existsByManager (Establishment establishment);
}
