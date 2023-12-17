package br.com.amorim.supermarket.common.enums;

public enum OperationType {

    UPDATE("UPDATE"),
    SAVE("SAVE");

    public final String operation;

    OperationType(String operation) {
        this.operation = operation;
    }
}
