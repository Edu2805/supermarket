package br.com.amorim.supermarket.controller.goodsissue.dto;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertGoodsIssueMapperImpl implements ConvertGoodsIssueMapper {

    private ModelMapper modelMapper;

    @Override
    public GoodsIssue createOrUpdateGoodsIssueMapper(GoodsIssueDTO goodsIssueDTO) {
        return modelMapper.map(goodsIssueDTO, GoodsIssue.class);
    }
}
