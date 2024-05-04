package br.com.amorim.supermarket.controller.paymentoptions.dto.paymentoptionmapper;

import br.com.amorim.supermarket.common.enums.PaymentOptionsType;

public interface PaymentOptionTypeMapper {

    PaymentOptionsType mapperPaymentoOptionsType(String paymentOptionType);
}
