package br.com.amorim.supermarket.common.enums;

public enum SubscriptionType {

    CNPJ("CNPJ"),
    CPF("CPF"),
    CEI("CEI"),
    UNINFORMED("UNINFORMED");

    public final String subscription;

    SubscriptionType(String subscription) {
        this.subscription = subscription;
    }
}
