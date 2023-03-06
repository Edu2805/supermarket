package br.com.amorim.supermarket.model.goodsreceipt;

import br.com.amorim.supermarket.model.common.CommonIdEntity;
import br.com.amorim.supermarket.model.productdata.ProductData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "goods_receipt")
public class GoodsReceipt extends CommonIdEntity {

    @Column(name = "control_number", nullable = false)
    @NotNull(message = "{br.com.supermarket.GOODS_RECEIPT_FIELD_CONTROL_NUMBER_IS_NOT_EMPTY}")
    private BigInteger controlNumber;
    @Column(name = "invoice", length = 50)
    @NotNull(message = "{br.com.supermarket.GOODS_RECEIPT_FIELD_INVOICE_IS_NOT_EMPTY}")
    private String invoice;
    @OneToMany(cascade= CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinTable(
            name = "goods_receipt_to_product_data",
            joinColumns = @JoinColumn(name = "goods_receipt_id"),
            inverseJoinColumns = @JoinColumn(name = "product_data_id")
    )
    private List<ProductData> productDataList;
}
