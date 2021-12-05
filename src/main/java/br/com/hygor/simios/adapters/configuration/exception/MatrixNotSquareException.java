package br.com.hygor.simios.adapters.configuration.exception;

import org.springframework.http.HttpStatus;

public class MatrixNotSquareException extends SimiosCommonException {

    public MatrixNotSquareException() {
        super("Ops, está matriz não é quadrada.", HttpStatus.BAD_REQUEST);
    }
}
