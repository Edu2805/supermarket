package br.com.amorim.supermarket.service.paymentoptions;

import br.com.amorim.supermarket.model.paymentoptions.PaymentOptions;
import br.com.amorim.supermarket.repository.paymentoptions.PaymentOptionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class PaymentOptionsServiceImpl implements PaymentOptionsService {

    private PaymentOptionsRepository paymentOptionsRepository;
    @Override
    public List<PaymentOptions> findAllPaymentOptions() {
        return paymentOptionsRepository.findAll();
    }
}
