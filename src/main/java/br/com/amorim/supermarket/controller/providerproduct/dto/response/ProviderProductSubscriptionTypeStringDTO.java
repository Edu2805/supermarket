package br.com.amorim.supermarket.controller.providerproduct.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProviderProductSubscriptionTypeStringDTO {

    private UUID id;
    private String name;
    private BigInteger code;
    private String stateRegistration;
    private String municipalRegistration;
    private String subscriptionType;
    private String subscriptionNumber;
    private String address;
    private String phone;
    private String responsible;
}
