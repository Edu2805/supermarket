package br.com.amorim.supermarket.service.mainsection.generateinternalcode;

import br.com.amorim.supermarket.model.mainsection.MainSection;

import java.math.BigInteger;

public interface GenerateInternalCodeMainSection {

    BigInteger generate (MainSection mainSection);
}
