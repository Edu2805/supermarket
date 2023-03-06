package br.com.amorim.supermarket.model.productdata;

import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.common.CommonIdNameAndCodeEntity;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.model.subsection.SubSection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "product_data")
public class ProductData extends CommonIdNameAndCodeEntity {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "unity", nullable = false)
    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_FIELD_UNITY_IS_NOT_NULL}")
    private UnityType unity;

    @Column(name = "purchase_price", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_FIELD_PURCHASE_PRICE_IS_NOT_EMPTY}")
    private BigDecimal purchasePrice;

    @Column(name = "sale_price", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_FIELD_SALE_PRICE_IS_NOT_EMPTY}")
    private BigDecimal salePrice;

    @Column(precision = 10, scale = 2)
    private BigDecimal margin;

    @Column(name = "ean_13", length = 13)
    private String ean13;

    @Column(name = "dun_14", length = 14)
    private String dun14;

    @Column(nullable = false)
    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_FIELD_INVENTORY_IS_NOT_EMPTY}")
    private BigDecimal inventory;

    @ManyToOne
    @JoinColumn(name = "subsection_id")
    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_FIELD_SUB_SECTION_IS_NOT_NULL}")
    private SubSection subSection;

    @ManyToOne
    @JoinColumn(name = "provider_product")
    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_FIELD_PROVIDER_PRODUCT_IS_NOT_NULL}")
    private ProviderProduct providerProduct;
}
