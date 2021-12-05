package br.com.hygor.simios.adapters.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.com.hygor.simios.application.domain.enums.QtMatrixSequenceEnum;

public class MatrixSequenceUtilTest {

    @InjectMocks
 	   private MatrixSequenceUtil matrixSequenceUtil;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllSequences_withSimianMatrix_mustReturnMoreOneSequences() throws ExecutionException, InterruptedException {

        int total = matrixSequenceUtil.getAllSequences(this.getMatrixWithSimianDna(), 4);

        assertThat(total, is(QtMatrixSequenceEnum.QT_MAX_SEQUENCE_SIMIAN.getQtMaxSequence()));

    }

    @Test
    public void getAllSequences_withoutSimianMatrix_mustReturnZeroSequences() throws ExecutionException, InterruptedException {

        int total = matrixSequenceUtil.getAllSequences(this.getMatrixWithInvalidData(), 4);

        assertThat(total, is(0));

    }

    private String[][] getMatrixWithSimianDna() {

        String[][] matrix = {
                {"A", "T", "G", "C", "G", "G"},
                {"C", "A", "G", "T", "G", "C"},
                {"T", "T", "A", "G", "G", "T"},
                {"A", "G", "G", "A", "G", "G"},
                {"C", "C", "C", "C", "T", "A"},
                {"T", "C", "A", "C", "T", "G"}
        };

        return matrix;

    }

    private String[][] getMatrixWithInvalidData() {

        String[][] matrix = {
                {"D", "T", "G", "C", "G", "A"},
                {"C", "A", "G", "T", "G", "C"},
                {"T", "T", "A", "T", "C", "T"},
                {"A", "G", "A", "A", "G", "G"},
                {"C", "G", "C", "C", "T", "A"},
                {"T", "C", "A", "C", "T", "G"}
        };

        return matrix;

    }
}