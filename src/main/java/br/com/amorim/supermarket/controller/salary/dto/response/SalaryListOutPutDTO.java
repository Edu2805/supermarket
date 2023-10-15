package br.com.amorim.supermarket.controller.salary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalaryListOutPutDTO {

    private UUID id;
    private String position;
    private String salaryRange;
}
