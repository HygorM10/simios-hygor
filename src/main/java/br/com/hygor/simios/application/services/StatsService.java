package br.com.hygor.simios.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hygor.simios.adapters.outbound.smtp.DnaService;
import br.com.hygor.simios.application.domain.StatsDto;
import br.com.hygor.simios.application.domain.enums.DnaTypeEnum;
import br.com.hygor.simios.application.ports.StatsServicePorts;

@Service
public class StatsService implements StatsServicePorts{

    @Autowired
    private DnaService dnaService;

    @Override
    public StatsDto getStatsMutantAndHuman(){

        int count_human_dna = (dnaService.findAllByDnaTypeEnum(DnaTypeEnum.HUMANO) != null) ? dnaService.findAllByDnaTypeEnum(DnaTypeEnum.HUMANO).size() : 0;
        int count_simian_dna = (dnaService.findAllByDnaTypeEnum(DnaTypeEnum.MUTANTE) != null) ? dnaService.findAllByDnaTypeEnum(DnaTypeEnum.MUTANTE).size() : 0;

        StatsDto statsDto = new StatsDto();
        statsDto.setCount_human_dna(count_human_dna);
        statsDto.setCount_mutant_dna(count_simian_dna);
        if (statsDto.getCount_human_dna() > 0) {
            float ratio = (float) statsDto.getCount_mutant_dna() / (float) statsDto.getCount_human_dna();
            statsDto.setRatio(ratio);
        } else {
            statsDto.setRatio(statsDto.getCount_mutant_dna());
        }

        return statsDto;

    }

}
