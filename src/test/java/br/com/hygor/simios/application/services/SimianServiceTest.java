package br.com.hygor.simios.application.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.hygor.simios.adapters.configuration.exception.DnaInvalidException;
import br.com.hygor.simios.adapters.configuration.exception.SimiosCommonException;
import br.com.hygor.simios.adapters.outbound.smtp.DnaService;
import br.com.hygor.simios.adapters.util.FormatUtil;
import br.com.hygor.simios.adapters.util.MatrixSequenceUtil;
import br.com.hygor.simios.adapters.util.ValidUtil;
import br.com.hygor.simios.application.domain.enums.QtMatrixSequenceEnum;

public class SimianServiceTest {

    @InjectMocks
    private SimianService simianService;

    @Mock
    private ValidUtil validUtil;

    @Mock
    private FormatUtil formatUtil;

    @Mock
    private DnaService dnaService;

    @Mock
    private MatrixSequenceUtil matrixSequenceUtil;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isSimian_withMatrixWithInvalidData_mustInvokeDnaInvalidException() throws SimiosCommonException {

        try {
            String[] dna = {"DTGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
            when(formatUtil.formatToSquareBidimensional(dna)).thenReturn(this.getMatrixWithInvalidData());
            Boolean simian = simianService.isSimian(dna);
            fail("Test failed");
        } catch (DnaInvalidException e) {
            assertThat(e.getMessage(), is("Ops, DNA inválido."));
        } catch (InterruptedException e) {
            fail("Test failed");
        } catch (ExecutionException e) {
            fail("Test failed");
        }

    }

    @Test
    public void isSimian_withMatrixWithSimianDna_mustReturnTrue() throws SimiosCommonException, ExecutionException, InterruptedException {

        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        String[][] matrixWithSimianDna = this.getMatrixWithSimianDna();
        when(formatUtil.formatToSquareBidimensional(dna)).thenReturn(matrixWithSimianDna);
        when(matrixSequenceUtil.getAllSequences(matrixWithSimianDna, QtMatrixSequenceEnum.QT_MAX_SEQUENCE_SIMIAN.getQtMaxSequence())).thenReturn(3);
        Boolean isSimian = simianService.isSimian(dna);
        assertTrue(isSimian);

    }
    
    @Test
    public void isSimian_withMatrixWithSimianDna_mustReturnFalse() throws SimiosCommonException, ExecutionException, InterruptedException {

        String[] dna = {"ATCG", "GGTA", "GATC", "ATCG"};
        String[][] matrixWithNoSimianDna = this.getMatrixWithNoSimianDna();
        when(formatUtil.formatToSquareBidimensional(dna)).thenReturn(matrixWithNoSimianDna);
        Boolean isSimian = simianService.isSimian(dna);
        assertFalse(isSimian);

    }

    private String[][] getMatrixWithInvalidData() {

        String[][] matrix = {
                {"D", "T", "G", "C", "G", "A"},
                {"C", "A", "G", "T", "G", "C"},
                {"T", "T", "A", "T", "G", "T"},
                {"A", "G", "A", "A", "G", "G"},
                {"C", "C", "C", "C", "T", "A"},
                {"T", "C", "A", "C", "T", "G"}
        };

        return matrix;

    }

    private String[][] getMatrixWithSimianDna() {

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
    
    private String[][] getMatrixWithNoSimianDna() {

    	String[][] matrix = {
                {"A", "T", "C", "G"},
                {"G", "G", "T", "A"},
                {"G", "A", "T", "C"},
                {"A", "T", "C", "G"}
        };

        return matrix;

    }
}