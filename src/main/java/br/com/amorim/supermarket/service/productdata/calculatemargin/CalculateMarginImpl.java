package br.com.amorim.supermarket.service.productdata.calculatemargin;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor

@Component
public class CalculateMarginImpl implements CalculateMargin {

    private static final int SCALE = 4;
    private ProductDataRepository productDataRepository;

    /**
     * ML = Margem de Lucro
     * C = Custos do produto
     * P = Preço do produto
     * ML = [P – C] / P]
     * @param productData produto no qual vai ser calculado a margem
     * @return a margem de lucro do produto
     */
    @Override
    public BigDecimal calculateWhenInsert(ProductData productData) {
        BigDecimal margin = productData.getSalePrice().subtract(productData.getPurchasePrice());
        return margin.divide(productData.getSalePrice(), SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateWhenUpdate(ProductData productData) {
        ProductData product = findProduct(productData);
        BigDecimal margin = product.getSalePrice().subtract(product.getPurchasePrice());
        return margin.divide(product.getSalePrice(), SCALE, RoundingMode.HALF_UP);
    }

    private ProductData findProduct(ProductData productData) {
        productDataRepository.findById(productData.getId()).ifPresent(product -> {
            if (productData.getSalePrice() == null) {
                productData.setSalePrice(product.getSalePrice());
            }
            if (productData.getPurchasePrice() == null) {
                productData.setPurchasePrice(product.getPurchasePrice());
            }
        });
        return productData;
    }
}
