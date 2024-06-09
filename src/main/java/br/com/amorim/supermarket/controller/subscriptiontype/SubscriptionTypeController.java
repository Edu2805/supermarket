package br.com.amorim.supermarket.controller.subscriptiontype;

import br.com.amorim.supermarket.common.enums.SubscriptionType;
import br.com.amorim.supermarket.controller.subscriptiontype.dto.SubscriptionTypeDTO;
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
@RequestMapping("api/subscription-type")
public class SubscriptionTypeController {

    @GetMapping
    @ApiIgnore
    public List<SubscriptionTypeDTO> findAll () {
        List<SubscriptionTypeDTO> subscriptionTypeDTOList = new ArrayList<>();
        for (SubscriptionType type : SubscriptionType.values()) {
            subscriptionTypeDTOList.add(new SubscriptionTypeDTO(type, getString(type.name())));
        }
        return subscriptionTypeDTOList;
    }
}
