package br.com.amorim.supermarket.repository.employee.getemployeeinformation;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.controller.saleinformation.dto.request.SaleInformationDTO;
import br.com.amorim.supermarket.controller.saleinformation.dto.response.SaleInformationSummaryDTO;
import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.employee.QEmployee;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Repository
public class EmployeeInformationRepositoryCustomImpl implements EmployeeInformationRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public SaleInformationSummaryDTO getEmployeeInformationSummary(SaleInformationDTO saleInformationDTO) {
        var saleInformationSummaryDTO = new SaleInformationSummaryDTO();
        var qEmployee = QEmployee.employee;
        JPAQuery<Employee> query = new JPAQuery<>(entityManager);
        var employee = query.select(qEmployee)
                .from(qEmployee)
                .where(qEmployee.person.userData.userName.eq(saleInformationDTO.getEmployeeLogin()))
                .fetchOne();

        if (employee == null) {
            throw new NotFoundException(getString(MessagesKeyType.EMPLOYEE_NOT_FOUND.message));
        }

        saleInformationSummaryDTO.setEstablismentName(
                employee.getSubSection().getMainSection().getDepartment().getEstablishment().getName());
        saleInformationSummaryDTO.setEstablishmentCnpj(
                employee.getSubSection().getMainSection().getDepartment().getEstablishment().getCnpj());
        saleInformationSummaryDTO.setEmployeeCode(
                employee.getRegisterNumber().toString());
        saleInformationSummaryDTO.setEstablishmentLogo(
                employee.getSubSection().getMainSection().getDepartment().getEstablishment()
                        .getEstablismentLogo());
        return saleInformationSummaryDTO;
    }
}
