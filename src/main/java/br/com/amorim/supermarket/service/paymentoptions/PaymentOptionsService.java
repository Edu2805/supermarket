package br.com.amorim.supermarket.service.paymentoptions;

import br.com.amorim.supermarket.model.paymentoptions.PaymentOptions;

import java.util.List;

public interface PaymentOptionsService {

    List<PaymentOptions> findAllPaymentOptions();
}
