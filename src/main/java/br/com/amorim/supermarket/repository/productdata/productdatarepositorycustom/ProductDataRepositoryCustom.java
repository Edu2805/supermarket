package br.com.amorim.supermarket.repository.productdata.productdatarepositorycustom;

import br.com.amorim.supermarket.model.productdata.ProductData;

import java.math.BigInteger;

/**
 * Interface que irá gerar a assinatura do método que irá criar o código interno
 * do produto no momento do cadastro
 */
public interface ProductDataRepositoryCustom {

    /**
     * Método que gera um código interno com base em um código já existente ou não na base
     * @param productData produto cadastrado
     * @return código já existente ou não na base
     */
    BigInteger generateInternalCode(ProductData productData);
}
