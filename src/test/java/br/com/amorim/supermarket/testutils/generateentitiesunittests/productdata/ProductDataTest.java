package br.com.amorim.supermarket.testutils.generateentitiesunittests.productdata;

import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class ProductDataTest {

    public ProductData generateProductData () {
        ProviderProductTest providerProductTest = new ProviderProductTest();
        SubSectionTest subSectionTest = new SubSectionTest();

        Random randomName = new Random();
        Random randomCode = new Random();
        Random randomEan13 = new Random();
        UUID uuid = randomUUID();

        var name = randomName.nextInt(10000, 19999);
        var code = randomCode.nextInt(1, 19999);
        var ean13 = randomEan13.nextInt(789000000, 789999999);

        ProductData productData = new ProductData();
        productData.setId(uuid);
        productData.setName(String.valueOf(name));
        productData.setUnity(UnityType.UNITY);
        productData.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData.setSalePrice(BigDecimal.valueOf(15.90));
        productData.setMargin(BigDecimal.valueOf(0.5555));
        productData.setInternalCode(BigInteger.valueOf(code));
        productData.setEan13(String.valueOf(ean13).concat("9999"));
        productData.setInventory(BigDecimal.valueOf(100));
        productData.setProviderProduct(providerProductTest.generateProvider());
        productData.setSubSection(subSectionTest.generateSubsection());
        return productData;
    }
}
