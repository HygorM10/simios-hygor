package br.com.hygor.simios.application.ports;

import br.com.hygor.simios.application.domain.StatsDto;

public interface StatsServicePorts {

	StatsDto getStatsMutantAndHuman();
	
}
