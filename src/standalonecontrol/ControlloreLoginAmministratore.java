package standalonecontrol;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import standaloneboundary.BoundaryAmministrazione;
import standaloneboundary.BoundaryLoginErrato;
import utils.DeserializzaOggetti;

import java.util.ArrayList;
import java.util.List;

public class ControlloreLoginAmministratore {
	
	//Variabili e percorso
	
	public static List<Utente> utenti = new ArrayList<>();

	//Costruttore
	public ControlloreLoginAmministratore(){//default
	
	}
	
	// Deserializza gli Utenti del file utenti e li posiziona in un ArrayList. Viene fatta una scansione
	// di tutti gli elementi e viene verificata la corrispondenza di Username e Password inserite
	
	@SuppressWarnings("unchecked")
	public void login(String username, char[] password) throws DeserializzazioneException{

		String pw = String.valueOf(password);
		utenti = (ArrayList<Utente>) DeserializzaOggetti
				.deserializza(Constants.ADMIN_PATH);


        for (Utente anUtenti : utenti) {
            if (anUtenti.getUsername().equals(username)) {
                if (anUtenti.getPassword().equals(pw)) {
                    String lang = anUtenti
                            .getLingua()
                            .getLanguage();
                    System.setProperty(Constants.USER_KEY, username);
                    System.setProperty(Constants.LINGUA_KEY, lang);


                    new BoundaryAmministrazione();
                    return;
                } else {
                    break;
                }
            }
        }
		new BoundaryLoginErrato();
	}
}