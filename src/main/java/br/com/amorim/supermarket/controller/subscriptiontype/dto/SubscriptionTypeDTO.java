package br.com.amorim.supermarket.controller.subscriptiontype.dto;

import br.com.amorim.supermarket.common.enums.EnumCommonEntity;
import br.com.amorim.supermarket.common.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionTypeDTO extends EnumCommonEntity<SubscriptionType> {

    public SubscriptionTypeDTO(SubscriptionType key, String name) {
        super(key, name);
    }
}
