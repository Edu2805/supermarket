package br.com.amorim.supermarket.controller.department;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.service.department.DepartmentService;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor

@RestController
@RequestMapping("department")
public class DepartmentController {

    private DepartmentService departmentService;

    @GetMapping
    public List<Department> findAll () {
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public Department getById (@PathVariable UUID id) {
        return departmentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Department save (@RequestBody @Valid Department department) {
        return departmentService.save(department);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid Department department, @PathVariable UUID id) {
        departmentService.update(department, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        departmentService.delete(id);
    }
}
