package br.com.amorim.supermarket.service.employee;

import br.com.amorim.supermarket.model.employee.Employee;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface EmployeeCrudService {

    Page<Employee> getAll(int page, int size);
    Employee findById(UUID id);
    Employee save (Employee employee);
    void update (Employee employee, UUID id);
    void delete (UUID id);
}
