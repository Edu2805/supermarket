package br.com.amorim.supermarket.testutils.generateentities.department;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.testutils.generateentities.establishment.EstablishmentTest;

import java.math.BigInteger;
import static java.util.UUID.randomUUID;

public class DepartmentTest {

    public static final java.util.UUID UUID = randomUUID();
    public static final String NAME = "Departamento teste";

    public Department generateDepartment () {
        Department department = new Department();
        EstablishmentTest establishmentTest = new EstablishmentTest();
        department.setId(UUID);
        department.setName(NAME);
        department.setCode(BigInteger.ONE);
        department.setEstablishment(establishmentTest.generateEstablishment());
        return department;
    }
}
