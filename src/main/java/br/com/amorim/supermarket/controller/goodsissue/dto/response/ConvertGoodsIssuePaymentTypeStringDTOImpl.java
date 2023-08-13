package br.com.amorim.supermarket.controller.goodsissue.dto.response;

import br.com.amorim.supermarket.controller.productdata.dto.response.ConvertProductUnitTypeStringDTO;
import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class ConvertGoodsIssuePaymentTypeStringDTOImpl implements ConvertGoodsIssuePaymentTypeStringDTO {

    private ConvertProductUnitTypeStringDTO convertProductUnitTypeStringDTO;
    @Override
    public GoodsIssuePaymentTypeStringDTO mapper(GoodsIssue goodsIssue) {
        return mapperGoodsIssue(goodsIssue);
    }

    private GoodsIssuePaymentTypeStringDTO mapperGoodsIssue(GoodsIssue goodsIssue) {
        var goodsIssuePaymentTypeStringDTO = new GoodsIssuePaymentTypeStringDTO();
        goodsIssuePaymentTypeStringDTO.setId(goodsIssue.getId());
        goodsIssuePaymentTypeStringDTO.setSaleNumber(goodsIssue.getSaleNumber());
        goodsIssuePaymentTypeStringDTO.setProductsTotal(goodsIssue.getProductsTotal());
        goodsIssuePaymentTypeStringDTO.setSubtotal(goodsIssue.getProductsTotal());
        goodsIssuePaymentTypeStringDTO.setTotalReceived(goodsIssue.getTotalReceived());
        goodsIssuePaymentTypeStringDTO.setChange(goodsIssue.getChange());
        goodsIssuePaymentTypeStringDTO.setEffectiveSale(goodsIssue.isEffectiveSale());
        goodsIssuePaymentTypeStringDTO.setPaymentOptionsType(getString(goodsIssue.getPaymentOptionsType().name()));
        goodsIssuePaymentTypeStringDTO.setRegistrationDate(goodsIssue.getRegistrationDate());
        goodsIssuePaymentTypeStringDTO.setProductDataList(goodsIssue.getProductDataList()
                .stream()
                .map(product -> convertProductUnitTypeStringDTO.mapper(product))
                .collect(Collectors.toList()));
        goodsIssuePaymentTypeStringDTO.setProductList(goodsIssue.getProductList());
        return goodsIssuePaymentTypeStringDTO;
    }
}
