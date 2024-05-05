package br.com.amorim.supermarket.controller.saleinformation.dto.response;

import br.com.amorim.supermarket.model.attatchment.Attachment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleInformationSummaryDTO {

    private String establismentName;
    private String establishmentCnpj;
    private String employeeCode;
    private Attachment establishmentLogo;
}
