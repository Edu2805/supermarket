package br.com.amorim.supermarket.repository.employee.getemployeeinformation;

import br.com.amorim.supermarket.controller.saleinformation.dto.request.SaleInformationDTO;
import br.com.amorim.supermarket.controller.saleinformation.dto.response.SaleInformationSummaryDTO;

public interface EmployeeInformationRepositoryCustom {

    SaleInformationSummaryDTO getEmployeeInformationSummary(SaleInformationDTO saleInformationDTO);
}
