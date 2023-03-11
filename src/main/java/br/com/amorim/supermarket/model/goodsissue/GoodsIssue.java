package br.com.amorim.supermarket.model.goodsissue;

import br.com.amorim.supermarket.model.common.CommonIdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "goods_issue")
public class GoodsIssue extends CommonIdEntity {

    @Column(name = "sale_number", nullable = false)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_SALE_NUMBER_IS_NOT_EMPTY}")
    private BigInteger saleNumber;
    @Column(name = "product_description", length = 50)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_PRODUCT_DESCRIPTION_IS_NOT_EMPTY}")
    private String productDescription;
    @Column(name = "bar_code", length = 14)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_BAR_CODE_IS_NOT_EMPTY}")
    private String barCode;
    @Column(name = "unity_value", precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_UNITY_VALUE_IS_NOT_EMPTY}")
    private BigDecimal unityValue;
    @Column(name = "product_total", precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_PRODUCT_TOTAL_IS_NOT_EMPTY}")
    private BigDecimal productTotal;
    @Column(name = "subtotal", precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_SUBTOTAL_IS_NOT_EMPTY}")
    private BigDecimal subtotal;
    @Column(name = "total_received", precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_TOTAL_RECEIVED_IS_NOT_EMPTY}")
    private BigDecimal totalReceived;
    @Column(name = "change", precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_CHANGE_IS_NOT_EMPTY}")
    private BigDecimal change;
    @Column(name = "registration_date", nullable = false)
    private Timestamp registrationDate;
    @ElementCollection
    @CollectionTable(name = "product_list")
    private List<String> productList;
}
