package br.com.amorim.supermarket.testutils.generateentitiesunittests.mainsection;

import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.department.DepartmentTest;

import java.math.BigInteger;
import java.util.Random;

public class MainSectionTest {
    public MainSection generateMainsection () {
        DepartmentTest departmentTest = new DepartmentTest();
        Random randomName = new Random();
        Random randomCode = new Random();
        var name = randomName.nextInt(10000, 19999);
        var code = randomCode.nextInt(1, 19999);

        MainSection mainSection = new MainSection();
        mainSection.setName(String.valueOf(name));
        mainSection.setCode(BigInteger.valueOf(code));
        mainSection.setDepartment(departmentTest.generateDepartment());
        return mainSection;
    }
}
