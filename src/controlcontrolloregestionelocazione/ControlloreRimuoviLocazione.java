package controlcontrolloregestionelocazione;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;



// Questa classe serve a rimuovere una locazione dal sistema da parte del proprietario. 
// Vengono anche eliminate le prenotazioni associate alla locazione da rimuovere

public class ControlloreRimuoviLocazione {
	
	// Percorsi
	

	private static String percorsoPrenotatiAlbergo = Constants.PRENOTATI_ALBERGO_PATH;
	private static String percorsoPrenotatiAppartamento = Constants.PRENOTATI_APPARTAMENTO_PATH;
	private static String percorsoPrenotatiBeb = Constants.PRENOTATI_BEB_PATH;
	private static String percorsoPrenotatiCasaVacanza = Constants.PRENOTATI_CASEVACANZA_PATH;
	private static String percorsoPrenotatiOstello = Constants.PRENOTATI_OSTELLO_PATH;

	private static String percorsoPrenotazioniAlberghi = Constants.PRENOTAZIONE_ALBERGO_PATH;
	private static String percorsoPrenotazioniBeb = Constants.PRENOTAZIONE_BEB_PATH;
	private static String percorsoPrenotazioniOstelli = Constants.PRENOTAZIONE_OSTELLO_PATH;
	private static String percorsoPrenotazioniAppartamenti = Constants.PRENOTAZIONE_APPARTAMENTO_PATH;
	private static String percorsoPrenotazioniCaseVacanza = Constants.PRENOTAZIONE_CASAVACANZA_PATH;
	
	// Costruttore
	
	public ControlloreRimuoviLocazione(){// Costruttore
		
	}

	//metodo che passa al metodo privato "rimuoviLocazione" la locazione scelta dall'utente e il percorso corrispondente
	//chiama dunque anche le operazioni per rimuovere le corrispondenti PostiDisponibili,Prenotazioni e Recensioni corrispondenti a quella locazione
	@SuppressWarnings("unchecked")
	public String rimuovi(Locazione locazione) throws DeserializzazioneException, SerializzazioneException{

		String nomeLocazione = locazione.getNomeLocazione();

		//generale per tutti i tipi di locazioni
		rimuoviLocazione(locazione,locazione.selectPath());

		File fRecensioni= new File(Constants.RECENSIONI_PATH);
		if (fRecensioni.length()!=0) {
			rimuoviRecensioni(locazione);
		}

		if(locazione.getClass()==Albergo.class){

			File fPrenotazioni = new File(percorsoPrenotazioniAlberghi);
			File fPrenotati = new File(percorsoPrenotatiAlbergo);

			if(fPrenotazioni.length() != 0)
				rimuoviPostiDisponibili(locazione,percorsoPrenotazioniAlberghi);
			if(fPrenotati.length() != 0)
				rimuoviPrenotazione(locazione,percorsoPrenotatiAlbergo);

		}
		else if(locazione.getClass()==Appartamento.class){

			File fPrenotazioni = new File(percorsoPrenotazioniAppartamenti);
			File fPrenotati = new File(percorsoPrenotatiAppartamento);

			if(fPrenotazioni.length() != 0)
				rimuoviPostiDisponibili(locazione,percorsoPrenotazioniAppartamenti);
			if(fPrenotati.length() != 0)
				rimuoviPrenotazione(locazione,percorsoPrenotatiAppartamento);
		}
		else if(locazione.getClass()==Beb.class){

			File fPrenotazioni = new File(percorsoPrenotazioniBeb);
			File fPrenotati = new File(percorsoPrenotatiBeb);

			if(fPrenotazioni.length() != 0)
				rimuoviPostiDisponibili(locazione,percorsoPrenotazioniBeb);
			if(fPrenotati.length() != 0)
				rimuoviPrenotazione(locazione,percorsoPrenotatiBeb);
		}
		else if(locazione.getClass()==CasaVacanza.class){

			File fPrenotazioni = new File(percorsoPrenotazioniCaseVacanza);
			File fPrenotati = new File(percorsoPrenotatiCasaVacanza);

			if(fPrenotazioni.length() != 0)
				rimuoviPostiDisponibili(locazione,percorsoPrenotazioniCaseVacanza);
			if(fPrenotati.length() != 0)
				rimuoviPrenotazione(locazione,percorsoPrenotatiCasaVacanza);
		}
		else {

			File fPrenotazioni = new File(percorsoPrenotazioniOstelli);
			File fPrenotati = new File(percorsoPrenotatiOstello);

			if(fPrenotazioni.length() != 0)
				rimuoviPostiDisponibili(locazione,percorsoPrenotazioniOstelli);
			if(fPrenotati.length() != 0)
				rimuoviPrenotazione(locazione,percorsoPrenotatiOstello);


		}
		return nomeLocazione;


	}
	//metodo che rimuove una locazione da un file
	@SuppressWarnings("unchecked")
	private void rimuoviLocazione(Locazione locazione, String percorso) throws DeserializzazioneException, SerializzazioneException {
		String nomeLocazione = locazione.getNomeLocazione();
		String provincia =locazione.getProvincia();
		String indirizzo = locazione.getIndirizzo();
		ArrayList<Locazione> locazioni;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		SerializzaOggetti sobj = new SerializzaOggetti();

		locazioni=(ArrayList<Locazione>) dobj.deserializza(percorso);

		for(int i=0; i<locazioni.size();++i){
			if(locazioni.get(i).getNomeLocazione().equals(nomeLocazione)&&
					locazioni.get(i).getProvincia().equals(provincia)&&
					locazioni.get(i).getIndirizzo().equals(indirizzo)){
						locazioni.remove(i);

			}
		}
		sobj.serializza(locazioni,percorso);
	}

	//metodo che rimuove i posti disponibili legate ad una determinata locazione
	@SuppressWarnings("unchecked")
	private void rimuoviPostiDisponibili(Locazione locazione, String percorso) throws DeserializzazioneException, SerializzazioneException {
		String nomeLocazione = locazione.getNomeLocazione();
		ArrayList<PostiDisponibili> postiDisponibili;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		SerializzaOggetti sobj = new SerializzaOggetti();

		postiDisponibili = (ArrayList<PostiDisponibili>) dobj.deserializza(percorso);

		for(int i=0; i<postiDisponibili.size(); ++i){
			if(postiDisponibili.get(i).getNomeLocazion().equals(nomeLocazione)) {
				postiDisponibili.remove(i);
			}
		}
		sobj.serializza(postiDisponibili,percorso);

	}

	//metodo che rimuove le prenotazioni legate ad una determinata locazione
	@SuppressWarnings("unchecked")
	private void rimuoviPrenotazione(Locazione locazione, String percorso) throws DeserializzazioneException, SerializzazioneException {
		String nomeLocazione = locazione.getNomeLocazione();
		ArrayList<Prenotazione> prenotazioni;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		SerializzaOggetti sobj = new SerializzaOggetti();

		prenotazioni = (ArrayList<Prenotazione>) dobj.deserializza(percorso);

		for(int i=0; i< prenotazioni.size(); ++i){
			if(prenotazioni.get(i).getNomeLocazione().equals(nomeLocazione)){
				prenotazioni.remove(i);
			}
		}
		sobj.serializza(prenotazioni,percorso);

	}

	//metodo che rimuove le recensioni legate ad una determinata locazione
	private void rimuoviRecensioni(Locazione locazione) throws DeserializzazioneException, SerializzazioneException {
		String nomeLocazione = locazione.getNomeLocazione();
		ArrayList<Recensione> recensioni;


		recensioni = (ArrayList<Recensione>) DeserializzaOggetti.deserializza(Constants.RECENSIONI_PATH);

		for(int i=0; i< recensioni.size(); ++i){
			if(recensioni.get(i).getNomeLocazione().equals(nomeLocazione)){
				recensioni.remove(i);
			}
		}
		SerializzaOggetti.serializza(recensioni,Constants.RECENSIONI_PATH);


	}
}
