package br.com.hygor.simios.adapters.inbound.controllers;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.concurrent.ExecutionException;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.hygor.simios.application.domain.StatsDto;
import br.com.hygor.simios.application.services.StatsService;

public class StatsControllerTest {

    @InjectMocks
    private StatsController statsController;

    @Mock
    private StatsService statsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void stats_mustReturnStatsDto_withHttp200Ok() throws ExecutionException, InterruptedException {

        StatsDto statsDto = new StatsDto(10, 5, (float) 0.5);
        when(statsService.getStatsMutantAndHuman()).thenReturn(statsDto);

        ResponseEntity<Object> stats = statsController.stats();

        assertThat(stats.getBody(), CoreMatchers.is(statsDto));
        assertThat(stats.getStatusCode(), CoreMatchers.is(HttpStatus.OK));

    }
}