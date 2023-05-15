package br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto;

import br.com.amorim.supermarket.configuration.security.roles.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenDTO {

    private String login;
    private RoleType roleType;
    private String token;
}
