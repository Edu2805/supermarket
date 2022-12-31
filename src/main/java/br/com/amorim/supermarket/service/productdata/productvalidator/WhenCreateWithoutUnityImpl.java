package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

/**
 * Classe que implementa a validação se o tipo de unidade de medida do produto é null
 */

@Component("validateUnity")
public class WhenCreateWithoutUnityImpl implements ProductValitador{

    /**
     * Implementa a lógica onde verifica se tipo de unidade de medida do produto é diferente de null
     * @param productData produto cadastrado
     * @return true caso o tipo de unidade de medida do produto for diferente de null
     */
    @Override
    public boolean validate(ProductData productData) {
        return productData.getUnity() == null;
    }
}
