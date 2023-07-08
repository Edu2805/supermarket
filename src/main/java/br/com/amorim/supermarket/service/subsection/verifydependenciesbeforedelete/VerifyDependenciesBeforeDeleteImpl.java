package br.com.amorim.supermarket.service.subsection.verifydependenciesbeforedelete;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.repository.employee.EmployeeRepository;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyDependenciesBeforeDeleteImpl implements VerifyDependenciesBeforeDelete {

    private EmployeeRepository employeeRepository;
    private ProductDataRepository productDataRepository;

    @Override
    public void existsProductInSubSection(SubSection subSection) {
        var existsBySubSection = productDataRepository.existsBySubSection(subSection);
        if (existsBySubSection) {
            throw new NotFoundException(getString(
                    MessagesKeyType.ALREADY_EXISTS_PRODUCTS_FOR_THE_SUB_SECTION.message));
        }
    }

    @Override
    public void existsEmployeeInSubSection(SubSection subSection) {
        var existsBySubSection = employeeRepository.existsBySubSection(subSection);
        if (existsBySubSection) {
            throw new NotFoundException(getString(
                    MessagesKeyType.ALREADY_EXISTS_EMPLOYEES_FOR_THE_SUB_SECTION.message));
        }
    }
}
