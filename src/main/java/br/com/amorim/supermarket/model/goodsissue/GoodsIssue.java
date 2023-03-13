package br.com.amorim.supermarket.model.goodsissue;

import br.com.amorim.supermarket.common.enums.PaymentOptionsType;
import br.com.amorim.supermarket.model.common.CommonIdEntity;
import br.com.amorim.supermarket.model.productdata.ProductData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(name = "products_total", precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_PRODUCT_TOTAL_IS_NOT_EMPTY}")
    private BigDecimal productsTotal;
    @Column(name = "subtotal", precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_SUBTOTAL_IS_NOT_EMPTY}")
    private BigDecimal subtotal;
    @Column(name = "total_received", precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_TOTAL_RECEIVED_IS_NOT_EMPTY}")
    private BigDecimal totalReceived;
    @Column(name = "change", precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_CHANGE_IS_NOT_EMPTY}")
    private BigDecimal change;
    @Column(name = "is_effective_sale", nullable = false)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_IS_EFFECTIVE_SALE_IS_NOT_EMPTY}")
    private boolean isEffectiveSale = false;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_options_type", nullable = false)
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_FIELD_PAYMENT_OPTIONS_TYPE_IS_NOT_EMPTY}")
    private PaymentOptionsType paymentOptionsType = PaymentOptionsType.OPENED;
    @Column(name = "registration_date", nullable = false)
    private Timestamp registrationDate;
    @OneToMany(cascade= CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinTable(
            name = "goods_issue_to_product_data",
            joinColumns = @JoinColumn(name = "goods_issue_id"),
            inverseJoinColumns = @JoinColumn(name = "product_data_id")
    )
    private List<ProductData> productDataList;
    @ElementCollection
    @CollectionTable(name = "product_issue_list")
    private List<String> productList;
}
