package entity;

import java.io.Serializable;

public class Locazione implements Serializable {
	// Variabili
	private static final long serialVersionUID = 1L;
	protected String nomeLocazione;
	private String postiTotali;
	protected String provincia;
	protected String indirizzo;
	protected String userLocatore;
	protected String prezzo;
	protected String descrizione;
	protected boolean parcheggio;
	protected boolean wifi;
	protected boolean pet;
	
	// Costruttori
	public Locazione(){//default

	}
	public Locazione(String nomeLocazione,String postiTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet) {
		this.nomeLocazione = nomeLocazione;
		this.postiTotali=postiTotali;
		this.provincia= provincia;
		this.indirizzo = indirizzo;
		this.userLocatore = userLocatore;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.parcheggio = parcheggio;
		this.wifi = wifi;
		this.pet = pet;
	}

	//metodo che moltiplica il prezzo per notte per il numero di giorni
	public int calcolaPrezzoBase(Locazione locazione,int numeroGiorni, String tipoPensione){
		return numeroGiorni * (Integer.parseInt(this.getPrezzo().trim()));
	}
	
	String path = "";

	public String selectPath()
	{
		return path;
		
	}
	// Getters e Setters
	public String getNomeLocazione() {
		return nomeLocazione;
	}

	public void setNomeLocazione(String nomeLocazione) {
		this.nomeLocazione = nomeLocazione;
	}

	public String getPostiTotali(){return postiTotali;}

	public String getProvincia(){return provincia;}

	public void setProvincia(String provincia){
		this.provincia = provincia;}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getUserLocatore() {
		return userLocatore;
	}

	public void setUserLocatore(String userLocatore) {
		this.userLocatore = userLocatore;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public boolean isParcheggio() {
		return parcheggio;
	}

	public void setParcheggio(boolean parcheggio) {
		this.parcheggio = parcheggio;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public boolean isPet() {
		return pet;
	}

	public void setPet(boolean pet) {
		this.pet = pet;
	}

	public void setPostiTotali(String postiTotali) {
		this.postiTotali = postiTotali;
	}
}
