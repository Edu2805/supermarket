package br.com.amorim.supermarket.service.productdata.validateproviderproduct;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class ValidateProductProviderProductImplTest {

    @InjectMocks
    private ValidateProductProviderProductImpl validateProductProviderProduct;
    @Mock
    private ProviderProductRepository providerProductRepositoryMock;

    public static final java.util.UUID UUID_PRODUCT = randomUUID();
    public static final java.util.UUID UUID_PROVIDER = randomUUID();
    public static final String NAME = "Produto teste";

    private ProductData productData;
    ProviderProductTest providerProductTest = new ProviderProductTest();
    SubSectionTest subSectionTest = new SubSectionTest();

    private void startProduct () {
        productData = new ProductData();
        productData.setId(UUID_PRODUCT);
        productData.setName(NAME);
        productData.setUnity(UnityType.UNITY);
        productData.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData.setSalePrice(BigDecimal.valueOf(15.90));
        productData.setEan13("7891112223334");
        productData.setInternalCode(BigInteger.valueOf(1));
        productData.setInventory(BigDecimal.valueOf(100));
        productData.setProviderProduct(providerProductTest.generateProvider());
        productData.setSubSection(subSectionTest.generateSubsection());
        productData.setSubSection(subSectionTest.generateSubsection());
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startProduct();
    }

    @Test
    void shouldReturnNonExistentProviderProductMessageWhenSaved() {
        String messageError = getString(MessagesKeyType.PRODUCT_DATA_PROVIDER_PRODUCT_NON_EXISTENT.message);

        when(providerProductRepositoryMock.findById(UUID_PROVIDER))
                .thenReturn(Optional.of(productData.getProviderProduct()));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    validateProductProviderProduct.validate(productData);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () -> validateProductProviderProduct.validate(productData));
    }

    @Test
    void shouldReturnFalseWhenProviderProductIdIsCorrect() {
        when(providerProductRepositoryMock.findById(productData.getSubSection().getId()))
                .thenReturn(Optional.of(productData.getProviderProduct()));

        var validate = validateProductProviderProduct.validate(productData);

        assertFalse(validate);
    }
}