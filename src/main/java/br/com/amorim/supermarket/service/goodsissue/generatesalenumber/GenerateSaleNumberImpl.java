package br.com.amorim.supermarket.service.goodsissue.generatesalenumber;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import br.com.amorim.supermarket.repository.goodsissue.generatesalenumber.GenerateSaleNumberRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@AllArgsConstructor

@Component
public class GenerateSaleNumberImpl implements GenerateSaleNumber {

    private GenerateSaleNumberRepositoryCustom generateSaleNumberRepositoryCustom;

    @Override
    public BigInteger generate(GoodsIssue goodsIssue) {
        return generateSaleNumberRepositoryCustom.generateSaleNumber(goodsIssue);
    }
}
