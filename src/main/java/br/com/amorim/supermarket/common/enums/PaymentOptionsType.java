package br.com.amorim.supermarket.common.enums;

public enum PaymentOptionsType {

    MONEY("MONEY"),
    CREDIT_CARD("CREDIT_CARD"),
    DEBIT_CARD("DEBIT_CARD"),
    MEAL_TICKET("MEAL_TICKET"),
    FOOD_VOUCHER("FOOD_VOUCHER"),
    PIX("PIX"),
    OPENED("OPENED");

    public final String payment;

    PaymentOptionsType(String payment) {
        this.payment = payment;
    }
}
