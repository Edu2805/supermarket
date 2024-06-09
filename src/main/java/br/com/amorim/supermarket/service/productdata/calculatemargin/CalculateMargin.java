package br.com.amorim.supermarket.service.productdata.calculatemargin;

import br.com.amorim.supermarket.model.productdata.ProductData;

import java.math.BigDecimal;

public interface CalculateMargin {

    BigDecimal calculateWhenInsert(ProductData productData);
    BigDecimal calculateWhenUpdate(ProductData productData);
}
