package br.com.hygor.simios.adapters.inbound.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hygor.simios.application.domain.StatsDto;
import br.com.hygor.simios.application.services.StatsService;

@RestController
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    @RequestMapping("/stats")
    public ResponseEntity<Object> stats() throws ExecutionException, InterruptedException {
        StatsDto stats = statsService.getStatsMutantAndHuman();
        return ResponseEntity.status(HttpStatus.OK).body(stats);
    }

}
