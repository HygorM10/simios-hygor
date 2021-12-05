package br.com.hygor.simios.application.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hygor.simios.adapters.configuration.exception.DnaInvalidException;
import br.com.hygor.simios.adapters.configuration.exception.SimiosCommonException;
import br.com.hygor.simios.adapters.outbound.smtp.DnaService;
import br.com.hygor.simios.adapters.util.FormatUtil;
import br.com.hygor.simios.adapters.util.MatrixSequenceUtil;
import br.com.hygor.simios.adapters.util.ValidUtil;
import br.com.hygor.simios.application.domain.enums.DnaTypeEnum;
import br.com.hygor.simios.application.domain.enums.QtMatrixSequenceEnum;
import br.com.hygor.simios.application.ports.SimianServicePorts;

@Service
public class SimianService implements SimianServicePorts{

    private final Set LETRAS_PERMITIDAS = new HashSet<>(Arrays.asList("A", "G", "C", "T"));

    @Autowired
    private ValidUtil validUtil;

    @Autowired
    private FormatUtil formatUtil;

    @Autowired
    private DnaService dnaService;

    @Autowired
    private MatrixSequenceUtil matrixSequenceUtil;

    @Override
    public Boolean isSimian(String[] dna) throws ExecutionException, InterruptedException, SimiosCommonException {

        validUtil.validArrayNullOrEmpty(dna);

        String[][] formattedDna = formatUtil.formatToSquareBidimensional(dna);

        this.validSimianDna(formattedDna);

        Boolean isSimian = matrixSequenceUtil.getAllSequences(formattedDna, QtMatrixSequenceEnum.QT_MAX_SEQUENCE_SIMIAN.getQtMaxSequence()) > 0;

        DnaTypeEnum dnaTypeEnum = isSimian ? DnaTypeEnum.MUTANTE : DnaTypeEnum.HUMANO;

        dnaService.saveIfUniqueDna(formattedDna, dnaTypeEnum);

        return isSimian;

    }

    private void validSimianDna(String[][] dna) throws DnaInvalidException {

        for (int i=0; i < dna.length; i++) {
            for (int k=0; k < dna.length; k++) {
                if (!LETRAS_PERMITIDAS.contains(dna[i][k])) {
                    throw new DnaInvalidException();
                }
            }
        }

    }

}
