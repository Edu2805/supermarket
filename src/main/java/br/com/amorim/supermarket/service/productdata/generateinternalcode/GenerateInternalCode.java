package br.com.amorim.supermarket.service.productdata.generateinternalcode;

import br.com.amorim.supermarket.model.productdata.ProductData;

import java.math.BigInteger;

/**
 * Interface que irá criar a abstração para a geração do código interno do produto.
 * Em sua implementação, essa interface será injetada para chamar o método que
 * gera o código interno.
 */

public interface GenerateInternalCode {

    BigInteger generate (ProductData productData);
}
