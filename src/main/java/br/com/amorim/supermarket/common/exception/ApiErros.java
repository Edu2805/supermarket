package br.com.amorim.supermarket.common.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

    @Getter
    private List<String> errors;

    public ApiErros(String messageError) {
        this.errors = Arrays.asList(messageError);
    }

    public ApiErros(List<String> errors) {
        this.errors = errors;
    }
}
