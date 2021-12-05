package br.com.hygor.simios.adapters.inbound.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hygor.simios.adapters.configuration.exception.SimiosCommonException;
import br.com.hygor.simios.application.domain.DnaDto;
import br.com.hygor.simios.application.services.SimianService;
import br.com.hygor.simios.application.services.StatsService;

@RestController
public class SimianController {

    @Autowired
    private SimianService simianService;

    private StatsService statsService;

    @PostMapping
    @RequestMapping("/simian")
    public ResponseEntity isSimian(@RequestBody DnaDto dnaDto) throws ExecutionException, InterruptedException, SimiosCommonException {

        return simianService.isSimian(dnaDto.getDna())
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

}
