package br.com.amorim.supermarket.service.department;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor

@Service
public class DepartmentService {

    DepartmentRepository departmentRepository;

    public List<Department> getAll () {
        return departmentRepository.findAll();
    }

    public Department findById (UUID id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Departamento não encontrado");
                });
    }

    @Transactional
    public Department save (Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public void update (Department department, UUID id) {
        departmentRepository.findById(id)
                .map(existingDepartment -> {
                   department.setId(existingDepartment.getId());
                   departmentRepository.save(department);
                   return existingDepartment;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Departamento não encontrado"));
    }

    @Transactional
    public void delete (UUID id) {
        departmentRepository.findById(id)
                .map(existingDepartment -> {
                   departmentRepository.delete(existingDepartment);
                   return existingDepartment;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Departamento não encontrado"));
    }
}
