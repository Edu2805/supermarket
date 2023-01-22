package br.com.amorim.supermarket.service.providerproduct.validatedocument.cnpj;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.enums.SubscriptionType;
import br.com.amorim.supermarket.common.exception.invaliddocument.InvalidDocumentException;
import br.com.amorim.supermarket.common.verifydocument.cnpj.VerifyCNPJ;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCNPJ;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ValidateCnpjDocumentImplTest {

    @InjectMocks
    private ValidateCnpjDocumentProviderProductImpl validateCnpjDocument;
    @Mock
    private VerifyCNPJ verifyCNPJMock;
    private ProviderProduct providerProduct;
    private GenerateCNPJ generateCNPJ;
    public static final java.util.UUID UUID_1 = randomUUID();

    private void startProvider () {
        ProviderProductTest providerProductTest = new ProviderProductTest();
        generateCNPJ = new GenerateCNPJ();
        providerProduct = providerProductTest.generateProvider();
    }

    @BeforeEach
    void setUp() {
        startProvider();
    }

    @Test
    void shouldReturnAExceptionWhenCnpjIsInvalid() {
        String messageError = getString(MessagesKeyType.PROVIDER_PRODUCT_INCORRECT_CNPJ_NUMBER.message);
        providerProduct.setSubscriptionType(SubscriptionType.CNPJ);
        providerProduct.setSubscriptionNumber("12345678000134");

        String exceptionMessage = Assertions.assertThrows(
                InvalidDocumentException.class, () -> {
                    validateCnpjDocument.isCnpj(providerProduct);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(InvalidDocumentException.class, () -> validateCnpjDocument.isCnpj(providerProduct));

    }

    @Test
    void shouldValidateWithSuccessWhenCnpjIsValid() {
        providerProduct.setSubscriptionType(SubscriptionType.CNPJ);
        providerProduct.setSubscriptionNumber(generateCNPJ.cnpj(false));

        when(verifyCNPJMock.isCNPJ(providerProduct.getSubscriptionNumber()))
                .thenReturn(true);
        var validate = validateCnpjDocument.isCnpj(providerProduct);

        assertFalse(validate);

    }
}