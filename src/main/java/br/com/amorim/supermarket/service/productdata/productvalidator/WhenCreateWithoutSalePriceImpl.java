package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

/**
 * Classe que implementa a validação se o sale price é null
 */

@Component("validateSalePrice")
public class WhenCreateWithoutSalePriceImpl implements ProductValitador{

    /**
     * Implementa a lógica onde verifica se o sale price de ProductData é igual a null
     * @param productData produto cadastrado
     * @return retorna true caso o sale price de ProductData for igual a null
     */
    @Override
    public boolean validate(ProductData productData) {
        return productData.getSalePrice() == null;
    }
}
