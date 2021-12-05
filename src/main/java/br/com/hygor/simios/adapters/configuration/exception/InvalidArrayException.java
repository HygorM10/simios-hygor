package br.com.hygor.simios.adapters.configuration.exception;

import org.springframework.http.HttpStatus;

public class InvalidArrayException extends SimiosCommonException {

    public InvalidArrayException() {
        super("Ops, este array é nulo ou vazio.", HttpStatus.BAD_REQUEST);
    }

}
