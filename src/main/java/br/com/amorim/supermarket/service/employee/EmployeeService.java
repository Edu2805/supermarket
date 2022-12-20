package br.com.amorim.supermarket.service.employee;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.repository.employee.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public List<Employee> getAll () {
        return employeeRepository.findAll();
    }

    public Employee findById (UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Empregado não encontrado");
                });
    }

    @Transactional
    public Employee save (Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public void update (Employee employee, UUID id) {
        employeeRepository.findById(id)
                .map(existingEmployee -> {
                   employee.setId(existingEmployee.getId());
                   employeeRepository.save(employee);
                   return existingEmployee;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Empregado não encontrado"));
    }

    @Transactional
    public void delete (UUID id) {
        employeeRepository.findById(id)
                .map(existingEmployee -> {
                    employeeRepository.delete(existingEmployee);
                    return existingEmployee;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Empregado não encontrado"));
    }

}
