package br.com.amorim.supermarket.controller.productdata.dto.response;

import br.com.amorim.supermarket.controller.providerproduct.dto.response.ConvertProviderSubscriptionTypeString;
import br.com.amorim.supermarket.model.productdata.ProductData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class ConvertProductUnitTypeStringDTOImpl implements ConvertProductUnitTypeStringDTO {

    private ConvertProviderSubscriptionTypeString convertProviderSubscriptionTypeString;

    @Override
    public ProductUnitTypeStringDTO mapper(ProductData productData) {
        return mapperProvider(productData);
    }

    private ProductUnitTypeStringDTO mapperProvider(ProductData productData) {
        var productUnitTypeStringDTO = new ProductUnitTypeStringDTO();
        productUnitTypeStringDTO.setId(productData.getId());
        productUnitTypeStringDTO.setName(productData.getName());
        productUnitTypeStringDTO.setUnity(getString(productData.getUnity().name()));
        productUnitTypeStringDTO.setPurchasePrice(productData.getPurchasePrice());
        productUnitTypeStringDTO.setSalePrice(productData.getSalePrice());
        productUnitTypeStringDTO.setMargin(productData.getMargin());
        productUnitTypeStringDTO.setEan13(productData.getEan13());
        productUnitTypeStringDTO.setDun14(productData.getDun14());
        productUnitTypeStringDTO.setCode(productData.getCode());
        productUnitTypeStringDTO.setInventory(productData.getInventory());
        productUnitTypeStringDTO.setSubSection(productData.getSubSection());
        var mapperProviderProduct = convertProviderSubscriptionTypeString.mapper(productData.getProviderProduct());
        productUnitTypeStringDTO.setProviderProduct(mapperProviderProduct);
        productUnitTypeStringDTO.setProductPhoto(productData.getProductPhoto());
        return productUnitTypeStringDTO;
    }
}
