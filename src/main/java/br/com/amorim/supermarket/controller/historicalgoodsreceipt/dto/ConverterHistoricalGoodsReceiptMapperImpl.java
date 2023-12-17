package br.com.amorim.supermarket.controller.historicalgoodsreceipt.dto;

import br.com.amorim.supermarket.controller.historicalgoodsreceipt.dto.response.HistoricalGoodsReceiptOutput;
import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@Component
public class ConverterHistoricalGoodsReceiptMapperImpl implements ConverterHistoricalGoodsReceiptMapper{

    private ModelMapper modelMapper;

    @Override
    public List<HistoricalGoodsReceiptOutput> historicalGoodsReceiptOutputMapper(List<HistoricalGoodsReceipt> historicalGoodsReceiptList) {
        List<HistoricalGoodsReceiptOutput> historicalGoodsReceiptOutputList = new ArrayList<>();
        if (!historicalGoodsReceiptList.isEmpty()) {
            historicalGoodsReceiptList.forEach(historicalGoodsReceiptOutput ->
                    historicalGoodsReceiptOutputList.add(modelMapper.map(historicalGoodsReceiptOutput, HistoricalGoodsReceiptOutput.class)));
            return historicalGoodsReceiptOutputList;
        }
        return List.of();
    }
}
