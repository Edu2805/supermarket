package br.com.amorim.supermarket.model.establishment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "establishment")
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Nome não pode estar vazio")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Código não pode estar vazio")
    private BigInteger code;

    @Column(nullable = false, length = 14)
    @NotEmpty(message = "CNPJ não pode estar vazio")
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @Column(name = "state_registration", nullable = false, length = 20)
    @NotEmpty(message = "Inscrição estadual não pode estar vazia")
    private String stateRegistration;

    @Column(name = "municipal_registration", length = 20)
    private String municipalRegistration;

    @Column(nullable = false, length = 60)
    @NotEmpty(message = "Endereço não pode estar vazio")
    private String address;

    @Column(nullable = false, length = 11)
    @NotEmpty(message = "Número de telefone não pode estar vazio")
    private String phone;

    @Column(nullable = false, length = 60)
    @NotEmpty(message = "Gerente não pode estar vazio")
    private String manager;
}
