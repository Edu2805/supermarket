package br.com.amorim.supermarket.controller.person.dto.response;

import br.com.amorim.supermarket.model.attatchment.Attachment;
import br.com.amorim.supermarket.model.userdata.UserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonListOutputDTO {

    private UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String cpf;
    private String email;
    private UserData userData;
    private Attachment personPhoto;
}
