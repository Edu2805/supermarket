package br.com.amorim.supermarket.model.productdata;

import br.com.amorim.supermarket.model.subsection.SubSection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "product_data")
public class ProductData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 30)
    @NotEmpty(message = "Nome não pode estar vazio")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Quantidade não pode estar vazio")
    private BigInteger unity;

    @Column(name = "purchase_price", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Preço de compra não pode estar vazio")
    private BigDecimal purchasePrice;

    @Column(name = "sale_price", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Preço de venda não pode estar vazio")
    private BigDecimal salePrice;

    @Column(precision = 10, scale = 2)
    private BigDecimal margin;

    @Column(nullable = false, length = 60)
    @NotEmpty(message = "Fornecedor não pode estar vazio")
    private String provider;

    @Column(name = "EAN_13", length = 13)
    private String ean13;

    @Column(name = "DUN_14", length = 14)
    private String dun14;

    @Column(name = "internal_code", nullable = false)
    @NotNull(message = "Código interno não pode estar vazio")
    private BigInteger internalCode;

    @Column(nullable = false)
    @NotNull(message = "Estoque não pode estar vazio")
    private BigInteger inventory;

    @ManyToOne
    @JoinColumn(name = "subsection_id")
    private SubSection subSection;
}
