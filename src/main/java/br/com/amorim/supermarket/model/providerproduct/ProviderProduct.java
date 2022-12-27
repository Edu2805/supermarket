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
    @NotEmpty(message = "Nome não pode estar vazio")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Código não pode estar vazio")
    private BigInteger code;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "subscription_type", nullable = false)
    private SubscriptionType subscriptionType;

    @Column(name = "subscription_number", length = 50)
    @NotNull(message = "Número do documento não pode estar vazio")
    private String subscriptionNumber;

    @Column(name = "state_registration", nullable = false, length = 20)
    @NotEmpty(message = "Inscrição estadual não pode estar vazia")
    private String stateRegistration;

    @Column(name = "municipal_registration", length = 20)
    private String municipalRegistration;

    @Column(nullable = false, length = 60)
    @NotEmpty(message = "Endereço não pode estar vazio")
    private String address;

    @Column(nullable = false, length = 11)
    @NotEmpty(message = "Número do telefone não pode estar vazio")
    private String phone;

    @Column(nullable = false, length = 60)
    @NotEmpty(message = "Responsável não pode estar vazio")
    private String responsible;
}
