package br.com.amorim.supermarket.service.subsection.generateinternalcode;

import br.com.amorim.supermarket.model.subsection.SubSection;

import java.math.BigInteger;

public interface GenerateInternalCodeSubSection {

    BigInteger generate (SubSection subSection);
}
