package br.com.amorim.supermarket.service.productdata.generateinternalcode;

import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.generateinternalcoderepositorycustom.GenerateInternalCodeRepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class GenerateInternalCodeImplTest {

    @InjectMocks
    private GenerateInternalCodeImpl generateInternalCode;
    @Mock
    private GenerateInternalCodeRepositoryCustom productDataRepositoryCustomMock;

    public static final java.util.UUID UUID_1 = randomUUID();
    public static final String NAME_1 = "Produto teste 1";
    public static final java.util.UUID UUID_2 = randomUUID();
    public static final String NAME_2 = "Produto teste 2";
    public static final java.util.UUID UUID_3 = randomUUID();
    public static final String NAME_3 = "Produto teste 3";

    private ProductData productData1;
    private ProductData productData2;
    private ProductData productData3;

    @BeforeEach
    void setUp() {
        ProviderProductTest providerProductTest = new ProviderProductTest();
        SubSectionTest subSectionTest = new SubSectionTest();
        productData1 = new ProductData();
        productData1.setId(UUID_1);
        productData1.setName(NAME_1);
        productData1.setUnity(UnityType.UNITY);
        productData1.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData1.setSalePrice(BigDecimal.valueOf(15.90));
        productData1.setEan13("7891112223334");
        productData1.setInventory(BigDecimal.valueOf(100));
        productData1.setProviderProduct(providerProductTest.generateProvider());
        productData1.setSubSection(subSectionTest.generateSubsection());

        productData2 = new ProductData();
        productData2.setId(UUID_2);
        productData2.setName(NAME_2);
        productData2.setUnity(UnityType.UNITY);
        productData2.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData2.setSalePrice(BigDecimal.valueOf(15.90));
        productData2.setEan13("7891112223335");
        productData2.setInventory(BigDecimal.valueOf(100));
        productData2.setProviderProduct(providerProductTest.generateProvider());
        productData2.setSubSection(subSectionTest.generateSubsection());

        productData3 = new ProductData();
        productData3.setId(UUID_3);
        productData3.setName(NAME_3);
        productData3.setUnity(UnityType.UNITY);
        productData3.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData3.setSalePrice(BigDecimal.valueOf(15.90));
        productData3.setEan13("7891112223336");
        productData3.setInventory(BigDecimal.valueOf(100));
        productData3.setProviderProduct(providerProductTest.generateProvider());
        productData3.setSubSection(subSectionTest.generateSubsection());
    }

    @Test
    void shouldGenerateANewCodeWhenSave() {
        when(productDataRepositoryCustomMock.generateInternalCode(productData1))
                .thenReturn(BigInteger.valueOf(1));
        when(productDataRepositoryCustomMock.generateInternalCode(productData2))
                .thenReturn(BigInteger.valueOf(2));
        when(productDataRepositoryCustomMock.generateInternalCode(productData3))
                .thenReturn(BigInteger.valueOf(3));
        var newCode = generateInternalCode.generate(productData3);
        productData3.setInternalCode(newCode);
        assertEquals(BigInteger.valueOf(3), productData3.getInternalCode());
    }
}