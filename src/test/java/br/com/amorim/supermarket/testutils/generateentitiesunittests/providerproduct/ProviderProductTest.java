package br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct;

import br.com.amorim.supermarket.common.enums.SubscriptionType;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;

import java.math.BigInteger;
import static java.util.UUID.randomUUID;
public class ProviderProductTest {

    public java.util.UUID UUID = randomUUID();
    public String NAME = "Fornecedor teste";

    public ProviderProduct generateProvider() {
        ProviderProduct providerProduct = new ProviderProduct();
        providerProduct.setId(UUID);
        providerProduct.setCode(BigInteger.ONE);
        providerProduct.setName(NAME);
        providerProduct.setPhone("48999999999");
        providerProduct.setAddress("Avenida dos Testes, 666, Cidade dos Testes");
        providerProduct.setMunicipalRegistration("12345-A");
        providerProduct.setStateRegistration("54321-B");
        providerProduct.setResponsible("Senhor Teste");
        providerProduct.setSubscriptionType(SubscriptionType.CNPJ);
        providerProduct.setSubscriptionNumber("12345678000112");
        return providerProduct;
    }
}
