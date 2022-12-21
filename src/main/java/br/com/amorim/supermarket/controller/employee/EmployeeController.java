package br.com.amorim.supermarket.controller.employee;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.service.employee.EmployeeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAll () {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById (@PathVariable UUID id) {
        return employeeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Employee save (@RequestBody @Valid Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (Employee employee, UUID id) {
        employeeService.update(employee, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (UUID id) {
        employeeService.delete(id);
    }
}
