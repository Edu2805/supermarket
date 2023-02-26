package br.com.amorim.supermarket.service.department.generateinternalcode;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.repository.department.generateinternalcoderepositorycustom.GenerateInternalCodeDepartmentRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@AllArgsConstructor

@Component
public class GenerateInternalCodeDepartmentImpl implements GenerateInternalCodeDepartment {

    private GenerateInternalCodeDepartmentRepositoryCustom generateInternalCodeDepartmentRepositoryCustom;

    @Override
    public BigInteger generate(Department department) {
        return generateInternalCodeDepartmentRepositoryCustom
                .generateInternalCode(department);
    }
}
