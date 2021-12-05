package br.com.hygor.simios.adapters.outbound.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.hygor.simios.adapters.outbound.persistence.entities.Dna;
import br.com.hygor.simios.application.domain.enums.DnaTypeEnum;

@Repository
public interface DnaRepository extends MongoRepository<Dna, Long> {

    Dna findByDna(String[][] dna);

    List<Dna> findAllByDnaTypeEnum(DnaTypeEnum dnaTypeEnum);

}
