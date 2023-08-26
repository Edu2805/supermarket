package br.com.amorim.supermarket.controller.productdata.dto.mapper;

import br.com.amorim.supermarket.common.enums.UnityType;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.common.enums.UnityType.GRAMS;
import static br.com.amorim.supermarket.common.enums.UnityType.KILOS;
import static br.com.amorim.supermarket.common.enums.UnityType.LITERS;
import static br.com.amorim.supermarket.common.enums.UnityType.MILLIGRAMS;
import static br.com.amorim.supermarket.common.enums.UnityType.MILLILITERS;
import static br.com.amorim.supermarket.common.enums.UnityType.TONS;
import static br.com.amorim.supermarket.common.enums.UnityType.UNINFORMED;
import static br.com.amorim.supermarket.common.enums.UnityType.UNITY;
import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@Component
public class UnityTypeMapperImpl implements UnityTypeMapper {

    @Override
    public UnityType mapperUnityTypeMapper(String unity) {
        UnityType unityType = UNINFORMED;
        if (getString(UNITY.name()).equals(unity)) {
            unityType = UNITY;
        } else if (getString(LITERS.name()).equals(unity)) {
            unityType = LITERS;
        } else if (getString(MILLILITERS.name()).equals(unity)) {
            unityType = MILLILITERS;
        } else if (getString(MILLIGRAMS.name()).equals(unity)) {
            unityType = MILLIGRAMS;
        } else if (getString(GRAMS.name()).equals(unity)) {
            unityType = GRAMS;
        } else if (getString(KILOS.name()).equals(unity)) {
            unityType = KILOS;
        } else if (getString(TONS.name()).equals(unity)) {
            unityType = TONS;
        }
        return unityType;
    }
}
