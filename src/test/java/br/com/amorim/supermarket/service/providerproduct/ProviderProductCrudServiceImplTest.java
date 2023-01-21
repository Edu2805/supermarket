package br.com.amorim.supermarket.service.providerproduct;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.enums.SubscriptionType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.service.providerproduct.generateinternalcode.GenerateInternalCodeProviderProduct;
import br.com.amorim.supermarket.service.providerproduct.validatedocument.cei.ValidateCeiDocument;
import br.com.amorim.supermarket.service.providerproduct.validatedocument.cnpj.ValidateCnpjDocument;
import br.com.amorim.supermarket.service.providerproduct.validatedocument.cpf.ValidateCpfDocument;
import br.com.amorim.supermarket.service.providerproduct.verifymunicipalorstateregistration.VerifyMunicipalOrStateRegistration;
import br.com.amorim.supermarket.service.providerproduct.verifysubscriptionnumber.VerifySubscriptionNumber;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCNPJ;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCPF;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProviderProductCrudServiceImplTest {

    @InjectMocks
    ProviderProductCrudServiceImpl providerProductCrudService;
    @Mock
    private ProviderProductRepository providerProductRepositoryMock;
    @Mock
    private GenerateInternalCodeProviderProduct generateInternalCodeProviderProductMock;
    @Mock
    private ValidateCnpjDocument validateCnpjDocumentMock;
    @Mock
    private ValidateCpfDocument validateCpfDocumentMock;
    @Mock
    private ValidateCeiDocument validateCeiDocumentMock;
    @Mock
    private VerifyMunicipalOrStateRegistration verifyMunicipalRegistrationMock;
    @Mock
    private VerifySubscriptionNumber verifySubscriptionNumberMock;
    @Mock
    private VerifyPageSize verifyPageSizeMock;

    private GenerateCPF generateCPF;
    private GenerateCNPJ generateCNPJ;
    private ProviderProduct providerProduct;

    private void startProvider () {
        generateCPF = new GenerateCPF();
        generateCNPJ = new GenerateCNPJ();
        ProviderProductTest providerProductTest = new ProviderProductTest();
        providerProduct = providerProductTest.generateProvider();
    }

    @BeforeEach
    void setUp() {
        startProvider();
    }

    @Test
    void shouldFindById() {
        var knownId = providerProduct.getId();
        when(providerProductRepositoryMock.findById(knownId))
                .thenReturn(Optional.of(providerProduct));

        var productDataFound= providerProductCrudService.findById(knownId);

        assertEquals(knownId, productDataFound.getId());
    }

    @Test
    void shouldShowANotFoundExceptionMessageWhenFindByIdWithIncorrectId() {
        String messageError = getString(MessagesKeyType.PROVIDER_PRODUCT_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";

        when(providerProductRepositoryMock.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(providerProduct));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    providerProductCrudService.findById(providerProduct.getId());
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                providerProductCrudService.findById(providerProduct.getId()));
    }

    @Test
    void shouldSaveProviderProductSubscriptionTypeCpfWithSuccess() {
        providerProduct.setSubscriptionType(SubscriptionType.CPF);
        providerProduct.setSubscriptionNumber(generateCPF.cpf(false));

        when(validateCpfDocumentMock.isCpf(providerProduct))
                .thenReturn(true);
        when(verifySubscriptionNumberMock.verifySubscriptionNumber(providerProduct))
                .thenReturn(false);
        when(providerProductRepositoryMock.save(providerProduct)).thenReturn(providerProduct);

        var saveProvider = providerProductCrudService.save(providerProduct);
        assertNotNull(saveProvider);
        assertEquals(ProviderProduct.class, saveProvider.getClass());
        assertEquals(providerProduct.getId(), saveProvider.getId());
        assertEquals(providerProduct.getName(), saveProvider.getName());
    }

    @Test
    void shouldSaveProviderProductSubscriptionTypeCnpjWithSuccess() {
        providerProduct.setSubscriptionType(SubscriptionType.CNPJ);
        providerProduct.setSubscriptionNumber(generateCNPJ.cnpj(false));

        when(validateCpfDocumentMock.isCpf(providerProduct))
                .thenReturn(true);
        when(verifySubscriptionNumberMock.verifySubscriptionNumber(providerProduct))
                .thenReturn(false);
        when(providerProductRepositoryMock.save(providerProduct)).thenReturn(providerProduct);

        var saveProvider = providerProductCrudService.save(providerProduct);
        assertNotNull(saveProvider);
        assertEquals(ProviderProduct.class, saveProvider.getClass());
        assertEquals(providerProduct.getId(), saveProvider.getId());
        assertEquals(providerProduct.getName(), saveProvider.getName());
    }

    @Test
    void shouldSaveProviderProductSubscriptionTypeCeiCnoWithSuccess() {
        providerProduct.setSubscriptionType(SubscriptionType.CEI);
        providerProduct.setSubscriptionNumber("12345678912345");

        when(validateCpfDocumentMock.isCpf(providerProduct))
                .thenReturn(true);
        when(verifySubscriptionNumberMock.verifySubscriptionNumber(providerProduct))
                .thenReturn(false);
        when(providerProductRepositoryMock.save(providerProduct)).thenReturn(providerProduct);

        var saveProvider = providerProductCrudService.save(providerProduct);
        assertNotNull(saveProvider);
        assertEquals(ProviderProduct.class, saveProvider.getClass());
        assertEquals(providerProduct.getId(), saveProvider.getId());
        assertEquals(providerProduct.getName(), saveProvider.getName());
    }

    @Test
    void shouldUpdateWithSuccess() {
        var knownId = providerProduct.getId();
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);

        when(providerProductRepositoryMock.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(providerProduct));
        when(validateCpfDocumentMock.isCpf(providerProduct))
                .thenReturn(true);
        when(verifySubscriptionNumberMock.verifySubscriptionNumber(providerProduct))
                .thenReturn(false);
        when(providerProductRepositoryMock.save(providerProduct)).thenReturn(providerProduct);

        providerProductCrudService.update(providerProduct, knownId);

        assertEquals(knownId, knownIdCapture.getValue());
        assertEquals(providerProduct.getCode(),
                providerProductRepositoryMock.save(providerProduct).getCode());
        assertEquals(providerProduct.getClass(),
                providerProductRepositoryMock.save(providerProduct).getClass());
    }

    @Test
    void shouldNotUpdateWhenIdIsIncorrect() {
        String messageError = getString(MessagesKeyType.PROVIDER_PRODUCT_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
        when(providerProductRepositoryMock.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(providerProduct));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    providerProductCrudService.update(
                            providerProduct, providerProduct.getId());
                }
        ).getMessage();

        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                providerProductCrudService.update(providerProduct, providerProduct.getId()));
    }

    @Test
    void shouldDeleteWhenIdIsCorrect() {
        var knownId = providerProduct.getId();
        when(providerProductRepositoryMock.findById(knownId))
                .thenReturn(Optional.of(providerProduct));
        doNothing().when(providerProductRepositoryMock).delete(providerProduct);

        providerProductCrudService.delete(knownId);

        verify(providerProductRepositoryMock,
                times(1)).delete(providerProduct);
    }

    @Test
    void shouldBeExactlyIdPassedAsAnArgumentWhenDelete() {
        var knownId = providerProduct.getId();
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);

        when(providerProductRepositoryMock.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(providerProduct));
        doNothing().when(providerProductRepositoryMock).delete(providerProduct);

        providerProductCrudService.delete(knownId);

        assertEquals(knownId, knownIdCapture.getValue());
    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        String messageError = getString(MessagesKeyType.PROVIDER_PRODUCT_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
        when(providerProductRepositoryMock.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(providerProduct));
        doNothing().when(providerProductRepositoryMock).delete(providerProduct);

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    providerProductCrudService.delete(providerProduct.getId());
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                providerProductCrudService.delete(providerProduct.getId()));
        verify(providerProductRepositoryMock,
                times(0)).delete(providerProduct);
    }
}