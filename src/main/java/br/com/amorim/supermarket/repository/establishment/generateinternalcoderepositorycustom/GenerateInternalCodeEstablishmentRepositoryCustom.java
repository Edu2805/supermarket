package br.com.amorim.supermarket.repository.establishment.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.establishment.Establishment;

import java.math.BigInteger;

public interface GenerateInternalCodeEstablishmentRepositoryCustom {

    BigInteger generateInternalCode(Establishment establishment);
}
