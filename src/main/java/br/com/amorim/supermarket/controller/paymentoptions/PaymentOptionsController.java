package br.com.amorim.supermarket.controller.paymentoptions;

import br.com.amorim.supermarket.controller.paymentoptions.dto.PaymentOptionsOutputDTO;
import br.com.amorim.supermarket.service.paymentoptions.PaymentOptionsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@RestController
@RequestMapping("api/payment-options")
public class PaymentOptionsController {

    private PaymentOptionsService paymentOptionsService;

    @GetMapping
    @ApiIgnore
    public PaymentOptionsOutputDTO findAll () {
        List<String> paymentOptionsName = new ArrayList<>();
        paymentOptionsService.findAllPaymentOptions().forEach(paymentOptions -> paymentOptionsName.add(getString(paymentOptions.getName())));
        return new PaymentOptionsOutputDTO(paymentOptionsName);
    }
}
