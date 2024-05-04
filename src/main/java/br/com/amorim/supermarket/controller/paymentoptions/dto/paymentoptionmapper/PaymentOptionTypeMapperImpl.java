package br.com.amorim.supermarket.controller.paymentoptions.dto.paymentoptionmapper;

import br.com.amorim.supermarket.common.enums.PaymentOptionsType;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.common.enums.PaymentOptionsType.CREDIT_CARD;
import static br.com.amorim.supermarket.common.enums.PaymentOptionsType.DEBIT_CARD;
import static br.com.amorim.supermarket.common.enums.PaymentOptionsType.FOOD_VOUCHER;
import static br.com.amorim.supermarket.common.enums.PaymentOptionsType.MEAL_TICKET;
import static br.com.amorim.supermarket.common.enums.PaymentOptionsType.MONEY;
import static br.com.amorim.supermarket.common.enums.PaymentOptionsType.OPENED;
import static br.com.amorim.supermarket.common.enums.PaymentOptionsType.PIX;
import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@Component
public class PaymentOptionTypeMapperImpl implements PaymentOptionTypeMapper{
    @Override
    public PaymentOptionsType mapperPaymentoOptionsType(String paymentOptionType) {
        PaymentOptionsType paymentOption = OPENED;
        if (getString(MONEY.name()).equals(paymentOptionType)) {
            paymentOption = MONEY;
        }
        else if (getString(CREDIT_CARD.name()).equals(paymentOptionType)) {
            paymentOption = CREDIT_CARD;
        }
        else if (getString(DEBIT_CARD.name()).equals(paymentOptionType)) {
            paymentOption = DEBIT_CARD;
        }
        else if (getString(MEAL_TICKET.name()).equals(paymentOptionType)) {
            paymentOption = MEAL_TICKET;
        }
        else if (getString(FOOD_VOUCHER.name()).equals(paymentOptionType)) {
            paymentOption = FOOD_VOUCHER;
        }
        else if (getString(PIX.name()).equals(paymentOptionType)) {
            paymentOption = PIX;
        }
        return paymentOption;
    }
}
