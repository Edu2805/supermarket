package br.com.amorim.supermarket.service.saleinformation;

import br.com.amorim.supermarket.controller.saleinformation.dto.request.SaleInformationDTO;
import br.com.amorim.supermarket.controller.saleinformation.dto.response.SaleInformationSummaryDTO;
import br.com.amorim.supermarket.repository.employee.getemployeeinformation.EmployeeInformationRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class SaleInformationServiceImpl implements SaleInformationService {

    private EmployeeInformationRepositoryCustom employeeInformationRepositoryCustom;

    @Override
    public SaleInformationSummaryDTO getSaleInformationSummary(SaleInformationDTO saleInformationDTO) {
        return employeeInformationRepositoryCustom.getEmployeeInformationSummary(saleInformationDTO);
    }
}
