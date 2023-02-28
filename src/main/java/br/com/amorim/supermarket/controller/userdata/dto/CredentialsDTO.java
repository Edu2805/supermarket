package br.com.amorim.supermarket.controller.userdata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CredentialsDTO {

    private String login;
    private String password;
}
