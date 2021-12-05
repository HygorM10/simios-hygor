package br.com.hygor.simios.application.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.hygor.simios.adapters.outbound.persistence.entities.Dna;
import br.com.hygor.simios.adapters.outbound.smtp.DnaService;
import br.com.hygor.simios.application.domain.StatsDto;
import br.com.hygor.simios.application.domain.enums.DnaTypeEnum;

public class StatsServiceTest {

    @InjectMocks
    private StatsService statsService;

    @Mock
    private DnaService dnaService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getStatsMutantAndHuman_withMutantsAndHumans_mustCreateStats() {

        List<Dna> dnasHumans = Arrays.asList(new Dna(new ObjectId(), this.getMatrix(), DnaTypeEnum.HUMANO));
        Mockito.when(dnaService.findAllByDnaTypeEnum(DnaTypeEnum.HUMANO)).thenReturn(dnasHumans);

        List<Dna> dnaMutants = Arrays.asList(new Dna(new ObjectId(), this.getMatrix(), DnaTypeEnum.MUTANTE));
        Mockito.when(dnaService.findAllByDnaTypeEnum(DnaTypeEnum.MUTANTE)).thenReturn(dnaMutants);

        StatsDto statsMutantAndHuman = statsService.getStatsMutantAndHuman();

        assertThat(statsMutantAndHuman.getCount_human_dna(), is(1));
        assertThat(statsMutantAndHuman.getCount_mutant_dna(), is(1));
        assertThat(statsMutantAndHuman.getRatio(), is((float)1.0));

    }

    @Test
    public void getStatsMutantAndHuman_withoutMutantsAndHumans_mustCreateStats() {

        Mockito.when(dnaService.findAllByDnaTypeEnum(DnaTypeEnum.HUMANO)).thenReturn(null);
        Mockito.when(dnaService.findAllByDnaTypeEnum(DnaTypeEnum.MUTANTE)).thenReturn(null);

        StatsDto statsMutantAndHuman = statsService.getStatsMutantAndHuman();

        assertThat(statsMutantAndHuman.getCount_human_dna(), is(0));
        assertThat(statsMutantAndHuman.getCount_mutant_dna(), is(0));
        assertThat(statsMutantAndHuman.getRatio(), is((float)0.0));

    }

    private String[][] getMatrix() {

        String[][] matrix = {
                {"A", "T", "G", "C", "G", "A"},
                {"C", "A", "G", "T", "G", "C"},
                {"T", "T", "A", "T", "G", "T"},
                {"A", "G", "A", "A", "G", "G"},
                {"C", "C", "C", "C", "T", "A"},
                {"T", "C", "A", "C", "T", "G"}
        };

        return matrix;

    }

}
