package br.com.amorim.supermarket.service.goodsreceipt.generatecontrolnumber;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.repository.goodsreceipt.generatecontrolnumberrepositorycustom.GenerateControlNumberGoodsReceiptRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@AllArgsConstructor

@Component
public class GenerateControlNumberGoodsReceiptImpl implements GenerateControlNumberGoodsReceipt {

    private GenerateControlNumberGoodsReceiptRepositoryCustom generateControlNumberGoodsReceiptRepositoryCustom;

    @Override
    public BigInteger generate(GoodsReceipt goodsReceipt) {
        return generateControlNumberGoodsReceiptRepositoryCustom
                .generateRegisterNumber(goodsReceipt);
    }
}
