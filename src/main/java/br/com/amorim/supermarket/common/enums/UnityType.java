package br.com.amorim.supermarket.common.enums;

public enum UnityType {

    UNITY("UNITY"),
    LITERS("LITERS"),
    MILLILITERS("MILLILITERS"),
    MILLIGRAMS("MILLIGRAMS"),
    GRAMS("GRAMS"),
    KILOS("KILOS"),
    TONS("TONS"),
    UNINFORMED("UNINFORMED");

    public final String unity;

    UnityType(String unity) {
        this.unity = unity;
    }
}
