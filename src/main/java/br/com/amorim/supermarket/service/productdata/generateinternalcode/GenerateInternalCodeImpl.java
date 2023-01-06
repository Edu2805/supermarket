package br.com.amorim.supermarket.service.productdata.generateinternalcode;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.generateinternalcoderepositorycustom.GenerateInternalCodeRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * Classe que irá implementar a geração do código interno
 */

@AllArgsConstructor

@Component
public class GenerateInternalCodeImpl implements GenerateInternalCode {

    private GenerateInternalCodeRepositoryCustom productDataRepositoryCustom;

    /**
     * Método que irá implementar a geração do código imterno do produto
     * @param productData produto cadastrado
     * @return código interno do produto
     */
    @Override
    public BigInteger generate(ProductData productData) {
        return productDataRepositoryCustom.generateInternalCode(productData);
    }
}
