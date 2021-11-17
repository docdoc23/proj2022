package entity;


import constants.Constants;

public class Ostello extends Locazione {
	
	// Variabili
	
	private static final long serialVersionUID = 1L;
	private String tipoPensione;

	
	// Costruttori
	public Ostello(){//default

	}
	
	public Ostello(String nomeLocazione,String postiTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String tipoPensione) {
		super(nomeLocazione,postiTotali,provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
		this.tipoPensione=tipoPensione;
	}

	//metodo che calcola il prezzo base, aggiungendo al prezzo per notte il costo della pensione e moltiplicandolo per il numero di giorni
	@Override
	public int calcolaPrezzoBase(Locazione locazione,int numeroGiorni, String tipoPensione){
		if (tipoPensione.equals("completa"))
			return (((Integer.parseInt(locazione.getPrezzo().trim())) +20) * numeroGiorni );
		else {
			return (((Integer.parseInt(locazione.getPrezzo().trim())) + 10 )* numeroGiorni);
		}

	}

	@Override
	public String selectPath()
	{
		return Constants.OSTELLI_PATH;
	}

	// Getters e Setters

	public String getTipoPensione() {
		return tipoPensione;
	}

	public void setTipoPensione(String tipoPensione) {
		this.tipoPensione = tipoPensione;
	}

	
	
}
