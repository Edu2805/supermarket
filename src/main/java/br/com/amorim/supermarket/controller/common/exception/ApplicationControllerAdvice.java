package br.com.amorim.supermarket.controller.common.exception;

import br.com.amorim.supermarket.common.exception.ApiErros;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.common.exception.invalidactionexception.InvalidActionException;
import br.com.amorim.supermarket.common.exception.invaliddocument.InvalidDocumentException;
import br.com.amorim.supermarket.common.exception.invalidpasswordexception.InvalidPasswordException;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(InvalidDocumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros handlerInvalidDocumentException (InvalidDocumentException exception) {
        String messageError = exception.getMessage();
        return new ApiErros(messageError);
    }

    @ExceptionHandler(InvalidActionException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros handlerInvalidActionException (InvalidActionException exception) {
        String messageError = exception.getMessage();
        return new ApiErros(messageError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros handlerMethodArgumentNotValidException (MethodArgumentNotValidException exception) {
        List<String> erros = exception.getBindingResult()
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage
                ).collect(Collectors.toList());
        return new ApiErros(erros);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErros handlerInvalidPasswordException (InvalidPasswordException exception) {
        String messageError = exception.getMessage();
        return new ApiErros(messageError);
    }
}
