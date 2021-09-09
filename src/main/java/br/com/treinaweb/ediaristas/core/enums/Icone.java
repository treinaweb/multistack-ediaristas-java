package br.com.treinaweb.ediaristas.core.enums;

public enum Icone {

    TWF_CLEANING_1("twf-cleaning-1"),
    TWF_CLEANING_2("twf-cleaning-2"),
    TWF_CLEANING_3("twf-cleaning-3");

    private String nome;

    private Icone(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
}
