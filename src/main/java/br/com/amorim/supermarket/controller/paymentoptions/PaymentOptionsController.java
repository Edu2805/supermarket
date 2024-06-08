package br.com.amorim.supermarket.controller.paymentoptions;

import br.com.amorim.supermarket.common.enums.PaymentOptionsType;
import br.com.amorim.supermarket.controller.paymentoptions.dto.PaymentOptionsOutputDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@RestController
@RequestMapping("api/payment-options")
public class PaymentOptionsController {

    @GetMapping
    @ApiIgnore
    public List<PaymentOptionsOutputDTO> findAll () {
        List<PaymentOptionsOutputDTO> paymentOptions = new ArrayList<>();
        for (PaymentOptionsType type : PaymentOptionsType.values()) {
            paymentOptions.add(new PaymentOptionsOutputDTO(type, getString(type.name())));
        }
        return paymentOptions;
    }
}