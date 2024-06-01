package br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto;

import br.com.amorim.supermarket.configuration.security.roles.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserNameOutput {

    private UUID id;
    private RoleType role;
}
