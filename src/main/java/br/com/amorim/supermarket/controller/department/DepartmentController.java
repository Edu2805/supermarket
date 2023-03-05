package br.com.amorim.supermarket.controller.department;

import br.com.amorim.supermarket.configuration.security.roles.RoleType;
import br.com.amorim.supermarket.controller.department.dto.ConvertDepartmentMapper;
import br.com.amorim.supermarket.controller.department.dto.DepartmentDTO;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.service.department.DepartmentCrudServiceImpl;
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
public class DepartmentController {

    private DepartmentCrudServiceImpl departmentService;
    private ConvertDepartmentMapper convertDepartmentMapper;

    @GetMapping
    public Page<Department> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                   @RequestParam(
                                           value = "size",
                                           required = false,
                                           defaultValue = "20") int size) {
        return departmentService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public Department getById (@PathVariable UUID id) {
        return departmentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Department save (@RequestBody @Valid DepartmentDTO departmentDTO) {
        var newDepartment = convertDepartmentMapper.createDepartmentMapper(departmentDTO);
        return departmentService.save(newDepartment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable UUID id) {
        var newDepartment = convertDepartmentMapper.createDepartmentMapper(departmentDTO);
        departmentService.update(newDepartment, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        departmentService.delete(id);
    }
}
