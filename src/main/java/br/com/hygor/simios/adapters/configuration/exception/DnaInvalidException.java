package br.com.hygor.simios.adapters.configuration.exception;

import org.springframework.http.HttpStatus;

public class DnaInvalidException extends SimiosCommonException {

    public DnaInvalidException() {
        super("Ops, DNA inválido.", HttpStatus.BAD_REQUEST);
    }

}
