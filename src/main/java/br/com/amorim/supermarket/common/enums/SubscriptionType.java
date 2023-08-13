package br.com.amorim.supermarket.common.enums;

public enum SubscriptionType {

    CNPJ("{br.com.supermarket.CNPJ}"),
    CPF("{br.com.supermarket.CPF}"),
    CEI("{br.com.supermarket.CEI}");

    public final String subscription;

    SubscriptionType(String subscription) {
        this.subscription = subscription;
    }
}
