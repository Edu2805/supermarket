package br.com.amorim.supermarket.model.historicalgoodsissue;

import br.com.amorim.supermarket.model.common.CommonIdAndNameEntity;
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
@Table(name = "historical_goods_issue")
public class HistoricalGoodsIssue extends CommonIdAndNameEntity {

    @Column(name = "product_code")
    private BigInteger productCode;
    @Column(name = "ean13")
    private String ean13;
    @Column(name = "dun14")
    private String dun14;
    @Column(name = "sale_price")
    private BigDecimal salePrice;
    @Column(name = "inventory")
    private BigDecimal inventory;
    @Column(name = "provider_product_name")
    private String providerProductName;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "mainsection_name")
    private String mainsectionName;
    @Column(name = "subsection_name")
    private String subsectionName;
    @Column(name = "sale_number")
    private BigInteger saleNumber;
    @Column(name = "products_total")
    private BigDecimal productsTotal;
    @Column(name = "is_effective_sale")
    private boolean isEffectiveSale = false;
    @Column(name = "registration_date")
    private Timestamp registrationDate;
}
