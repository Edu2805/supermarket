package br.com.amorim.supermarket.service.goodsreceipt.totalproduct.fieldsummary;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor

@Component
public class PurchaseSummaryImpl implements PurchaseSummary {

    private SubSectionRepository subSectionRepository;

    @Override
    public BigDecimal calculateTotalProducts(GoodsReceipt goodsReceipt) {
        final BigDecimal[] totalPurchase = {BigDecimal.ZERO};
        goodsReceipt.getProductDataList().forEach(product -> {
            totalPurchase[0] = totalPurchase[0].add(product.getPurchasePrice().multiply(product.getInventory()));
        });
        return totalPurchase[0];
    }

    @Override
    public BigDecimal calculateTotalBySubsection(GoodsReceipt goodsReceipt) {
        final BigDecimal[] totalPurchaseBySubsection = {BigDecimal.ZERO};
        goodsReceipt.getProductDataList().forEach(product -> {
            var findProductSubSection = subSectionRepository.findById(product.getSubSection().getId());
            if(findProductSubSection.isPresent()) {
                totalPurchaseBySubsection[0] = totalPurchaseBySubsection[0]
                        .add(product.getPurchasePrice().multiply(product.getInventory()));
            }
        });
        return totalPurchaseBySubsection[0];
    }

    @Override
    public BigDecimal calculateTotalByMainsection(GoodsReceipt goodsReceipt) {
        final BigDecimal[] totalPurchaseBySubsection = {BigDecimal.ZERO};
        goodsReceipt.getProductDataList().forEach(product -> {
            if(product.getSubSection().getMainSection() != null) {
                totalPurchaseBySubsection[0] = totalPurchaseBySubsection[0]
                        .add(product.getPurchasePrice().multiply(product.getInventory()));
            }
        });
        return totalPurchaseBySubsection[0];
    }

    @Override
    public BigDecimal calculateTotalByDepartment(GoodsReceipt goodsReceipt) {
        final BigDecimal[] totalPurchaseBySubsection = {BigDecimal.ZERO};
        goodsReceipt.getProductDataList().forEach(product -> {
            if(product.getSubSection().getMainSection().getDepartment() != null) {
                totalPurchaseBySubsection[0] = totalPurchaseBySubsection[0]
                        .add(product.getPurchasePrice().multiply(product.getInventory()));
            }
        });
        return totalPurchaseBySubsection[0];
    }
}
