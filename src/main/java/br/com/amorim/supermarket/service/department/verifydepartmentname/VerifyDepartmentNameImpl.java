package br.com.amorim.supermarket.service.department.verifydepartmentname;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyDepartmentNameImpl implements VerifyDepartmentName {

    private DepartmentRepository departmentRepository;

    @Override
    public boolean existsByDepartmentNameBeforeSave(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new BusinessRuleException(
                    getString(MessagesKeyType.DEPARTMENT_NAME_ALREADY_EXISTS.message));
        }
        return false;
    }

    @Override
    public boolean existsByDepartmentNameBeforeUpdate(Department department) {
        departmentRepository.findAll()
                .forEach(existingDepartment -> {
                    if(existingDepartment.getName().equals(department.getName())) {
                        if (!existingDepartment.getId().equals(department.getId())) {
                            throw new BusinessRuleException(
                                    getString(MessagesKeyType.DEPARTMENT_NAME_ALREADY_EXISTS.message));
                        }
                    }
                });
        return false;
    }
}
