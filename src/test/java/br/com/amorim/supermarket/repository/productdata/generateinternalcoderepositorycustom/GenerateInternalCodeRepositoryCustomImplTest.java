package br.com.amorim.supermarket.repository.productdata.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class GenerateInternalCodeRepositoryCustomImplTest {

    @Autowired
    private GenerateInternalCodeProductRepositoryCustomImpl generateInternalCodeRepositoryCustom;
    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    GenerateEntitiesRepositoryUtils generateEntities;

    private ProductData productData1;
    private ProductData productData2;
    private ProductData productData3;
    public static final String NAME_1 = "Produto teste 1";
    public static final String NAME_2 = "Produto teste 2";
    public static final String NAME_3 = "Produto teste 3";
    private BigInteger code1;

    private void startProduct () {
        Random randomEan13Product1 = new Random();
        Random randomDun14Product2 = new Random();
        Random randomEan13Product3 = new Random();

        var generateProvider = generateEntities.generateProvider();
        var generateEstablishment = generateEntities.generateEstablishment();
        var generateDepartment = generateEntities.generateDepartment(generateEstablishment);
        var generateMainSection = generateEntities.generateMainsection(generateDepartment);
        var generateSubsection = generateEntities.generateSubsection(generateMainSection);
        var ean13Product1 = randomEan13Product1.nextInt(789000000, 789999999);
        var dun14Product2 = randomDun14Product2.nextInt(978900000, 978999999);
        var ean13Product3 = randomEan13Product3.nextInt(799000000, 799999999);

        productData1 = new ProductData();
        productData1.setName(NAME_1);
        productData1.setUnity(UnityType.UNITY);
        productData1.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData1.setSalePrice(BigDecimal.valueOf(15.90));
        productData1.setMargin(BigDecimal.valueOf(0.9999));
        productData1.setEan13(String.valueOf(ean13Product1).concat("9999"));
        productData1.setInventory(BigDecimal.valueOf(100));
        productData1.setProviderProduct(generateProvider);
        productData1.setSubSection(generateSubsection);
        code1 = generateInternalCodeRepositoryCustom.generateInternalCode(productData1);
        productData1.setCode(code1);
        productDataRepository.save(productData1);

        productData2 = new ProductData();
        productData2.setName(NAME_2);
        productData2.setUnity(UnityType.UNITY);
        productData2.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData2.setSalePrice(BigDecimal.valueOf(15.90));
        productData2.setMargin(BigDecimal.valueOf(0.9999));
        productData2.setDun14(String.valueOf(dun14Product2));
        productData2.setInventory(BigDecimal.valueOf(100));
        productData2.setProviderProduct(generateProvider);
        productData2.setSubSection(generateSubsection);
        BigInteger code2 = generateInternalCodeRepositoryCustom.generateInternalCode(productData2);
        productData2.setCode(code2);
        productDataRepository.save(productData2);

        productData3 = new ProductData();
        productData3.setName(NAME_3);
        productData3.setUnity(UnityType.UNITY);
        productData3.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData3.setSalePrice(BigDecimal.valueOf(15.90));
        productData3.setMargin(BigDecimal.valueOf(0.9999));
        productData3.setEan13(String.valueOf(ean13Product3).concat("99999"));
        productData3.setInventory(BigDecimal.valueOf(100));
        productData3.setProviderProduct(generateProvider);
        productData3.setSubSection(generateSubsection);
    }

    @BeforeEach
    void setUp() {
        productDataRepository.deleteAll();
        startProduct();
    }

    @Transactional
    @Test
    void shouldSetTheFirstProductWithTheInternalCodeEqualToOne() {
        Assertions.assertEquals(BigInteger.valueOf(1), code1);
    }

    @Transactional
    @Test
    void shouldAddCodeSequenceBeforeSaving() {
        var generateCode = generateInternalCodeRepositoryCustom.generateInternalCode(productData3);
        Assertions.assertEquals(BigInteger.valueOf(3), generateCode);
    }
}