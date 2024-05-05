package br.com.amorim.supermarket.testutils.generateentitiesunittests.establishment;

import br.com.amorim.supermarket.model.attatchment.Attachment;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.testutils.generatedocument.GenerateCNPJ;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class EstablishmentTest {

    public Establishment generateEstablishment () {
        Random randomName = new Random();
        Random randomCode = new Random();
        Random randomMunicipalRegistration = new Random();
        Random randomStateRegistration = new Random();
        Random randomManager = new Random();
        GenerateCNPJ generateCNPJ = new GenerateCNPJ();
        Attachment attachment = new Attachment();
        attachment.setId(UUID.randomUUID());
        var name = randomName.nextInt(10000, 19999);
        var code = randomCode.nextInt(1, 19999);
        var municipalRegistration = randomMunicipalRegistration.nextInt(1, 1999);
        var stateRegistration = randomStateRegistration.nextInt(1, 1999);
        var manager = randomManager.nextInt(10000, 19999);

        Establishment establishment = new Establishment();
        establishment.setId(UUID.randomUUID());
        establishment.setName(String.valueOf(name));
        establishment.setCode(BigInteger.valueOf(code));
        establishment.setCnpj(generateCNPJ.cnpj(false));
        establishment.setManager(String.valueOf(manager));
        establishment.setAddress("Avenida Teste, 666, Cidade dos Testes");
        establishment.setMunicipalRegistration(String.valueOf(municipalRegistration));
        establishment.setStateRegistration(String.valueOf(stateRegistration));
        establishment.setPhone("4833333333");
        establishment.setEstablismentLogo(attachment);
        return establishment;
    }
}
