package br.com.amorim.supermarket.controller.otherdiscount.dto;

import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;

public interface ConverterOtherDiscountMapper {

    OtherDiscount createOrUpdateOtherDiscountMapper(OtherDiscountDTO otherDiscountDTO);
}
