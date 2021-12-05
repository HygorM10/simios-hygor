package br.com.hygor.simios.application.ports;

import br.com.hygor.simios.application.domain.enums.DnaTypeEnum;

public interface DnaServicePorts {

	void saveIfUniqueDna(String[][] dna, DnaTypeEnum dnaTypeEnum);
	
}
