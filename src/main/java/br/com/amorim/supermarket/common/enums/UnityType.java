package br.com.amorim.supermarket.common.enums;

public enum UnityType {

    UNITY("{br.com.supermarket.UNITY}"),
    LITERS("{br.com.supermarket.LITERS}"),
    MILLILITERS("{br.com.supermarket.MILLILITERS}"),
    MILLIGRAMS("{br.com.supermarket.MILLIGRAMS}"),
    GRAMS("{br.com.supermarket.GRAMS}"),
    KILOS("{br.com.supermarket.KILOS}"),
    TONS("{br.com.supermarket.TONS}"),
    UNINFORMED("{br.com.supermarket.UNINFORMED}");

    public final String unity;

    UnityType(String unity) {
        this.unity = unity;
    }
}
