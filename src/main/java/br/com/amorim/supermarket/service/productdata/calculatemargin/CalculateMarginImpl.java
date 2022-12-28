package br.com.amorim.supermarket.service.productdata.calculatemargin;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CalculateMarginImpl implements CalculateMargin{

    /**
     * ML = Margem de Lucro
     * C = Custos
     * D = Despesas
     * P = Preço do produto ou serviço
     * ML = {[P – (C + D)] / P]} x 100
     * A margem será salva no banco de dados em formato decimal
     * @param productData produto no qual vai ser calculado a margem
     * @return a margem de lucro do produto
     */
    @Override
    public BigDecimal calculate(ProductData productData) {
        BigDecimal subtractValue = productData.getSalePrice().subtract(productData.getPurchasePrice());
        return subtractValue.divide(productData.getSalePrice(), 4, RoundingMode.HALF_UP);
    }
}
