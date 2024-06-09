package br.com.amorim.supermarket.controller.scholarity;

import br.com.amorim.supermarket.common.enums.ScholarityType;
import br.com.amorim.supermarket.controller.scholarity.dto.ScholarityOutput;
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
@RequestMapping("api/scholarity")
public class ScholarityController {

    @GetMapping
    @ApiIgnore
    public List<ScholarityOutput> findAll () {
        List<ScholarityOutput> scholarityOutputs = new ArrayList<>();
        for (ScholarityType type : ScholarityType.values()) {
            scholarityOutputs.add(new ScholarityOutput(type, getString(type.name())));
        }
        return scholarityOutputs;
    }
}
