package br.com.amorim.supermarket.testutils.generateentitiesunittests.department;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.establishment.EstablishmentTest;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class DepartmentTest {

    public Department generateDepartment () {
        EstablishmentTest establishmentTest = new EstablishmentTest();
        Random randomName = new Random();
        Random randomCode = new Random();
        var name = randomName.nextInt(10000, 19999);
        var code = randomCode.nextInt(1, 19999);

        Department department = new Department();
        department.setId(UUID.randomUUID());
        department.setName(String.valueOf(name));
        department.setCode(BigInteger.valueOf(code));
        department.setEstablishment(establishmentTest.generateEstablishment());
        return department;
    }
}
