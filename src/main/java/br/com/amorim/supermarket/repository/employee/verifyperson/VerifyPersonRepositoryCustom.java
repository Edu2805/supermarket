package br.com.amorim.supermarket.repository.employee.verifyperson;

import br.com.amorim.supermarket.model.employee.Employee;

public interface VerifyPersonRepositoryCustom {

    boolean existsByEmployeePerson(Employee employee);
}
