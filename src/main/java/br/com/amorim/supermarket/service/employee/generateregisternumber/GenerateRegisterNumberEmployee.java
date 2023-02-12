package br.com.amorim.supermarket.service.employee.generateregisternumber;

import br.com.amorim.supermarket.model.employee.Employee;

import java.math.BigInteger;

public interface GenerateRegisterNumberEmployee {

    BigInteger generate (Employee employee);
}
