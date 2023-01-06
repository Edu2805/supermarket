package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.verifyean13ordun14.VerifyEan13OrDun14RepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductValidatorEan13OrDun14ImplTest {

    @InjectMocks
    private ProductValidatorEan13OrDun14Impl productValidatorEan13OrDun14;
    @Mock
    private VerifyEan13OrDun14RepositoryCustom verifyEan13OrDun14RepositoryCustomMock;

    public static final java.util.UUID UUID = randomUUID();
    public static final String NAME = "Produto teste 3";

    private ProductData productData;

    void startProduct() {
        ProviderProductTest providerProductTest = new ProviderProductTest();
        SubSectionTest subSectionTest = new SubSectionTest();
        productData = new ProductData();
        productData.setId(UUID);
        productData.setName(NAME);
        productData.setUnity(UnityType.UNITY);
        productData.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData.setSalePrice(BigDecimal.valueOf(15.90));
        productData.setEan13("7891112223334");
        productData.setInventory(BigDecimal.valueOf(100));
        productData.setProviderProduct(providerProductTest.generateProvider());
        productData.setSubSection(subSectionTest.generateSubsection());
    }

    @BeforeEach
    void setUp() {
        startProduct();
    }

    @Test
    void shouldReturnABusinessExceptionMessageWhenEan13IsNull() {
        productData.setEan13(null);
        String messageError = "Não é possível cadastrar um produto sem um EAN 13 ou um DUN 14.";

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    productValidatorEan13OrDun14.validate(productData);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () -> productValidatorEan13OrDun14.validate(productData));
    }

    @Test
    void shouldReturnABusinessExceptionMessageWhenDun14IsNull() {
        productData.setEan13(null);
        productData.setDun14(null);
        String messageError = "Não é possível cadastrar um produto sem um EAN 13 ou um DUN 14.";

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    productValidatorEan13OrDun14.validate(productData);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () -> productValidatorEan13OrDun14.validate(productData));
    }

    @Test
    void shouldReturnABusinessExceptionMessageWhenEan13AndDun14IsDifferentOfNull() {
        productData.setDun14("17893546701265");
        String messageError = "Não é possível cadastrar um produto com EAN 13 e DUN 14 juntos. Deve haver ou um EAN 13 ou um DUN 14";

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    productValidatorEan13OrDun14.validate(productData);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () -> productValidatorEan13OrDun14.validate(productData));
    }

    @Test
    void shouldReturnFalseWhenEan13IsDifferentOfNull() {
        productData.setDun14(null);
        var validateEan13 = productValidatorEan13OrDun14.validate(productData);
        assertFalse(validateEan13);
    }

    @Test
    void shouldReturnFalseWhenDun14IsDifferentOfNull() {
        productData.setEan13(null);
        productData.setDun14("17893546701265");
        var validateEan13 = productValidatorEan13OrDun14.validate(productData);
        assertFalse(validateEan13);
    }
}