package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;

/**
 * Assinatura para validação do sub-seção do produto
 */

public interface WhenCreateWithoutSubSection {

    /**
     * Valida se a sub-seção do produto não está sendo preenchido
     * @param productData produto cadastrado
     * @return true se productData.getProvider() for diferente de null
     */
    boolean validate(ProductData productData);
}
