package br.com.amorim.supermarket.service.productdata.validateean13anddun14;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.repository.productdata.verifyean13ordun14repositorycustom.VerifyEan13OrDun14RepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ProductValidatorEan13OrDun14ImplTest {

    @InjectMocks
    private ValidateProductEan13OrDun14Impl productValidatorEan13OrDun14;
    @Mock
    private VerifyEan13OrDun14RepositoryCustom verifyEan13OrDun14RepositoryCustomMock;
    @Mock
    private ProductDataRepository productDataRepository;

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
    void shouldReturnABusinessExceptionMessageWhenEan13IsNullBeforeSave() {
        productData.setEan13(null);
        String messageError = getString(MessagesKeyType.PRODUCT_DATA_EAN13_OR_DUN14_EMPTY.message);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    productValidatorEan13OrDun14.validateBeforeSaveAndUpdate(productData);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () -> productValidatorEan13OrDun14.validateBeforeSaveAndUpdate(productData));
    }

    @Test
    void shouldReturnABusinessExceptionMessageWhenDun14IsNullBeforeSave() {
        productData.setEan13(null);
        productData.setDun14(null);
        String messageError = getString(MessagesKeyType.PRODUCT_DATA_EAN13_OR_DUN14_EMPTY.message);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    productValidatorEan13OrDun14.validateBeforeSaveAndUpdate(productData);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () -> productValidatorEan13OrDun14.validateBeforeSaveAndUpdate(productData));
    }

    @Test
    void shouldReturnABusinessExceptionMessageWhenEan13AndDun14IsDifferentOfNullBeforeSave() {
        productData.setDun14("17893546701265");
        String messageError = getString(MessagesKeyType.PRODUCT_DATA_EAN13_OR_DUN14_SAVE_TOGETHER.message);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    productValidatorEan13OrDun14.validateBeforeSaveAndUpdate(productData);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () -> productValidatorEan13OrDun14.validateBeforeSaveAndUpdate(productData));
    }

    @Test
    void shouldReturnFalseWhenEan13IsDifferentOfNullBeforeSave() {
        productData.setDun14(null);
        var validateEan13 = productValidatorEan13OrDun14.validateBeforeSaveAndUpdate(productData);
        assertFalse(validateEan13);
    }

    @Test
    void shouldReturnFalseWhenDun14IsDifferentOfNullBeforeSave() {
        productData.setEan13(null);
        productData.setDun14("17893546701265");
        var validateEan13 = productValidatorEan13OrDun14.validateBeforeSaveAndUpdate(productData);
        assertFalse(validateEan13);
    }

    @Test
    void shouldReturnABusinessExceptionMessageWhenEan13IsNullBeforeUpdate() {
        productData.setEan13(null);
        String messageError = getString(MessagesKeyType.PRODUCT_DATA_EAN13_OR_DUN14_EMPTY.message);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    productValidatorEan13OrDun14.validateBeforeUpdate(productData);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () -> productValidatorEan13OrDun14.validateBeforeUpdate(productData));
    }

    @Test
    void shouldReturnABusinessExceptionMessageWhenDun14IsNullBeforeUpdate() {
        productData.setEan13(null);
        productData.setDun14(null);
        String messageError = getString(MessagesKeyType.PRODUCT_DATA_EAN13_OR_DUN14_EMPTY.message);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    productValidatorEan13OrDun14.validateBeforeUpdate(productData);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () -> productValidatorEan13OrDun14.validateBeforeUpdate(productData));
    }

    @Test
    void shouldReturnABusinessExceptionMessageWhenEan13AndDun14IsDifferentOfNullBeforeUpdate() {
        productData.setDun14("17893546701265");
        String messageError = getString(MessagesKeyType.PRODUCT_DATA_EAN13_OR_DUN14_SAVE_TOGETHER.message);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    productValidatorEan13OrDun14.validateBeforeUpdate(productData);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () -> productValidatorEan13OrDun14.validateBeforeUpdate(productData));
    }

    @Test
    void shouldReturnFalseWhenEan13IsDifferentOfNullBeforeUpdate() {
        productData.setDun14(null);
        var validateEan13 = productValidatorEan13OrDun14.validateBeforeUpdate(productData);
        assertFalse(validateEan13);
    }

    @Test
    void shouldReturnFalseWhenDun14IsDifferentOfNullBeforeUpdate() {
        productData.setEan13(null);
        productData.setDun14("17893546701265");
        var validateEan13 = productValidatorEan13OrDun14.validateBeforeUpdate(productData);
        assertFalse(validateEan13);
    }
}