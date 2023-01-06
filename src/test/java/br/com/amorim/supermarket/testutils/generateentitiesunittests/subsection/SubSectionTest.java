package br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection;

import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.mainsection.MainSectionTest;

import java.math.BigInteger;
import static java.util.UUID.randomUUID;

public class SubSectionTest {

    public static final java.util.UUID UUID = randomUUID();
    public static final String NAME = "Sub Seção Teste";

    public SubSection generateSubsection () {
        SubSection subSection = new SubSection();
        MainSectionTest mainSectionTest = new MainSectionTest();
        subSection.setId(UUID);
        subSection.setName(NAME);
        subSection.setCode(BigInteger.ONE);
        subSection.setMainSection(mainSectionTest.generateMainsection());
        return subSection;
    }
}
