package br.com.amorim.supermarket.common.enums;

public enum PaymentOptionsType {

    MONEY("{br.com.supermarket.MONEY}"),
    CREDIT_CARD("{br.com.supermarket.CREDIT_CARD}"),
    DEBIT_CARD("{br.com.supermarket.DEBIT_CARD}"),
    MEAL_TICKET("{br.com.supermarket.MEAL_TICKET}"),
    FOOD_VOUCHER("{br.com.supermarket.FOOD_VOUCHER}"),
    PIX("{br.com.supermarket.PIX}"),
    OPENED("{br.com.supermarket.OPENED}");

    public final String payment;

    PaymentOptionsType(String payment) {
        this.payment = payment;
    }
}
