package br.com.amorim.supermarket.controller.scholarity.dto;

import br.com.amorim.supermarket.common.enums.EnumCommonEntity;
import br.com.amorim.supermarket.common.enums.ScholarityType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScholarityOutput extends EnumCommonEntity<ScholarityType> {

    public ScholarityOutput(ScholarityType key, String name) {
        super(key, name);
    }
}
