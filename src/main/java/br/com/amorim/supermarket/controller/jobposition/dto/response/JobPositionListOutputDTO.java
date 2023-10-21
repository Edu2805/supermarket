package br.com.amorim.supermarket.controller.jobposition.dto.response;

import br.com.amorim.supermarket.model.salary.Salary;
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
public class JobPositionListOutputDTO {

    private UUID id;
    private BigInteger code;
    private String name;
    private String assignments;
    private Salary salary;
}
