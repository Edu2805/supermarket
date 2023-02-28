package br.com.amorim.supermarket.controller.employee;

import br.com.amorim.supermarket.controller.employee.dto.ConvertEmployeeMapper;
import br.com.amorim.supermarket.controller.employee.dto.EmployeeSaveDTO;
import br.com.amorim.supermarket.controller.employee.dto.EmployeeUpdateDTO;
import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.service.employee.EmployeeCrudServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private EmployeeCrudServiceImpl employeeService;
    private ConvertEmployeeMapper convertEmployeeMapper;

    @GetMapping
    public Page<Employee> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                   @RequestParam(
                                           value = "size",
                                           required = false,
                                           defaultValue = "20") int size) {
        return employeeService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public Employee getById (@PathVariable UUID id) {
        return employeeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Employee save (@RequestBody @Valid EmployeeSaveDTO employeeDTO) {
        var newEmployee = convertEmployeeMapper
                .createEmployeeMapper(employeeDTO);
        return employeeService.save(newEmployee);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid EmployeeUpdateDTO employeeUpdateDTO, @PathVariable UUID id) {
        var newEmployee = convertEmployeeMapper
                .updateEmployeeMapper(employeeUpdateDTO);
        employeeService.update(newEmployee, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        employeeService.delete(id);
    }
}
