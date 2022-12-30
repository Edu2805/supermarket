package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

/**
 * Classe que implementa a validação se a sub-seção é null
 */
@Component
public class WhenCreateWithoutSubSectionImpl implements WhenCreateWithoutSubSection {

    /**
     * Implementa a lógica onde verifica se a sub-seção de ProductData é diferente de null
     * @param productData produto cadastrado
     * @return true caso a sub-seção for diferente de null
     */
    @Override
    public boolean validate(ProductData productData) {
        return productData.getSubSection() != null;
    }
}
