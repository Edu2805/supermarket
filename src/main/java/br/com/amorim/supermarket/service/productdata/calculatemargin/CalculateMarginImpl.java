package br.com.amorim.supermarket.service.productdata.calculatemargin;

import br.com.amorim.supermarket.model.productdata.ProductData;

import java.math.BigDecimal;

/**
 * Interface que irá calcular a margem de lucro do produto com base em seu
 * preço de compra x preço de venda
 */
public interface CalculateMarginImpl {

    /**
     * Assinatura para o método que vai calcular a margem de lucro de um
     * determinado produto
     * @param productData produto cadastrado
     * @return retorna a margem em formato decimal (sem o formato %)
     */
    BigDecimal calculate (ProductData productData);
}
