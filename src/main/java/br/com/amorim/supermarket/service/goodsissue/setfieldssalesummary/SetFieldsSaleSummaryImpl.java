package br.com.amorim.supermarket.service.goodsissue.setfieldssalesummary;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;
import br.com.amorim.supermarket.repository.historicalgoodsissue.HistoricalGoodsIssueRepository;
import br.com.amorim.supermarket.service.goodsissue.setfieldssalesummary.fieldsummary.SalesSummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class SetFieldsSaleSummaryImpl implements SetFieldsSaleSummary {

    private SalesSummary salesSummary;
    private HistoricalGoodsIssueRepository historicalGoodsIssueRepository;

    @Override
    public void setFieldsSummary(GoodsIssue goodsIssue) {
        var totalSaleAndSubTotal = salesSummary.fillTotalSaleAndSubtotal(goodsIssue);
        var calculateReceiveAndChange = salesSummary.calculateTotalReceivedAndChange(goodsIssue);
        goodsIssue.setProductsTotal(totalSaleAndSubTotal);
        goodsIssue.setSubtotal(totalSaleAndSubTotal);
        goodsIssue.setChange(calculateReceiveAndChange);
        goodsIssue.setEffectiveSale(salesSummary.checkOut(goodsIssue));
    }

    @Override
    public void setFieldsHistoricalGoodsReceipt(GoodsIssue goodsIssue) {
        goodsIssue.getProductDataList().forEach(productData -> {
            HistoricalGoodsIssue historicalGoodsIssue = new HistoricalGoodsIssue();
            historicalGoodsIssue.setName(productData.getName());
            historicalGoodsIssue.setProductCode(productData.getCode());
            historicalGoodsIssue.setEan13(productData.getEan13());
            historicalGoodsIssue.setDun14(productData.getDun14());
            historicalGoodsIssue.setSalePrice(productData.getSalePrice());
            historicalGoodsIssue.setInventory(productData.getInventory());
            historicalGoodsIssue.setProviderProductName(productData.getProviderProduct().getName());
            historicalGoodsIssue.setDepartmentName(productData.getSubSection().getMainSection().getDepartment().getName());
            historicalGoodsIssue.setMainsectionName(productData.getSubSection().getMainSection().getName());
            historicalGoodsIssue.setSubsectionName(productData.getSubSection().getName());
            historicalGoodsIssue.setSaleNumber(goodsIssue.getSaleNumber());
            historicalGoodsIssue.setProductsTotal(goodsIssue.getProductsTotal());
            historicalGoodsIssue.setEffectiveSale(goodsIssue.isEffectiveSale());
            historicalGoodsIssue.setRegistrationDate(goodsIssue.getRegistrationDate());
            saveHistoricalGoodsIssue(historicalGoodsIssue);
        });
    }

    private void saveHistoricalGoodsIssue(HistoricalGoodsIssue historicalGoodsIssue) {
        historicalGoodsIssueRepository.save(historicalGoodsIssue);
    }
}
