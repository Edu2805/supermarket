package br.com.amorim.supermarket.service.goodsissue.registerproduct;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor

@Component
public class RegisterProductImpl implements RegisterProduct {

    private ProductDataRepository productDataRepository;

    @Override
    public void register(GoodsIssue goodsIssue) {
        goodsIssue.getProductDataList().forEach(product -> {
            var findProducts = productDataRepository.findById(product.getId());
            findProducts.map(productData -> {
                var updatedStock = subtractStock(productData, product);
                productData.setInventory(updatedStock);
                productDataRepository.save(productData);
                return product;
            });
        });
    }

    private BigDecimal subtractStock(ProductData findProductData, ProductData productData) {
        return findProductData.getInventory().subtract(productData.getInventory());
    }
}