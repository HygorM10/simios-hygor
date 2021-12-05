package br.com.hygor.simios.application.domain;

public class DnaDto {

    private String[] dna;

    public DnaDto() {
    }

    public DnaDto(String[] dna) {
        this.dna = dna;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
