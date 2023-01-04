package br.com.amorim.supermarket.controller.productdata.dto;

import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.model.subsection.SubSection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    @NotBlank(message = "Nome não pode estar em vazio.")
    @Size(min = 5, max = 30, message = "Nome deve ter no mínimo 5 e no máximo 30 caracteres.")
    private String name;

    @NotNull(message = "Tipo de unidade de medida não pode estar nulo.")
    private UnityType unity;

    @NotNull(message = "Preço de compra não pode estar nulo.")
    @Positive(message = "Preço de compra não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Digite os valores no formato correto (XXX.XXX.XXX,XX)")
    private BigDecimal purchasePrice;

    @NotNull(message = "Preço de venda não pode estar nulo.")
    @Positive(message = "Preço de venda não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Digite os valores no formato correto (XXX.XXX.XXX,XX)")
    private BigDecimal salePrice;

    @Size(min = 13, max = 13, message = "O EAN 13 deve conter 13 dígitos")
    private String ean13;

    @Size(min = 14, max = 14, message = "O DUN 14 deve conter 14 dígitos")
    private String dun14;

    @NotNull(message = "Estoque não pode estar nulo.")
    @Positive(message = "Estoque não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Digite o estoque no formato unitátio ou decimal")
    private BigDecimal inventory;

    @NotNull(message = "Sub-Seção  não pode estar nulo.")
    private SubSection subSection;

    @NotNull(message = "Fornecedor não pode estar nulo.")
    private ProviderProduct providerProduct;
}
