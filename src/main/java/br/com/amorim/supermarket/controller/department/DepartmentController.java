package br.com.amorim.supermarket.controller.department;

import br.com.amorim.supermarket.controller.department.dto.ConvertDepartmentMapper;
import br.com.amorim.supermarket.controller.department.dto.DepartmentDTO;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.service.department.DepartmentCrudServiceImpl;
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
@RequestMapping("api/department")
@Api("Department")
public class DepartmentController {

    private DepartmentCrudServiceImpl departmentService;
    private ConvertDepartmentMapper convertDepartmentMapper;

    @GetMapping
    @ApiOperation("Get all departments")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Departments returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the departments")
    })
    public Page<Department> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Department list page") int page,
                                   @RequestParam(
                                           value = "size",
                                           required = false,
                                           defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return departmentService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific department")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Department returned successfully"),
            @ApiResponse(code = 404, message = "Department not found for given id")
    })
    public Department getById (@PathVariable @ApiParam("Department id") UUID id) {
        return departmentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a department")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Department successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the department")
    })
    public Department save (@RequestBody @Valid @ApiParam("Parameters for saving the department") DepartmentDTO departmentDTO) {
        var newDepartment = convertDepartmentMapper.createDepartmentMapper(departmentDTO);
        return departmentService.save(newDepartment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a department")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Department successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the department")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the department")
                            DepartmentDTO departmentDTO, @PathVariable @ApiParam("Department id") UUID id) {
        var newDepartment = convertDepartmentMapper.createDepartmentMapper(departmentDTO);
        departmentService.update(newDepartment, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific department")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Department deleted successfully"),
            @ApiResponse(code = 404, message = "Department not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Department id") UUID id) {
        departmentService.delete(id);
    }
}
