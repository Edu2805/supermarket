package br.com.amorim.supermarket.service.saleinformation;

import br.com.amorim.supermarket.controller.saleinformation.dto.request.SaleInformationDTO;
import br.com.amorim.supermarket.controller.saleinformation.dto.response.SaleInformationSummaryDTO;

public interface SaleInformationService {

    SaleInformationSummaryDTO getSaleInformationSummary(SaleInformationDTO saleInformationDTO);
}
