package br.com.amorim.supermarket.repository.mainsection.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.mainsection.MainSection;

import java.math.BigInteger;

public interface GenerateInternalCodeMainSectionRepositoryCustom {

    BigInteger generateInternalCode(MainSection mainSection);
}
