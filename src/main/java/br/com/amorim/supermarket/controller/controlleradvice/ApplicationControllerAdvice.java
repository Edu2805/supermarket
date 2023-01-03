package br.com.amorim.supermarket.controller.controlleradvice;

import br.com.amorim.supermarket.common.exception.ApiErros;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros handlerBusinessException (BusinessRuleException exception) {
        String messageError = exception.getMessage();
        return new ApiErros(messageError);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErros handlerNotFoundException (NotFoundException exception) {
        String messageError = exception.getMessage();
        return new ApiErros(messageError);
    }
}
