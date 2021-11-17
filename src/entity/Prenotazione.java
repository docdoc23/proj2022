package entity;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Prenotazione implements Serializable{
    private String nomeLocazione;
    private String proprietario;
    private String cliente;
    private GregorianCalendar dataInizio;
    private GregorianCalendar dataFine;
    private String tipo;
    private String prezzo;
    private String numeroPersone;


    public Prenotazione(String nomeLocazione, String proprietario, String cliente, GregorianCalendar dataInizio, GregorianCalendar dataFine,
                        String tipo, String prezzo, String numeroPersone){
        this.nomeLocazione= nomeLocazione;
        this.proprietario = proprietario;
        this.cliente = cliente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.tipo=tipo;
        this.prezzo=prezzo;
        this.numeroPersone=numeroPersone;
    }

    public String getNomeLocazione() {
        return nomeLocazione;
    }

    public void setNomeLocazione(String nomeLocazione) {
        this.nomeLocazione = nomeLocazione;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getCliente() {
        return cliente;
    }

    public GregorianCalendar getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(GregorianCalendar dataInizio) {
        this.dataInizio = dataInizio;
    }

    public GregorianCalendar getDataFine() {
        return dataFine;
    }

    public void setDataFine(GregorianCalendar dataFine) {
        this.dataFine = dataFine;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public String getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(String numeroPersone) {
        this.numeroPersone = numeroPersone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prenotazione that = (Prenotazione) o;

        if (nomeLocazione != null ? !nomeLocazione.equals(that.nomeLocazione) : that.nomeLocazione != null)
            return false;
        if (proprietario != null ? !proprietario.equals(that.proprietario) : that.proprietario != null) return false;
        if (cliente != null ? !cliente.equals(that.cliente) : that.cliente != null) return false;
        if (dataInizio != null ? !dataInizio.equals(that.dataInizio) : that.dataInizio != null) return false;
        if (dataFine != null ? !dataFine.equals(that.dataFine) : that.dataFine != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (prezzo != null ? !prezzo.equals(that.prezzo) : that.prezzo != null) return false;
        return numeroPersone != null ? numeroPersone.equals(that.numeroPersone) : that.numeroPersone == null;

    }

    @Override
    public int hashCode() {
        int result = nomeLocazione != null ? nomeLocazione.hashCode() : 0;
        result = 31 * result + (proprietario != null ? proprietario.hashCode() : 0);
        result = 31 * result + (cliente != null ? cliente.hashCode() : 0);
        result = 31 * result + (dataInizio != null ? dataInizio.hashCode() : 0);
        result = 31 * result + (dataFine != null ? dataFine.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (prezzo != null ? prezzo.hashCode() : 0);
        result = 31 * result + (numeroPersone != null ? numeroPersone.hashCode() : 0);
        return result;
    }
}
