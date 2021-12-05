package br.com.hygor.simios.adapters.util;

import org.springframework.stereotype.Component;

import br.com.hygor.simios.adapters.configuration.exception.InvalidArrayException;

@Component
public class ValidUtil {

    public void validArrayNullOrEmpty(String[] array) throws InvalidArrayException {
        if (array == null || array.length < 1) {
            throw new InvalidArrayException();
        }
    }

}
