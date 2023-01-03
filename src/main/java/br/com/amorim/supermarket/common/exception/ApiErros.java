package br.com.amorim.supermarket.common.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

    @Getter
    private List<String> erros;

    public ApiErros(String messageError) {
        this.erros = Arrays.asList(messageError);
    }
}
