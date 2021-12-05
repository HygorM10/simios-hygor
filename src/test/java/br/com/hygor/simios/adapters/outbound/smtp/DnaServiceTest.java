package br.com.hygor.simios.adapters.outbound.smtp;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.hygor.simios.adapters.outbound.persistence.DnaRepository;
import br.com.hygor.simios.adapters.outbound.persistence.entities.Dna;
import br.com.hygor.simios.application.domain.enums.DnaTypeEnum;

public class DnaServiceTest {

    @InjectMocks
    private DnaService dnaService;

    @Mock
    private DnaRepository dnaRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveIfUniqueDna_withExistsDna_mustNeverInvokeSaveMethod() {

        Dna dna = new Dna(new ObjectId(), this.getMatrix(), DnaTypeEnum.HUMANO);
        when(dnaRepository.findByDna(any())).thenReturn(dna);

        dnaService.saveIfUniqueDna(this.getMatrix(), DnaTypeEnum.HUMANO);

        verify(dnaRepository, never()).save(any());

    }

    @Test
    public void saveIfUniqueDna_withoutExistsDna_mustOneTimeInvokeSaveMethod() {

        when(dnaRepository.findByDna(any())).thenReturn(null);

        dnaService.saveIfUniqueDna(this.getMatrix(), DnaTypeEnum.HUMANO);

        verify(dnaRepository, times(1)).save(any());

    }

    @Test
    public void findAllByDnaTypeEnum_withExistsDna_mustReturnList() {

        List<Dna> dnasEsperado = Arrays.asList(new Dna(new ObjectId(), this.getMatrix(), DnaTypeEnum.MUTANTE));
        when(dnaRepository.findAllByDnaTypeEnum(DnaTypeEnum.MUTANTE)).thenReturn(dnasEsperado);

        List<Dna> dnasAtual = dnaService.findAllByDnaTypeEnum(DnaTypeEnum.MUTANTE);

        Assert.assertThat(dnasAtual, is(dnasEsperado));

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