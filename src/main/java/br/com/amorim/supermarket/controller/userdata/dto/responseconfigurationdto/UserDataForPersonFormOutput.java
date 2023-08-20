package br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDataForPersonFormOutput {

    private UUID id;
    private String userName;
}
