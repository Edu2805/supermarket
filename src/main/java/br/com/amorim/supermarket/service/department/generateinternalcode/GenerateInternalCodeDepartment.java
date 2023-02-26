package br.com.amorim.supermarket.service.department.generateinternalcode;

import br.com.amorim.supermarket.model.department.Department;

import java.math.BigInteger;

public interface GenerateInternalCodeDepartment {

    BigInteger generate (Department department);
}
