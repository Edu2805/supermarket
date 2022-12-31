package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

/**
 * Classe que implementa a validação se o fornecedor é null
 */

@Component("validateProvider")
public class WhenCreateWithoutProviderImpl implements ProductValitador {

    /**
     * Implementa a lógica onde verifica se o fornecedor de ProductData é diferente de null
     * @param productData produto cadastrado
     * @return true caso o fornecedor for diferente de null
     */
    @Override
    public boolean validate(ProductData productData) {
        return productData.getProviderProduct() == null;
    }
}
