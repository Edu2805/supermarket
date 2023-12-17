package br.com.amorim.supermarket.controller.historicalgoodsreceipt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoricalGoodsReceiptOutput {

    private UUID id;
    private String name;
    private BigInteger productCode;
    private BigDecimal purchasePrice;
    private BigDecimal inventory;
    private String providerProductName;
    private String departmentName;
    private String mainsectionName;
    private String subsectionName;
    private String invoice;
    private BigDecimal totalInvoice;
    private Timestamp registrationDate;
    private boolean isReceived;
    private UUID sourceId;
}
