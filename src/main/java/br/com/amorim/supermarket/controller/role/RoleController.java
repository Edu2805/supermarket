package br.com.amorim.supermarket.controller.role;

import br.com.amorim.supermarket.controller.role.dto.RoleOutput;
import br.com.amorim.supermarket.service.role.RoleCrudService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("api/role")
public class RoleController {

    private RoleCrudService roleCrudService;

    @GetMapping
    @ApiIgnore
    public RoleOutput findAll () {
        List<String> roleName = new ArrayList<>();
        roleCrudService.findAllRoles().forEach(role -> roleName.add(role.getName()));
        return new RoleOutput(roleName);
    }
}
