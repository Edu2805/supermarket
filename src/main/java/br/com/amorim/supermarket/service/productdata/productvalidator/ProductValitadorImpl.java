package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.model.productdata.ProductData;

/**
 * Interface que assina o método para os validadores de ProductData
 */
public interface ProductValitadorImpl {

    /**
     * Assinatura para a implementação dos validadores de ProductData
     * @param productData produto cadastrado
     * @return true caso ProductData possua algum campo sem preenchimento
     */
    boolean validate (ProductData productData);
}
