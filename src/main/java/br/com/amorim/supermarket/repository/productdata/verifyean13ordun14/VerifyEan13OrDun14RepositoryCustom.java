package br.com.amorim.supermarket.repository.productdata.verifyean13ordun14;

import br.com.amorim.supermarket.model.productdata.ProductData;

/**
 * Interface que irá gerar a assinatura do método para verificar se já existe
 * um EAN 13 ou um DUN 14 na base
 */
public interface VerifyEan13OrDun14RepositoryCustom {

    /**
     * Método que chama a implementação da query que consulta na base se já
     * existe um EAN 13 ou um DUN 14
     * @param productData produto cadastrado
     */
    boolean existsByEan13OrDun14 (ProductData productData);
}
