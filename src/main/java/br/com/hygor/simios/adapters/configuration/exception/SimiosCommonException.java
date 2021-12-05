package br.com.hygor.simios.adapters.configuration.exception;

import org.springframework.http.HttpStatus;

public class SimiosCommonException extends Exception {


    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public SimiosCommonException(String msg, HttpStatus status) {
        super(msg);
        this.setStatus(status);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }


}
