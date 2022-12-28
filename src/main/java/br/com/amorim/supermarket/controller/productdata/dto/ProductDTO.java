package br.com.amorim.supermarket.controller.productdata.dto;

import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.model.subsection.SubSection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    @NotBlank(message = "Nome não pode estar em branco.")
    @NotEmpty(message = "Nome não pode estar vazio.")
    @Size(max = 30, message = "Nome não pode ter mais do que 30 caracteres.")
    private String name;
    @NotBlank(message = "Tipo de unidade de medida não pode estar em branco.")
    @NotEmpty(message = "Tipo de unidade de medida não pode estar vazio.")
    private UnityType unity;
    @NotBlank(message = "Preço de compra não pode estar em branco.")
    @NotEmpty(message = "Preço de compra não pode estar vazio.")
    @Positive(message = "Preço de compra não pode ser negativo")
    @Digits(integer = 2, fraction = 0, message = "Digite os valores no formato correto (XXX.XXX.XXX,XX)")
    private BigDecimal purchasePrice;
    @NotBlank(message = "Preço de venda não pode estar em branco.")
    @NotEmpty(message = "Preço de venda não pode estar vazio.")
    @Positive(message = "Preço de venda não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Digite os valores no formato correto (XXX.XXX.XXX,XX)")
    private BigDecimal salePrice;
    private String ean13;
    private String dun14;
    @NotBlank(message = "Estoque não pode estar em branco.")
    @NotEmpty(message = "Estoque não pode estar vazio.")
    @Positive(message = "Estoque não pode ser negativo")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal inventory;
    @NotBlank(message = "Sub-Seção não pode estar em branco.")
    @NotEmpty(message = "Sub-Seção  não pode estar vazio.")
    private SubSection subSection;
    @NotBlank(message = "Fornecedor não pode estar em branco.")
    @NotEmpty(message = "Fornecedor não pode estar vazio.")
    private ProviderProduct providerProduct;
}
