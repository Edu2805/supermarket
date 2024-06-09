package br.com.amorim.supermarket.controller.unity.dto;

import br.com.amorim.supermarket.common.enums.EnumCommonEntity;
import br.com.amorim.supermarket.common.enums.UnityType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnityOutput extends EnumCommonEntity<UnityType> {

    public UnityOutput(UnityType key, String name) {
        super(key, name);
    }
}
