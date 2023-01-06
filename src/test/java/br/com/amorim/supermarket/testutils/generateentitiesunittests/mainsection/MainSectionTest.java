package br.com.amorim.supermarket.testutils.generateentitiesunittests.mainsection;

import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.department.DepartmentTest;

import java.math.BigInteger;
import static java.util.UUID.randomUUID;

public class MainSectionTest {

    public static final java.util.UUID UUID = randomUUID();
    public static final String NAME = "Seção principal teste";

    public MainSection generateMainsection () {
        MainSection mainSection = new MainSection();
        DepartmentTest departmentTest = new DepartmentTest();
        mainSection.setId(UUID);
        mainSection.setName(NAME);
        mainSection.setCode(BigInteger.ONE);
        mainSection.setDepartment(departmentTest.generateDepartment());
        return mainSection;
    }
}
