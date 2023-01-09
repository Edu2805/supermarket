package br.com.amorim.supermarket.model.providerproduct;

import br.com.amorim.supermarket.common.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "provider")
public class ProviderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "{br.com.supermarket.PROVIDER_PRODUCT_FIELD_NAME_IS_NOT_EMPTY}")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "{br.com.supermarket.PROVIDER_PRODUCT_FIELD_INTERNAL_CODE_IS_NOT_EMPTY}")
    private BigInteger code;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "subscription_type", nullable = false)
    @NotNull(message = "{br.com.supermarket.PROVIDER_PRODUCT_FIELD_SUBSCRIPTION_TYPE_IS_NOT_EMPTY}")
    private SubscriptionType subscriptionType;

    @Column(name = "subscription_number", length = 50)
    @NotNull(message = "{br.com.supermarket.PROVIDER_PRODUCT_FIELD_SUBSCRIPTION_NUMBER_IS_NOT_EMPTY}")
    private String subscriptionNumber;

    @Column(name = "state_registration", nullable = false, length = 20)
    @NotEmpty(message = "{br.com.supermarket.PROVIDER_PRODUCT_FIELD_STATE_REGISTRATION_IS_NOT_EMPTY}")
    private String stateRegistration;

    @Column(name = "municipal_registration", length = 20)
    private String municipalRegistration;

    @Column(nullable = false, length = 60)
    @NotEmpty(message = "{br.com.supermarket.PROVIDER_PRODUCT_FIELD_ADDRESS_IS_NOT_EMPTY}")
    private String address;

    @Column(nullable = false, length = 11)
    @NotEmpty(message = "{br.com.supermarket.PROVIDER_PRODUCT_FIELD_PHONE_IS_NOT_EMPTY}")
    private String phone;

    @Column(nullable = false, length = 60)
    @NotEmpty(message = "{br.com.supermarket.PROVIDER_PRODUCT_FIELD_MANAGER_IS_NOT_EMPTY}")
    private String responsible;
}
