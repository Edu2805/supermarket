package br.com.amorim.supermarket.repository.employee.generateregisternumberemployeerepositorycustom;

import br.com.amorim.supermarket.model.employee.Employee;

import java.math.BigInteger;

public interface GenerateRegisterNumberEmployeeRepositoryCustom {

    BigInteger generateRegisterNumber(Employee employee);
}
