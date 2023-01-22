package br.com.amorim.supermarket.service.establishment.verifymanagerestablishment;

import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.verifymanagerestablishmentrepositorycustom.VerifyManagerEstablishmentRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class VerifyManagerEstablishmentImpl implements VerifyManagerEstablishment {

    private VerifyManagerEstablishmentRepositoryCustom verifyManagerEstablishmentRepositoryCustom;

    @Override
    public boolean verifyManagerEstablishmentRegistred(Establishment establishment) {
        return verifyManagerEstablishmentRepositoryCustom.existsByManager(establishment);
    }
}
