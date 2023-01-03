package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

/**
 * Classe que implementa a validação se o EAN 13 ou DUN 14 estão preenchidos
 */

@Component("validateEAN13OrDUN14")
public class WhenCreateWithoutEAN13OrDUN14 implements ProductValitadorImpl {

    /**
     * Implementa a lógica onde verifica se o EAN 13 ou DUN 14 estão preenchidos
     * Um produto deve ter ou um EAN 13 ou um DUN 14
     * @param productData produto cadastrado
     * @return retorna true caso o EAN 13 ou DUN 14 for igual a null
     */
    @Override
    public boolean validate(ProductData productData) {
        return productData.getEan13() == null && productData.getDun14() == null;
    }
}
