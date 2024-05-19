package br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExpensiesReportInput {

        private LocalDate from;
        private LocalDate to;
}
