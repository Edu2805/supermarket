package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;

/**
 * Assinatura para validação do nome do produto
 */

public interface WhenCreateWithoutName {

    /**
     * Valida se o nome do produto não está sendo preenchido
     * @param productData produto cadastrado
     * @return true se productData.getName() for igual a null
     */
    boolean validate(ProductData productData);
}
