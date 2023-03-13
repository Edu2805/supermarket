package br.com.amorim.supermarket.service.goodsissue.setfieldssalesummary.fieldsummary;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.enums.PaymentOptionsType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class SalesSummaryImpl implements SalesSummary {

    @Override
    public BigDecimal fillTotalSaleAndSubtotal(GoodsIssue goodsIssue) {
        final BigDecimal[] totalSale = {BigDecimal.ZERO};
        goodsIssue.getProductDataList().forEach(product -> {
            totalSale[0] = totalSale[0].add(product.getSalePrice().multiply(product.getInventory()));
        });
        return totalSale[0];
    }

    @Override
    public BigDecimal calculateTotalReceivedAndChange(GoodsIssue goodsIssue) {
        if (goodsIssue.getTotalReceived().compareTo(fillTotalSaleAndSubtotal(goodsIssue)) >= 0) {
            return goodsIssue.getTotalReceived().subtract(fillTotalSaleAndSubtotal(goodsIssue));
        }
        throw new NotFoundException(getString(MessagesKeyType.GOODS_ISSUE_AMOUNT_RECEIVED_IS_BELOW.message));
    }

    @Override
    public boolean checkOut(GoodsIssue goodsIssue) {
        return goodsIssue.getPaymentOptionsType() != PaymentOptionsType.OPENED;
    }
}
