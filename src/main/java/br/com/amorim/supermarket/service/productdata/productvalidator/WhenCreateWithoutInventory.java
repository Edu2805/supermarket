package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

/**
 * Classe que implementa a validação se o inventory é null
 */

@Component("validateInventory")
public class WhenCreateWithoutInventory implements ProductValitadorImpl {

    /**
     * Implementa a lógica onde verifica se o inventory de ProductData é igual a null
     * @param productData produto cadastrado
     * @return retorna true caso o inventory de ProductData for igual a null
     */
    @Override
    public boolean validate(ProductData productData) {
        return productData.getInventory() == null;
    }
}
