package br.com.amorim.supermarket.controller.paymentoptions.dto;

import br.com.amorim.supermarket.common.enums.PaymentOptionsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentOptionsOutputDTO {

    private PaymentOptionsType key;
    private String name;
}
