package br.com.amorim.supermarket.controller.employee;

import br.com.amorim.supermarket.controller.employee.dto.ConvertEmployeeMapper;
import br.com.amorim.supermarket.controller.employee.dto.EmployeeSaveDTO;
import br.com.amorim.supermarket.controller.employee.dto.EmployeeUpdateDTO;
import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.service.employee.EmployeeCrudServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api("Employee")
public class EmployeeController {

    private EmployeeCrudServiceImpl employeeService;
    private ConvertEmployeeMapper convertEmployeeMapper;

    @GetMapping
    @ApiOperation("Get all employees")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Employees returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the employees")
    })
    public Page<Employee> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Employee list page") int page,
                                   @RequestParam(
                                           value = "size",
                                           required = false,
                                           defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return employeeService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific employee")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Employee returned successfully"),
            @ApiResponse(code = 404, message = "Employee not found for given id")
    })
    public Employee getById (@PathVariable @ApiParam("Employee id") UUID id) {
        return employeeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a employee")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Employee successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the employee")
    })
    public Employee save (@RequestBody @Valid @ApiParam("Parameters for saving the employee") EmployeeSaveDTO employeeDTO) {
        var newEmployee = convertEmployeeMapper
                .createEmployeeMapper(employeeDTO);
        return employeeService.save(newEmployee);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a employee")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Employee successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the employee")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the employee")
                            EmployeeUpdateDTO employeeUpdateDTO, @PathVariable @ApiParam("employee id") UUID id) {
        var newEmployee = convertEmployeeMapper
                .updateEmployeeMapper(employeeUpdateDTO);
        employeeService.update(newEmployee, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific employee")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Employee deleted successfully"),
            @ApiResponse(code = 404, message = "Employee not found for given id")
    })
    public void delete (@PathVariable @ApiParam("employee id") UUID id) {
        employeeService.delete(id);
    }
}
