package br.com.amorim.supermarket.testutils.generateentitiesunittests.otheraddition;

import br.com.amorim.supermarket.model.otheraddition.OtherAddition;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class OtherAdditionTest {

    public OtherAddition generateOtherAddition() {
        Random randomName = new Random();
        var name = randomName.nextInt(10000, 19999);

        OtherAddition otherAddition = new OtherAddition();
        otherAddition.setId(UUID.randomUUID());
        otherAddition.setAdditionName(String.valueOf(name));
        otherAddition.setAdditionValue(BigDecimal.valueOf(100.00));
        return otherAddition;
    }
}
