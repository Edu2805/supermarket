package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

/**
 * Classe que implementa a validação se o nome é null
 */

@Component("validateName")
public class WhenCreateWithoutNameImpl implements ProductValitador {

    /**
     * Implementa a lógica onde verifica se o name de ProductData é igual a null
     * @param productData produto cadastrado
     * @return retorna true caso o name de ProductData for igual a null
     */
    @Override
    public boolean validate(ProductData productData) {
        return productData.getName() == null;
    }
}
