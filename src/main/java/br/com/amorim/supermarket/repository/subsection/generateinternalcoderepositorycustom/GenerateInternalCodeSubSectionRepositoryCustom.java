package br.com.amorim.supermarket.repository.subsection.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.subsection.SubSection;

import java.math.BigInteger;

public interface GenerateInternalCodeSubSectionRepositoryCustom {

    BigInteger generateInternalCode(SubSection subSection);
}
