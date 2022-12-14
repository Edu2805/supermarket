package br.com.amorim.supermarket.service.productdata.calculatemargin;

import br.com.amorim.supermarket.model.productdata.ProductData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor

@Component
public class CalculateMarginImpl implements CalculateMargin {

    /**
     * ML = Margem de Lucro
     * C = Custos do produto
     * P = Preço do produto
     * ML = [P – C] / P]
     * @param productData produto no qual vai ser calculado a margem
     * @return a margem de lucro do produto
     */
    @Override
    public BigDecimal calculate(ProductData productData) {
        BigDecimal margin = productData.getSalePrice().subtract(productData.getPurchasePrice());
        return margin.divide(productData.getSalePrice(), 4, RoundingMode.HALF_UP);
    }
}
