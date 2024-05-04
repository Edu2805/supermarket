package br.com.amorim.supermarket.controller.paymentoptions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentOptionsOutputDTO {

    List<String> names;
}
