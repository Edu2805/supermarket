package br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection;

import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.mainsection.MainSectionTest;

import java.math.BigInteger;
import java.util.Random;

public class SubSectionTest {
    public SubSection generateSubsection () {
        MainSectionTest mainSectionTest = new MainSectionTest();
        Random randomName = new Random();
        Random randomCode = new Random();
        var name = randomName.nextInt(10000, 19999);
        var code = randomCode.nextInt(1, 19999);

        SubSection subSection = new SubSection();
        subSection.setName(String.valueOf(name));
        subSection.setCode(BigInteger.valueOf(code));
        subSection.setMainSection(mainSectionTest.generateMainsection());
        return subSection;
    }
}
