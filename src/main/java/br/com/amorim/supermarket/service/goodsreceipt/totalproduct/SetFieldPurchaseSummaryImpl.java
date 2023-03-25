package br.com.amorim.supermarket.service.goodsreceipt.totalproduct;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import br.com.amorim.supermarket.repository.historicalgoodsreceipt.HistoricalGoodsReceiptRepository;
import br.com.amorim.supermarket.service.goodsreceipt.totalproduct.fieldsummary.PurchaseSummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor

@Component
public class SetFieldPurchaseSummaryImpl implements SetFieldPurchaseSummary {

    private PurchaseSummary purchaseSummary;
    private HistoricalGoodsReceiptRepository historicalGoodsReceiptRepository;

    @Override
    public void calculateTotalProducts(GoodsReceipt goodsReceipt) {
        var totalProducts = purchaseSummary.calculateTotalProducts(goodsReceipt);
        goodsReceipt.setProductsTotal(totalProducts);
    }

    @Override
    public void setFieldsHistoricalGoodsReceipt(GoodsReceipt goodsReceipt) {
        goodsReceipt.getProductDataList().forEach(product -> {
            HistoricalGoodsReceipt historicalGoodsReceipt = new HistoricalGoodsReceipt();
            historicalGoodsReceipt.setName(product.getName());
            historicalGoodsReceipt.setProductCode(product.getCode());
            historicalGoodsReceipt.setPurchasePrice(product.getPurchasePrice());
            historicalGoodsReceipt.setInventory(product.getInventory());
            historicalGoodsReceipt.setProviderProductName(product.getProviderProduct().getName());
            historicalGoodsReceipt.setDepartmentName(product.getSubSection().getMainSection().getDepartment().getName());
            historicalGoodsReceipt.setMainsectionName(product.getSubSection().getMainSection().getName());
            historicalGoodsReceipt.setSubsectionName(product.getSubSection().getName());
            historicalGoodsReceipt.setInvoice(goodsReceipt.getInvoice());
            historicalGoodsReceipt.setTotalInvoice(goodsReceipt.getProductsTotal());
            historicalGoodsReceipt.setRegistrationDate(goodsReceipt.getRegistrationDate());
            historicalGoodsReceipt.setReceived(goodsReceipt.isReceived());
            saveHistoricalGoodsReceipt(historicalGoodsReceipt);
        });
    }
    private void saveHistoricalGoodsReceipt(HistoricalGoodsReceipt historicalGoodsReceipt) {
        historicalGoodsReceiptRepository.save(historicalGoodsReceipt);
    }
}
