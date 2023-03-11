package br.com.amorim.supermarket.service.goodsreceipt.verifyinvoice;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.repository.goodsreceipt.verifyinvoicerepositorycustom.VerifyInvoiceRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyInvoiceImpl implements VerifyInvoice {

    private VerifyInvoiceRepositoryCustom verifyInvoiceRepositoryCustom;
    @Override
    public boolean verifyInvoiceInDatabase(GoodsReceipt goodsReceipt) {
        if (verifyInvoiceRepositoryCustom.existsByInvoice(goodsReceipt)) {
            throw new BusinessRuleException(
                    getString(MessagesKeyType.GOODS_RECEIPT_INVOICE_ALREADY_EXISTS.message));
        }
        return false;
    }
}
