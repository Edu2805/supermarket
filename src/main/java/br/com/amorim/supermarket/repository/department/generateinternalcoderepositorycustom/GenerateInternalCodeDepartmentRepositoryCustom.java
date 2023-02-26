package br.com.amorim.supermarket.repository.department.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.department.Department;

import java.math.BigInteger;

public interface GenerateInternalCodeDepartmentRepositoryCustom {

    BigInteger generateInternalCode(Department department);
}
