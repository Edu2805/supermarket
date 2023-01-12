package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.service.productdata.calculatemargin.CalculateMargin;
import br.com.amorim.supermarket.service.productdata.generateinternalcode.GenerateInternalCode;
import br.com.amorim.supermarket.service.productdata.validateean13anddun14.ValidateProductEan13OrDun14;
import br.com.amorim.supermarket.service.productdata.validateproviderproduct.ValidateProductProviderProduct;
import br.com.amorim.supermarket.service.productdata.validatesubsection.ValidateProductSubSection;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductDataCrudServiceImplTest {

    @InjectMocks
    private ProductDataCrudServiceImpl productDataCrudService;
    @Mock
    private ProductDataRepository productDataRepositoryMock;
    @Mock
    private ValidateProductEan13OrDun14 validateEan13OrDun14Mock;
    @Mock
    private ValidateProductSubSection validateProductSubSectionMock;
    @Mock
    private ValidateProductProviderProduct validateProductProviderProductMock;
    @Mock
    private CalculateMargin calculateMarginMock;
    @Mock
    private GenerateInternalCode generateInternalCodeMock;

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
    void shouldFindByIdWithSuccess() {
        var knownId = productData.getId();
        when(productDataRepositoryMock.findById(knownId))
                .thenReturn(Optional.of(productData));

        var productDataFound= productDataCrudService.findById(knownId);

        assertEquals(knownId, productDataFound.getId());
    }

    @Test
    void shouldShowANotFoundExceptionMessageWhenFindByIdWithIncorrectId() {
        String messageError = getString(MessagesKeyType.PRODUCT_DATA_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";

        when(productDataRepositoryMock.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(productData));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    productDataCrudService.findById(productData.getId());
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () -> productDataCrudService.findById(productData.getId()));
    }

    @Test
    void shouldSaveWithSuccess() {
        when(calculateMarginMock.calculate(productData)).thenReturn(BigDecimal.valueOf(0.555));
        when(generateInternalCodeMock.generate(productData)).thenReturn(BigInteger.valueOf(1));
        when(productDataRepositoryMock.save(productData)).thenReturn(productData);

        var saveProduct = productDataCrudService.save(productData);
        assertNotNull(saveProduct);
        assertEquals(ProductData.class, saveProduct.getClass());
        assertEquals(UUID_1, saveProduct.getId());
        assertEquals(NAME, saveProduct.getName());
    }

    @Test
    void shouldUpdateWithSuccess() {
        var knownId = productData.getId();
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);
        when(productDataRepositoryMock.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(productData));
        when(calculateMarginMock.calculate(productData))
                .thenReturn(BigDecimal.valueOf(0.555));
        when(generateInternalCodeMock.generate(productData))
                .thenReturn(BigInteger.valueOf(1));
        when(productDataRepositoryMock.save(productData))
                .thenReturn(productData);

        productDataCrudService.update(productData, knownId);
        assertEquals(knownId, knownIdCapture.getValue());
        assertEquals(productData, productDataRepositoryMock.save(productData));
        assertEquals(productData.getInternalCode(),
                productDataRepositoryMock.save(productData).getInternalCode());
        assertEquals(productData.getClass(), productDataRepositoryMock.save(productData).getClass());
    }

    @Test
    void shouldNotUpdateWhenIdIsIncorrect() {
        String messageError = getString(MessagesKeyType.PRODUCT_DATA_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
        when(productDataRepositoryMock.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(productData));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    productDataCrudService.update(productData, productData.getId());
                }
        ).getMessage();

        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                productDataCrudService.update(productData, productData.getId()));
    }

    @Test
    void shouldDeleteWhenIdIsCorrect() {
        var knownId = productData.getId();
        when(productDataRepositoryMock.findById(knownId))
                .thenReturn(Optional.of(productData));
        doNothing().when(productDataRepositoryMock).delete(productData);

        productDataCrudService.delete(knownId);

        verify(productDataRepositoryMock, times(1)).delete(productData);
    }

    @Test
    void shouldBeExactlyIdPassedAsAnArgumentWhenDelete() {
        var knownId = productData.getId();
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);

        when(productDataRepositoryMock.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(productData));
        doNothing().when(productDataRepositoryMock).delete(productData);

        productDataCrudService.delete(knownId);

        assertEquals(knownId, knownIdCapture.getValue());
    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        String messageError = getString(MessagesKeyType.PRODUCT_DATA_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
        when(productDataRepositoryMock.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(productData));
        doNothing().when(productDataRepositoryMock).delete(productData);

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    productDataCrudService.delete(productData.getId());
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () -> productDataCrudService.delete(productData.getId()));
        verify(productDataRepositoryMock, times(0)).delete(productData);
    }
}