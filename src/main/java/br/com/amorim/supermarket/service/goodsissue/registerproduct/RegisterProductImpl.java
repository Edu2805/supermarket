package br.com.amorim.supermarket.service.goodsissue.registerproduct;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static java.lang.String.format;

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
        var inventoryUpdated = findProductData.getInventory().subtract(productData.getInventory());
        if (inventoryUpdated.compareTo(BigDecimal.ZERO) < 0) {
            if (findProductData.getDun14() == null) {
                throw new NotFoundException(format(getString(MessagesKeyType.GOODS_ISSUE_PRODUCT_DATA_INSUFFICIENT_STOCK.message),
                        findProductData.getName(), findProductData.getEan13()));
            }
            throw new NotFoundException(format(getString(MessagesKeyType.GOODS_ISSUE_PRODUCT_DATA_INSUFFICIENT_STOCK.message),
                    findProductData.getName(), findProductData.getDun14()));
        }
        return findProductData.getInventory().subtract(productData.getInventory());
    }
}