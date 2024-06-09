package br.com.amorim.supermarket.common.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnumCommonEntity<T> {

    private T key;
    private String name;

    public EnumCommonEntity(T key, String name) {
        this.key = key;
        this.name = name;
    }
}
