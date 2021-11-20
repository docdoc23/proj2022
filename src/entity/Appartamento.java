package entity;


import constants.Constants;

public class Appartamento extends Locazione {
	
	// Variabili

	private static final long serialVersionUID = 1L;
	private String numeroStanze;
	private String numeroBagni;
	private boolean giardino;
	private String numeroLetti;

	
	// Costruttori
	public Appartamento(){//default

	}
	
	public Appartamento(String nomeLocazione,String postiTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String numeroStanze, String numeroBagni, boolean giardino, String numeroLetti) {
		super(nomeLocazione,postiTotali,provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
		
		this.numeroStanze = numeroStanze;
		this.numeroBagni = numeroBagni;
		this.giardino = giardino;
		this.numeroLetti = numeroLetti;
	}

	@Override
	public String selectPath()
	{
		return Constants.APPART_PATH;
	}
	
	// Getters e Setters	
	
	public String getNumeroStanze() {
		return numeroStanze;
	}

	public void setNumeroStanze(String numeroStanze) {
		this.numeroStanze = numeroStanze;
	}

	public String getNumeroBagni() {
		return numeroBagni;
	}

	public void setNumeroBagni(String numeroBagni) {
		this.numeroBagni = numeroBagni;
	}

	public boolean isGiardino() {
		return giardino;
	}

	public void setGiardino(boolean giardino) {
		this.giardino = giardino;
	}

	public String getNumeroLetti() {
		return numeroLetti;
	}

	public void setNumeroLetti(String numeroLetti) {
		this.numeroLetti = numeroLetti;
	}

	
	
}

