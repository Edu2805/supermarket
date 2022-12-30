package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;

/**
 * Assinatura para validação do tipo de unidade de medida do produto
 */
public interface WhenCreateWithoutUnity {

    /**
     * Valida se a tipo de unidade de medida do produto não está sendo preenchido
     * @param productData produto cadastrado
     * @return true se productData.getUnity() for diferente de null
     */
    boolean validate(ProductData productData);
}
