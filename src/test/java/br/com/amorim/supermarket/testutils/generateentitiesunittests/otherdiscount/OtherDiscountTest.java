package br.com.amorim.supermarket.testutils.generateentitiesunittests.otherdiscount;

import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class OtherDiscountTest {

    public OtherDiscount generateOtherDiscount() {
        Random randomName = new Random();
        var name = randomName.nextInt(10000, 19999);

        OtherDiscount otherDiscount = new OtherDiscount();
        otherDiscount.setId(UUID.randomUUID());
        otherDiscount.setDiscountName(String.valueOf(name));
        otherDiscount.setDiscountValue(BigDecimal.valueOf(100.00));
        return otherDiscount;
    }
}
