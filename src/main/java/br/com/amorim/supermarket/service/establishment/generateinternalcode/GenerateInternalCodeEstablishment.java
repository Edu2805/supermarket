package br.com.amorim.supermarket.service.establishment.generateinternalcode;

import br.com.amorim.supermarket.model.establishment.Establishment;

import java.math.BigInteger;

public interface GenerateInternalCodeEstablishment {

    BigInteger generate (Establishment establishment);
}
