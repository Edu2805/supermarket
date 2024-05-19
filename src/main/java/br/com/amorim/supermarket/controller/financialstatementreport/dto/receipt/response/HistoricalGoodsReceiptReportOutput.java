package br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoricalGoodsReceiptReportOutput {
    private String name;
    private BigInteger productCode;
    private BigDecimal inventory;
    private String providerProductName;
    private String departmentName;
    private String mainsectionName;
    private String subsectionName;
    private String invoiceNumber;
    private BigDecimal totalInvoice;
    private Timestamp registrationDate;
    private boolean isReceived;
}
