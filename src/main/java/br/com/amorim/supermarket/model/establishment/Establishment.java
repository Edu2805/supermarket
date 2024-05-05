package br.com.amorim.supermarket.model.establishment;

import br.com.amorim.supermarket.model.attatchment.Attachment;
import br.com.amorim.supermarket.model.common.CommonIdNameAndCodeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "establishment")
public class Establishment extends CommonIdNameAndCodeEntity {

    @Column(nullable = false, length = 14)
    @NotEmpty(message = "{br.com.supermarket.ESTABLISHMENT_FIELD_CNPJ_IS_NOT_EMPTY}")
    @CNPJ(message = "{br.com.supermarket.ESTABLISHMENT_FIELD_INVALID_CNPJ}")
    private String cnpj;

    @Column(name = "state_registration", nullable = false, length = 20)
    @NotEmpty(message = "{br.com.supermarket.ESTABLISHMENT_FIELD_STATE_REGISTRATION_IS_NOT_EMPTY}")
    private String stateRegistration;

    @Column(name = "municipal_registration", length = 20)
    private String municipalRegistration;

    @Column(nullable = false, length = 60)
    @NotEmpty(message = "{br.com.supermarket.ESTABLISHMENT_FIELD_ADDRESS_IS_NOT_EMPTY}")
    private String address;

    @Column(nullable = false, length = 11)
    @NotEmpty(message = "{br.com.supermarket.ESTABLISHMENT_FIELD_PHONE_IS_NOT_EMPTY}")
    private String phone;

    @Column(nullable = false, length = 60)
    @NotEmpty(message = "{br.com.supermarket.ESTABLISHMENT_FIELD_MANAGER_IS_NOT_EMPTY}")
    private String manager;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "stablisment_logo")
    private Attachment establismentLogo;
}
