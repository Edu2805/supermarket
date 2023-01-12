package br.com.amorim.supermarket.service.productdata.validatesubsection;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ValidateProductSubSectionImplTest {

    @InjectMocks
    private ValidateProductSubSectionImpl validateProductSubSection;
    @Mock
    private SubSectionRepository subSectionRepositoryMock;

    public static final java.util.UUID UUID_1 = randomUUID();
    public static final String NAME = "Produto teste";

    private ProductData productData;
    ProviderProductTest providerProductTest = new ProviderProductTest();
    SubSectionTest subSectionTest = new SubSectionTest();

    private void startProduct () {
        productData = new ProductData();
        productData.setId(UUID_1);
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
    void shouldReturnNonExistentSubsectionMessageWhenSaved() {
        String messageError = getString(MessagesKeyType.PRODUCT_DATA_SUB_SECTION_NON_EXISTENT.message);
        var invalidId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";

        when(subSectionRepositoryMock.findById(UUID.fromString(invalidId)))
                .thenReturn(Optional.of(productData.getSubSection()));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    validateProductSubSection.validate(productData);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () -> validateProductSubSection.validate(productData));
    }

    @Test
    void shouldReturnFalseWhenSubsectionIdIsCorrect() {
        when(subSectionRepositoryMock.findById(productData.getSubSection().getId()))
                .thenReturn(Optional.of(productData.getSubSection()));

        var validate = validateProductSubSection.validate(productData);

        assertFalse(validate);
    }
}