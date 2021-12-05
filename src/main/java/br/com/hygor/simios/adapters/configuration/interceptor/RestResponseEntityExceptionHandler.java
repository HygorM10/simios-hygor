package br.com.hygor.simios.adapters.configuration.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.hygor.simios.adapters.configuration.exception.SimiosCommonException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> genericHandle(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Ops, ocorreu um erro interno, por gentileza, tente novamente mais tarde.";
        logger.fatal("Ocorreu uma exceção.", ex);
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {SimiosCommonException.class})
    protected ResponseEntity<Object> customHandle(SimiosCommonException ex, WebRequest request) {
        logger.fatal("Ocorreu uma exceção.", ex);
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), ex.getStatus(), request);
    }

}
