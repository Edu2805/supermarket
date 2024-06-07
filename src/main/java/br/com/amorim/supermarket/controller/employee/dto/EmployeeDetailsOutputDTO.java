package br.com.amorim.supermarket.controller.employee.dto;

import br.com.amorim.supermarket.controller.person.dto.response.PersonScholarityTypeStringDTO;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.subsection.SubSection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDetailsOutputDTO {

    private UUID id;
    private BigInteger registerNumber;
    private String fullName;
    private SubSection subSection;
    private JobPosition jobPosition;
    private PersonScholarityTypeStringDTO personScholarityTypeStringDTO;
}
