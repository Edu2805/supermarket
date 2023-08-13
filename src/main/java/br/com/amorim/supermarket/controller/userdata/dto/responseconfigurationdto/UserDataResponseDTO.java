package br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto;

import br.com.amorim.supermarket.configuration.security.roles.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDataResponseDTO {

    private String userName;
    private String role;
    private Timestamp registrationDate;
    private boolean isEmployee;
}
