package br.com.amorim.supermarket.repository.productdata.verifyean13ordun14repositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;

import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class VerifyEan13OrDun14RepositoryCustomImplTest {

    @Autowired
    private VerifyEan13OrDun14RepositoryCustom verifyEan13OrDun14RepositoryCustom;
    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    GenerateEntitiesRepositoryUtils generateEntities;

    private ProductData productData1;
    private ProductData productData2;
    private ProductData productData3;
    private ProductData productData4;
    private ProductData productData5;
    private ProductData productData6;
    public static final String NAME_1 = "Produto teste 1";
    public static final String NAME_2 = "Produto teste 2";
    public static final String NAME_3 = "Produto teste 3";
    public static final String NAME_4 = "Produto teste 4";
    public static final String NAME_5 = "Produto teste 5";
    public static final String NAME_6 = "Produto teste 6";

    private void startProduct () {
        Random randomEan13Product1 = new Random();
        Random randomDun14Product3 = new Random();
        Random randomDun13Product5 = new Random();
        Random randomDun14Product6 = new Random();
        Random randomCode1 = new Random();
        Random randomCode2 = new Random();
        Random randomCode3 = new Random();
        Random randomCode4 = new Random();
        Random randomCode5 = new Random();
        Random randomCode6 = new Random();

        var generateProvider = generateEntities.generateProvider();
        var generateEstablishment = generateEntities.generateEstablishment();
        var generateDepartment = generateEntities.generateDepartment(generateEstablishment);
        var generateMainSection = generateEntities.generateMainsection(generateDepartment);
        var generateSubsection = generateEntities.generateSubsection(generateMainSection);
        var ean13Product1 = randomEan13Product1.nextInt(789000000, 789999999);
        var dun14Product3 = randomDun14Product3.nextInt(978900000, 978999999);
        var ean13Product5 = randomDun13Product5.nextInt(889000000, 889999999);
        var dun14Product6 = randomDun14Product6.nextInt(988900000, 988999999);
        var code1 = randomCode1.nextInt(1, 1000);
        var code2 = randomCode2.nextInt(1001, 2000);
        var code3 = randomCode3.nextInt(2001, 3000);
        var code4 = randomCode4.nextInt(3001, 4000);
        var code5 = randomCode5.nextInt(4001, 5000);
        var code6 = randomCode6.nextInt(5001, 6000);

        productData1 = new ProductData();
        productData1.setName(NAME_1);
        productData1.setUnity(UnityType.UNITY);
        productData1.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData1.setSalePrice(BigDecimal.valueOf(15.90));
        productData1.setMargin(BigDecimal.valueOf(0.9999));
        productData1.setEan13(String.valueOf(ean13Product1).concat("9999"));
        productData1.setInternalCode(BigInteger.valueOf(code1));
        productData1.setInventory(BigDecimal.valueOf(100));
        productData1.setProviderProduct(generateProvider);
        productData1.setSubSection(generateSubsection);
        productDataRepository.save(productData1);

        productData2 = new ProductData();
        productData2.setName(NAME_2);
        productData2.setUnity(UnityType.UNITY);
        productData2.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData2.setSalePrice(BigDecimal.valueOf(15.90));
        productData2.setMargin(BigDecimal.valueOf(0.9999));
        productData2.setInternalCode(BigInteger.valueOf(code2));
        productData2.setInventory(BigDecimal.valueOf(100));
        productData2.setProviderProduct(generateProvider);
        productData2.setSubSection(generateSubsection);

        productData3 = new ProductData();
        productData3.setName(NAME_3);
        productData3.setUnity(UnityType.UNITY);
        productData3.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData3.setSalePrice(BigDecimal.valueOf(15.90));
        productData3.setMargin(BigDecimal.valueOf(0.9999));
        productData3.setDun14(String.valueOf(dun14Product3).concat("99999"));
        productData3.setInternalCode(BigInteger.valueOf(code3));
        productData3.setInventory(BigDecimal.valueOf(100));
        productData3.setProviderProduct(generateProvider);
        productData3.setSubSection(generateSubsection);
        productDataRepository.save(productData3);

        productData4 = new ProductData();
        productData4.setName(NAME_4);
        productData4.setUnity(UnityType.UNITY);
        productData4.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData4.setSalePrice(BigDecimal.valueOf(15.90));
        productData4.setMargin(BigDecimal.valueOf(0.9999));
        productData4.setInternalCode(BigInteger.valueOf(code4));
        productData4.setInventory(BigDecimal.valueOf(100));
        productData4.setProviderProduct(generateProvider);
        productData4.setSubSection(generateSubsection);

        productData5 = new ProductData();
        productData5.setName(NAME_5);
        productData5.setUnity(UnityType.UNITY);
        productData5.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData5.setSalePrice(BigDecimal.valueOf(15.90));
        productData5.setMargin(BigDecimal.valueOf(0.9999));
        productData5.setEan13(String.valueOf(ean13Product5).concat("9999"));
        productData5.setInternalCode(BigInteger.valueOf(code5));
        productData5.setInventory(BigDecimal.valueOf(100));
        productData5.setProviderProduct(generateProvider);
        productData5.setSubSection(generateSubsection);

        productData6 = new ProductData();
        productData6.setName(NAME_6);
        productData6.setUnity(UnityType.UNITY);
        productData6.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData6.setSalePrice(BigDecimal.valueOf(15.90));
        productData6.setMargin(BigDecimal.valueOf(0.9999));
        productData6.setDun14(String.valueOf(dun14Product6).concat("9999"));
        productData6.setInternalCode(BigInteger.valueOf(code6));
        productData6.setInventory(BigDecimal.valueOf(100));
        productData6.setProviderProduct(generateProvider);
        productData6.setSubSection(generateSubsection);
    }

    private void deleteProduct() {
        productDataRepository.delete(productData1);
        productDataRepository.delete(productData2);
        productDataRepository.delete(productData3);
        productDataRepository.delete(productData4);
        productDataRepository.delete(productData5);
        productDataRepository.delete(productData6);
    }

    @BeforeEach
    public void setUp() {
        this.startProduct();
    }

    @AfterEach
    public void cleanUp() {
        this.deleteProduct();
    }

    @Transactional
    @Test
    void shouldReturnOneIfAlreadyExistsEan13InDatabaseBeforeSave () {
        productData2.setEan13(productData1.getEan13());
        assertEquals(1, verifyEan13OrDun14RepositoryCustom.existsByEan13OrDun14(productData1));
    }

    @Transactional
    @Test
    void shouldReturnTwoIfAlreadyExistsDun14InDatabaseBeforeSave () {
        productData4.setDun14(productData3.getDun14());
        assertEquals(2, verifyEan13OrDun14RepositoryCustom.existsByEan13OrDun14(productData4));
    }

    @Transactional
    @Test
    void shouldReturnThreeIfNotExistsEan13InDatabaseBeforeSave () {
        assertEquals(3, verifyEan13OrDun14RepositoryCustom.existsByEan13OrDun14(productData5));
    }

    @Transactional
    @Test
    void shouldReturnThreeIfNotExistsDun14InDatabaseBeforeSave () {
        assertEquals(3, verifyEan13OrDun14RepositoryCustom.existsByEan13OrDun14(productData6));
    }
}