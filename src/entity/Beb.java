package entity;


import constants.Constants;

public class Beb extends Locazione {
	
	// Variabili

	private static final long serialVersionUID = 1L;
	private String orarioColazione;

	// Costruttori
	public Beb(){//default

	}
	
	public Beb(String nomeLocazione,String postiTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String orarioColazione) {
		super(nomeLocazione,postiTotali,provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
		this.orarioColazione = orarioColazione;
	}

	@Override
	public String selectPath()
	{
		return Constants.BEB;
	}
	
	// Getters e Setters
	public String getOrarioColazione() {
		return orarioColazione;
	}

	public void setOrarioColazione(String orarioColazione) {
		this.orarioColazione = orarioColazione;
	}


	
}
