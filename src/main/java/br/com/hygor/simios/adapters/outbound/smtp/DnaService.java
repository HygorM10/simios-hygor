package br.com.hygor.simios.adapters.outbound.smtp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hygor.simios.adapters.outbound.persistence.DnaRepository;
import br.com.hygor.simios.adapters.outbound.persistence.entities.Dna;
import br.com.hygor.simios.application.domain.enums.DnaTypeEnum;
import br.com.hygor.simios.application.ports.DnaServicePorts;

@Service
public class DnaService implements DnaServicePorts{

    @Autowired
    private DnaRepository dnaRepository;

    @Override
    public void saveIfUniqueDna(String[][] dna, DnaTypeEnum dnaTypeEnum) {
        Dna dnaSaved = dnaRepository.findByDna(dna);
        if (dnaSaved == null) {
            Dna dnaDocument = new Dna();
            dnaDocument.setDna(dna);
            dnaDocument.setDnaTypeEnum(dnaTypeEnum);
            dnaRepository.save(dnaDocument);
        }
    }

    public List<Dna> findAllByDnaTypeEnum(DnaTypeEnum dnaTypeEnum) {
        return dnaRepository.findAllByDnaTypeEnum(dnaTypeEnum);
    }

}
