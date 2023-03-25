package br.com.amorim.supermarket.model.historicalgoodsreceipt;

import br.com.amorim.supermarket.model.common.CommonIdNameAndCodeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "historical_goods_receipt")
public class HistoricalGoodsReceipt extends CommonIdNameAndCodeEntity {

    @Column(name = "product_code")
    private BigInteger productCode;
    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;
    @Column(name = "inventory")
    private BigDecimal inventory;
    @Column(name = "departmente_name")
    private String departmentName;
    @Column(name = "mainsection_name")
    private String mainsectionName;
    @Column(name = "subsection_name")
    private String subsectionName;
    @Column(name = "invoice")
    private String invoice;
    @Column(name = "total_invoice")
    private String totalInvoice;
    @Column(name = "registration_date")
    private Timestamp registrationDate;
    @Column(name = "is_received")
    private boolean isReceived;
}
