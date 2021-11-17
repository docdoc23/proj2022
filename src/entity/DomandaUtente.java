package entity;

import java.io.Serializable;


public class DomandaUtente implements Serializable{

    private static final long serialVersionUID = 1L;


    private int type; //0=faqViaggiatore 1=faqProprietario
    private String domanda;
    private String risposta;
    private Boolean settaRisposta;

    public DomandaUtente(String domanda, String risposta, Boolean settaRisposta, int type){
        this.domanda = domanda;
        this.risposta = risposta;
        this.settaRisposta = settaRisposta;
        this.type=type;
    }

    public String getDomanda() {
        return domanda;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public Boolean getSettaRisposta() {
        return settaRisposta;
    }

    public void setSettaRisposta(Boolean settaRisposta) {
        this.settaRisposta = settaRisposta;
    }

    public int getType() {
        return type;
    }


}
