package br.com.amorim.supermarket.service.goodsreceipt.changepurchaseamount;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.service.productdata.calculatemargin.CalculateMargin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ChangeProductPurchaseAmountImpl implements ChangeProductPurchaseAmount {

    private ProductDataRepository productDataRepository;
    private CalculateMargin calculateMargin;

    @Override
    public void changePurchasePrice(GoodsReceipt goodsReceipt) {
        goodsReceipt.getProductDataList().forEach(receiptProduct -> {
            var findProducts = productDataRepository.findById(receiptProduct.getId());
            findProducts.map(productData -> {
                productData.setPurchasePrice(receiptProduct.getPurchasePrice());
                productData.setInventory(receiptProduct.getInventory());
                var calculateMargim = calculateMargin.calculateWhenInsert(receiptProduct);
                productData.setMargin(calculateMargim);
                productDataRepository.save(productData);
                return receiptProduct;
            });
        });
    }
}
