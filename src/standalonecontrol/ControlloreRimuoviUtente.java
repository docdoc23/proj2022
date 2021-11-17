package standalonecontrol;

import constants.Constants;
import controlcontrolloregestionelocazione.ControlloreVisualizzaLocazioni;
import entity.Locazione;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standaloneboundary.BoundaryFallimento;
import standaloneboundary.BoundarySuccesso;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControlloreRimuoviUtente {
	
	//Percorso
	
	public String percorsoUtenti = Constants.UTENTI_PATH;
	public String percorsoLocazioniRimosse = Constants.RIMOSSE_PATH;
	
	//Costruttore
	
	public ControlloreRimuoviUtente(){//default
		
		
	}
	
	// Viene verificata la presenza dell'utente nel sistema, una volta accertata viene rimosso dall'arraylist, che viene riserializzato.
	// Vengono utilizzati le utils per stampare a schermo l'array degli utenti prima e dopo la rimozione.
	// inoltre vengono aggiunte le locazioni dell'utente rimosso al file "locazioniRimosse" in modo che possano essere rimosse se
	// l'utente lo desidera con il ControlloreRimuoviDatiUtente
	
	@SuppressWarnings("unchecked")
	public void rimuovi(String username) throws DeserializzazioneException, SerializzazioneException, InterruptedException, IOException {
        ControlloreProfiloAmministratore cp =
                new ControlloreProfiloAmministratore();
        Utente utente = cp.ottieniUtente(System.getProperty(Constants.USER_KEY));

        Locale langLocale;
        if (utente != null) {
            langLocale = utente.getLingua();
        } else {
            langLocale = Locale.getDefault();
        }

        ResourceBundle bundle = ResourceBundle
                .getBundle(Constants.PACKAGE_LANGUAGE, langLocale);

		File file = new File(percorsoUtenti);
		if(file.length()!=0) {
			ArrayList<Utente> utenti;
			DeserializzaOggetti dobj = new DeserializzaOggetti();
			utenti = (ArrayList<Utente>) dobj.deserializza(percorsoUtenti);


			for (int i = 0; i < utenti.size(); i++) {
				if (utenti.get(i).getUsername().equals(username)) {

					ControlloreVisualizzaLocazioni cvl = new ControlloreVisualizzaLocazioni();
					List<Locazione> locazioni = cvl.visualizzaLocazioni(username);

					File fileLoc = new File(percorsoLocazioniRimosse);
					if (fileLoc.length() != 0) {
						DeserializzaOggetti dl = new DeserializzaOggetti();
						ArrayList<Locazione> locazioniPrecedenti = (ArrayList<Locazione>) dl.deserializza(percorsoLocazioniRimosse);
						locazioniPrecedenti.addAll(locazioni);
						SerializzaOggetti s = new SerializzaOggetti();
						s.serializza(locazioniPrecedenti, percorsoLocazioniRimosse);
					} else {
						SerializzaOggetti s = new SerializzaOggetti();
						s.serializza(locazioni, percorsoLocazioniRimosse);
					}

					System.out.println(bundle.getString("boundaryRimuoviUtente_utente_rimosso") + utenti.get(i).getUsername());
					utenti.remove(i);
					SerializzaOggetti sobj = new SerializzaOggetti();
					sobj.serializza(utenti, percorsoUtenti);
					new BoundarySuccesso();
					return;
				}
			}
			new BoundaryFallimento(bundle.getString("boundaryFallimento_rimuovi_utente_1"));
		}
		new BoundaryFallimento(bundle.getString("boundaryFallimento_rimuovi_utente_2"));

	}
}
