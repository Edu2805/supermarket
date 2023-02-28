package br.com.amorim.supermarket.common.exception.invalidpasswordexception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}
