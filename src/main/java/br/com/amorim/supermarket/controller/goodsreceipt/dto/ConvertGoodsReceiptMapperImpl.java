package br.com.amorim.supermarket.controller.goodsreceipt.dto;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertGoodsReceiptMapperImpl implements ConvertGoodsReceiptMapper {

    private ModelMapper modelMapper;

    @Override
    public GoodsReceipt createOrUpdateGoodsReceiptMapper(GoodsReceiptDTO goodsReceiptDTO) {
        return modelMapper.map(goodsReceiptDTO, GoodsReceipt.class);
    }
}
