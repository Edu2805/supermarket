package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;

/**
 * Assinatura para validação do fornecedor do produto
 */

public interface WhenCreateWithoutProvider {

    /**
     * Valida se o fornecedor do produto não está sendo preenchido
     * @param productData produto cadastrado
     * @return true se productData.getName() for diferente de null
     */
    boolean validate(ProductData productData);
}
