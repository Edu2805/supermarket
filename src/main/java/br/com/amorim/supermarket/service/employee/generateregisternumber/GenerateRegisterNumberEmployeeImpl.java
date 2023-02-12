package br.com.amorim.supermarket.service.employee.generateregisternumber;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.repository.employee.generateregisternumberemployeerepositorycustom.GenerateRegisterNumberEmployeeRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@AllArgsConstructor

@Component
public class GenerateRegisterNumberEmployeeImpl implements GenerateRegisterNumberEmployee {

    private GenerateRegisterNumberEmployeeRepositoryCustom generateRegisterNumberEmployeeRepositoryCustom;

    @Override
    public BigInteger generate(Employee employee) {
        return generateRegisterNumberEmployeeRepositoryCustom.generateRegisterNumber(employee);
    }
}
