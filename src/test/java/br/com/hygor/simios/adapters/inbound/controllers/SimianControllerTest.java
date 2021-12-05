package br.com.hygor.simios.adapters.inbound.controllers;

import static org.mockito.Mockito.when;

import java.util.concurrent.ExecutionException;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.hygor.simios.adapters.configuration.exception.SimiosCommonException;
import br.com.hygor.simios.application.domain.DnaDto;
import br.com.hygor.simios.application.services.SimianService;

public class SimianControllerTest {

    @InjectMocks
    private SimianController simianController;

    @Mock
    private SimianService simianService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isSimian_withSimianDna_mustReturnHttp200Ok() throws InterruptedException, ExecutionException, SimiosCommonException {

        String[] dna = {"GTGCGT", "CAGTGC", "TTATAT", "AGAACG", "CCCCTA", "TCACTG"};

        when(simianService.isSimian(dna)).thenReturn(Boolean.TRUE);

        DnaDto dnaDto = new DnaDto(dna);

        ResponseEntity responseEntity = simianController.isSimian(dnaDto);

        Assert.assertThat(responseEntity.getStatusCode(), CoreMatchers.is(HttpStatus.OK));

    }

    @Test
    public void isSimian_withSimianDna_mustReturnHttp403Forbbiden() throws InterruptedException, ExecutionException, SimiosCommonException {

        String[] dna = {"GTGCGT", "CAGTGC", "TTATAT", "AGAACG", "TCCCTA", "TCACTG"};

        when(simianService.isSimian(dna)).thenReturn(Boolean.FALSE);

        DnaDto dnaDto = new DnaDto(dna);

        ResponseEntity responseEntity = simianController.isSimian(dnaDto);

        Assert.assertThat(responseEntity.getStatusCode(), CoreMatchers.is(HttpStatus.FORBIDDEN));

    }



}