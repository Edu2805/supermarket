package br.com.amorim.supermarket.service.department;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import br.com.amorim.supermarket.service.department.generateinternalcode.GenerateInternalCodeDepartment;
import br.com.amorim.supermarket.service.department.verifydepartmentname.VerifyDepartmentName;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class DepartmentCrudServiceImpl implements DepartmentCrudService {

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private DepartmentRepository departmentRepository;
    private VerifyPageSize verifyPageSize;
    private GenerateInternalCodeDepartment generateInternalCodeDepartment;
    private VerifyDepartmentName verifyDepartmentName;

    @Override
    public Page<Department> getAll(int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return departmentRepository.findAll(pageableRequest);
    }

    @Override
    public Department findById (UUID id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.DEPARTMENT_NOT_FOUND.message));
                });
    }

    @Transactional
    @Override
    public Department save (Department department) {
        verifyDepartmentName.existsByDepartmentNameBeforeSave(department);
        var internalCode = generateInternalCodeDepartment.generate(department);
        department.setCode(internalCode);
        return departmentRepository.save(department);
    }

    @Transactional
    @Override
    public void update (Department department, UUID id) {
        departmentRepository.findById(id)
                .map(existingDepartment -> {
                   department.setId(existingDepartment.getId());
                   verifyDepartmentName.existsByDepartmentNameBeforeUpdate(department);
                   department.setCode(existingDepartment.getCode());
                   departmentRepository.save(department);
                   return existingDepartment;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.DEPARTMENT_NOT_FOUND.message)));
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        departmentRepository.findById(id)
                .map(existingDepartment -> {
                   departmentRepository.delete(existingDepartment);
                   return existingDepartment;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.DEPARTMENT_NOT_FOUND.message)));
    }
}
