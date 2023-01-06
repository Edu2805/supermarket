package br.com.amorim.supermarket.testutils.generateentitiesunittests.productdata;

import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;

import java.math.BigDecimal;

import static java.util.UUID.randomUUID;

public class ProductDataTest {

    public java.util.UUID UUID = randomUUID();
    public String NAME = "Fornecedor teste";

    public ProductData generateProductData () {
        ProviderProductTest providerProductTest = new ProviderProductTest();
        SubSectionTest subSectionTest = new SubSectionTest();
        ProductData productData = new ProductData();
        productData.setId(UUID);
        productData.setName(NAME);
        productData.setUnity(UnityType.UNITY);
        productData.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData.setSalePrice(BigDecimal.valueOf(15.90));
        productData.setEan13("7891112223334");
        productData.setInventory(BigDecimal.valueOf(100));
        productData.setProviderProduct(providerProductTest.generateProvider());
        productData.setSubSection(subSectionTest.generateSubsection());
        return productData;
    }
}
