package br.com.amorim.supermarket.controller.scholarity;

import br.com.amorim.supermarket.controller.scholarity.dto.ScholarityOutput;
import br.com.amorim.supermarket.service.scholarity.ScholarityCrudService;
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

    private ScholarityCrudService scholarityCrudService;

    @GetMapping
    @ApiIgnore
    public ScholarityOutput findAll () {
        List<String> scholarityName = new ArrayList<>();
        scholarityCrudService.findAllEducations().forEach(scholarity -> scholarityName.add(getString(scholarity.getName())));
        return new ScholarityOutput(scholarityName);
    }
}
