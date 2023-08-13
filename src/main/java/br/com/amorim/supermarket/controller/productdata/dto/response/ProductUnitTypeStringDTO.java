package br.com.amorim.supermarket.controller.productdata.dto.response;

import br.com.amorim.supermarket.controller.providerproduct.dto.response.ProviderProductSubscriptionTypeStringDTO;
import br.com.amorim.supermarket.model.attatchment.Attachment;
import br.com.amorim.supermarket.model.subsection.SubSection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductUnitTypeStringDTO {

    private UUID id;
    private String name;
    private String unity;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private BigDecimal margin;
    private String ean13;
    private String dun14;
    protected BigInteger code;
    private BigDecimal inventory;
    private SubSection subSection;
    private ProviderProductSubscriptionTypeStringDTO providerProduct;
    private Attachment productPhoto;
}
