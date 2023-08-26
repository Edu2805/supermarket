package br.com.amorim.supermarket.controller.unity;

import br.com.amorim.supermarket.controller.unity.dto.UnityOutput;
import br.com.amorim.supermarket.service.unity.UnityCrudService;
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
@RequestMapping("api/unity")
public class UnityController {

    private UnityCrudService unityCrudService;

    @GetMapping
    @ApiIgnore
    public UnityOutput findAll () {
        List<String> unityName = new ArrayList<>();
        unityCrudService.findAllUnities().forEach(unity -> unityName.add(getString(unity.getName())));
        return new UnityOutput(unityName);
    }
}
