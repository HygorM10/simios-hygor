package br.com.hygor.simios.adapters.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.com.hygor.simios.adapters.configuration.exception.InvalidArrayException;

public class ValidUtilTest {

    @InjectMocks
    private ValidUtil validUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validArray_withEmptyArray_mustInvokeArrayInvalidException()  {

        try {
            String[] arr = new String[0];
            validUtil.validArrayNullOrEmpty(arr);
        } catch (InvalidArrayException e) {
            assertThat(e.getMessage(), is("Ops, este array é nulo ou vazio."));
        }

    }

    @Test
    public void validArray_withNullArray_mustInvokeArrayInvalidException()  {

        try {
            validUtil.validArrayNullOrEmpty(null);
        } catch (InvalidArrayException e) {
            assertThat(e.getMessage(), is("Ops, este array é nulo ou vazio."));
        }

    }
}