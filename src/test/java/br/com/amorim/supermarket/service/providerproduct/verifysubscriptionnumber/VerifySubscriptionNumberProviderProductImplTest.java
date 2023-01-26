package br.com.amorim.supermarket.service.providerproduct.verifysubscriptionnumber;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.repository.providerproduct.verifysubscriptionnumberrepositorycustom.VerifySubscriptionNumberProviderProductRepositoryCustom;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCNPJ;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCPF;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class VerifySubscriptionNumberProviderProductImplTest {

    @InjectMocks
    private VerifySubscriptionNumberProviderProductImpl verifySubscriptionNumberProviderProduct;
    @Mock
    private VerifySubscriptionNumberProviderProductRepositoryCustom verifySubscriptionNumberCustom;
    @Mock
    private ProviderProductRepository providerProductRepository;

    private GenerateCPF generateCPF;
    private GenerateCNPJ generateCNPJ;
    private ProviderProduct providerProduct1;
    private ProviderProduct providerProduct2;

    private void startProvider () {
        generateCPF = new GenerateCPF();
        generateCNPJ = new GenerateCNPJ();
        ProviderProductTest providerProductTest1 = new ProviderProductTest();
        providerProduct1 = providerProductTest1.generateProvider();

        generateCPF = new GenerateCPF();
        generateCNPJ = new GenerateCNPJ();
        ProviderProductTest providerProductTest2 = new ProviderProductTest();
        providerProduct2 = providerProductTest2.generateProvider();
    }

    @BeforeEach
    void setUp() {
        startProvider();
    }

    @Test
    void shouldReturnFalseWhenSubscriptionNumberIsValidBeforeSave() {
        when(verifySubscriptionNumberCustom.existsBySubscriptionNumber(providerProduct1))
                .thenReturn(false);

        var verifySubscriptionNumber = verifySubscriptionNumberProviderProduct
                .verifySubscriptionNumberBeforeSave(providerProduct1);

        assertFalse(verifySubscriptionNumber);
    }

    @Test
    void shouldReturnBusinessRuleExceptionMessageWhenSubscriptionNumberIsNotValidBeforeSave() {
        String messageError = getString(MessagesKeyType
                .PROVIDER_PRODUCT_SUBSCRIPTION_NUMBER_ALREADY_EXISTS.message);

        when(verifySubscriptionNumberCustom.existsBySubscriptionNumber(providerProduct1))
                .thenReturn(true);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifySubscriptionNumberProviderProduct
                            .verifySubscriptionNumberBeforeSave(providerProduct1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifySubscriptionNumberProviderProduct
                        .verifySubscriptionNumberBeforeSave(providerProduct1));
    }

    @Test
    void shouldReturnFalseWhenSubscriptionNumberIsDifferentFromNullAndSubscriptionNumberNoLongerExistsBeforeUpdate() {
        List<ProviderProduct> providerProductList = new ArrayList<>();
        providerProductList.add(providerProduct1);
        providerProductList.add(providerProduct2);

        when(providerProductRepository.findAll()).thenReturn(providerProductList);

        var verifySubscriptionNumber = verifySubscriptionNumberProviderProduct
                .verifySubscriptionNumberBeforeUpdate(providerProduct1);

        assertFalse(verifySubscriptionNumber);
    }

    @Test
    void shouldReturnABusinessRuleExceptionMessageWhenSubscriptionNumberIsEqualsAndIdNoLongerExistsBeforeUpdate() {
        String messageError = getString(MessagesKeyType
                .PROVIDER_PRODUCT_SUBSCRIPTION_NUMBER_ALREADY_EXISTS_WHEN_UPDATE.message);
        List<ProviderProduct> providerProductList = new ArrayList<>();
        providerProduct1.setSubscriptionNumber(providerProduct2.getSubscriptionNumber());
        providerProductList.add(providerProduct1);
        providerProductList.add(providerProduct2);

        when(providerProductRepository.findAll()).thenReturn(providerProductList);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifySubscriptionNumberProviderProduct
                            .verifySubscriptionNumberBeforeUpdate(providerProduct1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifySubscriptionNumberProviderProduct
                        .verifySubscriptionNumberBeforeUpdate(providerProduct1));
    }
}