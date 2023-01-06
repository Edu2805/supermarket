package br.com.amorim.supermarket.service.productdata.calculatemargin;

import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculateMarginTest {

    @InjectMocks
    private CalculateMarginImpl calculateMarginImplMock;
    public static final java.util.UUID UUID = randomUUID();
    public static final String NAME = "Produto teste";

    private ProductData productData;

    private void startProduct () {
        ProviderProductTest providerProductTest = new ProviderProductTest();
        SubSectionTest subSectionTest = new SubSectionTest();
        productData = new ProductData();
        productData.setId(UUID);
        productData.setName(NAME);
        productData.setUnity(UnityType.UNITY);
        productData.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData.setSalePrice(BigDecimal.valueOf(15.90));
        productData.setEan13("7891112223334");
        productData.setInternalCode(BigInteger.valueOf(1));
        productData.setInventory(BigDecimal.valueOf(100));
        productData.setProviderProduct(providerProductTest.generateProvider());
        productData.setSubSection(subSectionTest.generateSubsection());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startProduct();
    }

    @Test
    public void shouldCalculateMargin () {
        var calculate = productData.getSalePrice().subtract(productData.getPurchasePrice());
        var marginCalculed = calculate.divide(productData.getSalePrice(), 4, RoundingMode.HALF_UP);

        var margin = calculateMarginImplMock.calculate(productData);

        assertEquals(marginCalculed, margin);
    }
}