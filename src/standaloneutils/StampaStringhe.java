package standaloneutils;

import constants.Constants;
import entity.Locazione;
import entity.Utente;
import exception.DeserializzazioneException;
import standalonecontrol.ControlloreLinguaAmministratore;
import utils.DeserializzaOggetti;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StampaStringhe {

	private JTextArea area;
	private ControlloreLinguaAmministratore cl;

	public StampaStringhe(JTextArea area){
	    this.cl = new ControlloreLinguaAmministratore();
		this.area = area;
	}
	
	//Percorsi
	private static String percorsoLocazioniRimosse = Constants.RIMOSSE_PATH;
	private String percorsoUtenti = Constants.UTENTI_PATH;
	
	//Viene stampato l'array degli utenti nella JTextArea.
	
	@SuppressWarnings("unchecked")
	public void visualizzaUtenti(){
		ResourceBundle bundle = this.cl.getBundleFromProp();

		ArrayList<Utente> utenti;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		String elenco = bundle.getString("boundaryRimuoviUtente_utenti_presenti");
		elenco = elenco+'\n';
		try {
			utenti = (ArrayList<Utente>) dobj.deserializza(percorsoUtenti);

			for (Utente user : utenti ){
				elenco = elenco + user.getUsername() + '\n';
			}
			area.insert(elenco,0);

		} catch (DeserializzazioneException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void visualizzaUtentiRimossiMaConDati(){
        ResourceBundle bundle = this.cl.getBundleFromProp();

		ArrayList<Locazione> locazioni;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		String elenco = bundle.getString("boundaryRimuoviUtente_rimossi");
		elenco = elenco+'\n';
		File file = new File(percorsoLocazioniRimosse);
		if(file.length()!=0) {
			try {
				locazioni = (ArrayList<Locazione>) dobj.deserializza(percorsoLocazioniRimosse);

				elenco = elenco + locazioni.get(0).getUserLocatore() + '\n';
				area.insert(elenco, 0);

			} catch (DeserializzazioneException e) {
				e.printStackTrace();
			}
		}
	}



}
