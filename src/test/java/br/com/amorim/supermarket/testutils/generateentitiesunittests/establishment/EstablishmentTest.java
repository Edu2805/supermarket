package br.com.amorim.supermarket.testutils.generateentitiesunittests.establishment;

import br.com.amorim.supermarket.model.establishment.Establishment;

import java.math.BigInteger;
import static java.util.UUID.randomUUID;

public class EstablishmentTest {

    public static final java.util.UUID UUID = randomUUID();
    public static final String NAME = "Loja teste";

    public Establishment generateEstablishment () {
        Establishment establishment = new Establishment();
        establishment.setId(UUID);
        establishment.setName(NAME);
        establishment.setCode(BigInteger.ONE);
        establishment.setCnpj("98765432000199");
        establishment.setManager("Gerente Teste da Silva");
        establishment.setAddress("Avenida Teste, 666, Cidade dos Testes");
        establishment.setMunicipalRegistration("98765-A");
        establishment.setStateRegistration("123456-B");
        establishment.setPhone("4833333333");
        return establishment;
    }
}
