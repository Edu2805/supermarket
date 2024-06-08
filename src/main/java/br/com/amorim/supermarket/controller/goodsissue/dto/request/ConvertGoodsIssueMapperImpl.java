package br.com.amorim.supermarket.controller.goodsissue.dto.request;

import br.com.amorim.supermarket.controller.paymentoptions.dto.paymentoptionmapper.PaymentOptionTypeMapper;
import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertGoodsIssueMapperImpl implements ConvertGoodsIssueMapper {

    private PaymentOptionTypeMapper paymentOptionTypeMapper;

    @Override
    public GoodsIssue createGoodsIssueMapper(GoodsIssueDTO goodsIssueDTO) {
        GoodsIssue goodsIssue = new GoodsIssue();
        goodsIssue.setPaymentOptionsType(paymentOptionTypeMapper.mapperPaymentOptionsType(goodsIssueDTO.getPaymentOptionsType()));
        goodsIssue.setProductDataList(goodsIssueDTO.getProductDataList());
        goodsIssue.setSaleNumber(goodsIssueDTO.getSaleNumber());
        goodsIssue.setChange(goodsIssueDTO.getChange());
        goodsIssue.setSubtotal(goodsIssueDTO.getSubtotal());
        goodsIssue.setEffectiveSale(goodsIssueDTO.isEffectiveSale());
        goodsIssue.setProductsTotal(goodsIssueDTO.getProductsTotal());
        goodsIssue.setRegistrationDate(goodsIssueDTO.getRegistrationDate());
        goodsIssue.setTotalReceived(goodsIssueDTO.getTotalReceived());
        return goodsIssue;
    }

    @Override
    public GoodsIssue updateGoodsIssueMapper(GoodsIssueDTO goodsIssueDTO) {
        GoodsIssue goodsIssue = new GoodsIssue();
        goodsIssue.setId(goodsIssueDTO.getId());
        goodsIssue.setPaymentOptionsType(paymentOptionTypeMapper.mapperPaymentOptionsType(goodsIssueDTO.getPaymentOptionsType()));
        goodsIssue.setProductDataList(goodsIssueDTO.getProductDataList());
        goodsIssue.setSaleNumber(goodsIssueDTO.getSaleNumber());
        goodsIssue.setChange(goodsIssueDTO.getChange());
        goodsIssue.setSubtotal(goodsIssueDTO.getSubtotal());
        goodsIssue.setEffectiveSale(goodsIssueDTO.isEffectiveSale());
        goodsIssue.setProductsTotal(goodsIssueDTO.getProductsTotal());
        goodsIssue.setRegistrationDate(goodsIssueDTO.getRegistrationDate());
        goodsIssue.setTotalReceived(goodsIssueDTO.getTotalReceived());
        return goodsIssue;
    }
}
