package br.com.amorim.supermarket.service.establishment.generateinternalcode;

import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.generateinternalcoderepositorycustom.GenerateInternalCodeEstablishmentRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@AllArgsConstructor

@Component
public class GenerateInternalCodeEstablishmentImpl implements GenerateInternalCodeEstablishment{

    private GenerateInternalCodeEstablishmentRepositoryCustom generateInternalCodeEstablishmentRepositoryCustom;

    @Override
    public BigInteger generate(Establishment establishment) {
        return generateInternalCodeEstablishmentRepositoryCustom
                .generateInternalCode(establishment);
    }
}
