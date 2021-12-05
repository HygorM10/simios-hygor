package br.com.hygor.simios.application.ports;

import java.util.concurrent.ExecutionException;

import br.com.hygor.simios.adapters.configuration.exception.DnaInvalidException;
import br.com.hygor.simios.adapters.configuration.exception.SimiosCommonException;

public interface SimianServicePorts {

	Boolean isSimian(String[] dna) throws ExecutionException, InterruptedException, SimiosCommonException;
	
}
