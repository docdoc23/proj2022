package entity;


import java.io.Serializable;

public class Recensione implements Serializable{
    private String nomeLocazione;
    private String nomeRecensore;
    private int numeroStelle;
    private String testoRecensione;

    public Recensione(){/*default*/}

    public Recensione( String nomeLocazione, String nomeRecensore, int numeroStelle, String testoRecensione){
        this.nomeLocazione=nomeLocazione;
        this.nomeRecensore=nomeRecensore;
        this.numeroStelle=numeroStelle;
        this.testoRecensione=testoRecensione;
    }

    public String getNomeLocazione() {
        return nomeLocazione;
    }

    public void setNomeLocazione(String nomeLocazione) {
        this.nomeLocazione = nomeLocazione;
    }

    public String getNomeRecensore() {
        return nomeRecensore;
    }

    public void setNomeRecensore(String nomeRecensore) {
        this.nomeRecensore = nomeRecensore;
    }

    public int getNumeroStelle() {
        return numeroStelle;
    }

    public void setNumeroStelle(int numeroStelle) {
        this.numeroStelle = numeroStelle;
    }

    public String getTestoRecensione() {
        return testoRecensione;
    }

    public void setTestoRecensione(String testoRecensione) {
        this.testoRecensione = testoRecensione;
    }
}
