package br.com.amorim.supermarket.controller.productdata.dto.mapper;

import br.com.amorim.supermarket.controller.productdata.dto.request.ProductUnityStringDTO;
import br.com.amorim.supermarket.model.productdata.ProductData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class UnityForProductMapperImpl implements UnityForProductMapper {

    private UnityTypeMapper unityTypeMapper;

    @Override
    public ProductData productMapper(ProductUnityStringDTO productUnityStringDTO) {
        var productData = new ProductData();
        productData.setName(productUnityStringDTO.getName());
        productData.setUnity(unityTypeMapper.mapperUnityTypeMapper(productUnityStringDTO.getUnity()));
        productData.setPurchasePrice(productUnityStringDTO.getPurchasePrice());
        productData.setSalePrice(productUnityStringDTO.getSalePrice());
        productData.setEan13(productUnityStringDTO.getEan13());
        productData.setDun14(productUnityStringDTO.getDun14());
        productData.setInventory(productUnityStringDTO.getInventory());
        productData.setSubSection(productUnityStringDTO.getSubSection());
        productData.setProviderProduct(productUnityStringDTO.getProviderProduct());
        productData.setProductPhoto(productUnityStringDTO.getProductPhoto());
        return productData;
    }
}
