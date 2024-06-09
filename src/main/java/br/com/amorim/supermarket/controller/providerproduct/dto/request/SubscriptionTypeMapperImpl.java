package br.com.amorim.supermarket.controller.providerproduct.dto.request;

import br.com.amorim.supermarket.common.enums.SubscriptionType;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.common.enums.SubscriptionType.CEI;
import static br.com.amorim.supermarket.common.enums.SubscriptionType.CNPJ;
import static br.com.amorim.supermarket.common.enums.SubscriptionType.CPF;
import static br.com.amorim.supermarket.common.enums.SubscriptionType.UNINFORMED;

@Component
public class SubscriptionTypeMapperImpl implements SubscriptionTypeMapper {

    @Override
    public SubscriptionType mapperSubscriptionType(String subscription) {
        SubscriptionType subscriptionType = UNINFORMED;
        if (CNPJ.name().equals(subscription)) {
            subscriptionType = CNPJ;
        } else if (CPF.name().equals(subscription)) {
            subscriptionType = CPF;
        }
        else if (CEI.name().equals(subscription)) {
            subscriptionType = CEI;
        }
        return subscriptionType;
    }
}
