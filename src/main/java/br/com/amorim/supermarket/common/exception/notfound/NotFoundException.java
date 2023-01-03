package br.com.amorim.supermarket.common.exception.notfound;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}
