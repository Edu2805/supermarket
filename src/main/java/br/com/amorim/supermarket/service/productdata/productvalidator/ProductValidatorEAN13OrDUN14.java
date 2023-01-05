package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;

/**
 * Interface que assina o método para o validador dos campos EAN 13 e DUN 14
 */
public interface ProductValidatorEAN13OrDUN14 {

    /**
     * Assinatura para a implementação do validador dos campos EAN 13 e DUN 14
     * @param productData produto cadastrado
     * @return true caso ProductData possua algum campo sem preenchimento
     */
    boolean validate (ProductData productData);
}
