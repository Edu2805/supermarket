package br.com.amorim.supermarket.service.establishment.verifydepartment;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyDepartmentImpl implements VerifyDepartment {

    private DepartmentRepository departmentRepository;

    @Override
    public void verifyEstablishmentDepartment(Establishment establishment) {
        var existsByDepartment = departmentRepository.existsByEstablishment(establishment);
        if (existsByDepartment) {
            throw new NotFoundException(getString(
                    MessagesKeyType.ALREADY_EXISTS_DEPARTMENT_FOR_THE_ESTABLISHMENT.message));
        }
    }
}
