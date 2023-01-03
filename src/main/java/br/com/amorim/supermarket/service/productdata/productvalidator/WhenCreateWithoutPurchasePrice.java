package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

/**
 * Classe que implementa a validação se o purchase price é null
 */

@Component("validatePurchasePrice")
public class WhenCreateWithoutPurchasePrice implements ProductValitadorImpl {

    /**
     * Implementa a lógica onde verifica se o purchase price de ProductData é igual a null
     * @param productData produto cadastrado
     * @return retorna true caso o purchase price de ProductData for igual a null
     */
    @Override
    public boolean validate(ProductData productData) {
        return productData.getPurchasePrice() == null;
    }
}
