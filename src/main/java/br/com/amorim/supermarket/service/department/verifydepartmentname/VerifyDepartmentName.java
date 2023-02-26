package br.com.amorim.supermarket.service.department.verifydepartmentname;

import br.com.amorim.supermarket.model.department.Department;

public interface VerifyDepartmentName {

    boolean existsByDepartmentNameBeforeSave(Department department);
    boolean existsByDepartmentNameBeforeUpdate(Department department);
}
