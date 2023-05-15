package br.com.amorim.supermarket.controller.role;

import br.com.amorim.supermarket.controller.role.dto.RoleOutput;
import br.com.amorim.supermarket.service.role.RoleCrudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("api/role")
@Api("Role")
public class RoleController {

    private RoleCrudService roleCrudService;

    @GetMapping
    @ApiOperation("Get all roles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Roles returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the roles")
    })
    public RoleOutput findAll () {
        List<String> roleName = new ArrayList<>();
        roleCrudService.findAllRoles().forEach(role -> roleName.add(role.getName()));
        return new RoleOutput(roleName);
    }
}
