package br.com.amorim.supermarket.service.providerproduct.validatedocument.cpf;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.enums.SubscriptionType;
import br.com.amorim.supermarket.common.exception.invaliddocument.InvalidDocumentException;
import br.com.amorim.supermarket.common.verifydocument.cpf.VerifyCPF;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.testutils.generatedocument.GenerateCPF;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ValidateCpfDocumentImplTest {

    @InjectMocks
    private ValidateCpfDocumentProviderProductImpl validateCpfDocument;
    @Mock
    private VerifyCPF verifyCPFMock;

    private ProviderProduct providerProduct;
    private GenerateCPF generateCPF;

    private void startProvider () {
        ProviderProductTest providerProductTest = new ProviderProductTest();
        generateCPF = new GenerateCPF();
        providerProduct = providerProductTest.generateProvider();
    }

    @BeforeEach
    void setUp() {
        startProvider();
    }

    @Test
    void shouldReturnAExceptionWhenCpfIsInvalid() {
        String messageError = getString(MessagesKeyType.PROVIDER_PRODUCT_INCORRECT_CPF_NUMBER.message);
        providerProduct.setSubscriptionType(SubscriptionType.CPF);
        providerProduct.setSubscriptionNumber("12344576555");

        String exceptionMessage = Assertions.assertThrows(
                InvalidDocumentException.class, () -> {
                    validateCpfDocument.isCpf(providerProduct);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(InvalidDocumentException.class, () -> validateCpfDocument.isCpf(providerProduct));

    }

    @Test
    void shouldValidateWithSuccessWhenCpfIsValid() {
        providerProduct.setSubscriptionType(SubscriptionType.CNPJ);
        providerProduct.setSubscriptionNumber(generateCPF.cpf(false));

        when(verifyCPFMock.isCPF(providerProduct.getSubscriptionNumber()))
                .thenReturn(true);
        var validate = validateCpfDocument.isCpf(providerProduct);

        assertFalse(validate);

    }
}