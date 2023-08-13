package br.com.amorim.supermarket.controller.person.dto.response;

import br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto.UserDataResponseDTO;
import br.com.amorim.supermarket.model.attatchment.Attachment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonScholarityTypeStringDTO {

    private UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String cpf;
    private String rg;
    private String nationality;
    private String naturalness;
    private LocalDate birthDate;
    private String scholarity;
    private Boolean dependents;
    private String fatherName;
    private String motherName;
    private String email;
    private UserDataResponseDTO userData;
    private Attachment personPhoto;
}
