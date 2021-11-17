package standalonecontrol;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standaloneboundary.BoundaryFallimento;
import standaloneboundary.BoundarySuccesso;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.util.ArrayList;
import java.util.Locale;

public class ControlloreCreaAmministratore {

	//Costruttore
	public ControlloreCreaAmministratore(){//default
		
	}
	
	//Metodo che verifica la presenza di un utente registrato con l'username indicato come input. Se la presenza � conferma
	//viene inserito nell'arraylist del file degli amministratori.
	
	@SuppressWarnings("unchecked")
	public void creaAmministratore(String username, char[] password )
			throws DeserializzazioneException, SerializzazioneException{

        String pw = String.valueOf(password);
        ArrayList<Utente> amministratori ;

        if(username.equals("")|| pw.equals("")){
            new BoundaryFallimento("Hai lasciato qualche campo vuoto");
            return ;
        }

		amministratori = (ArrayList<Utente>) DeserializzaOggetti
				.deserializza(Constants.ADMIN_PATH);
		boolean controllo = true;

		for (Utente anAmministratori : amministratori) {
			if (anAmministratori.getUsername().equals(username)) {
				controllo = false;
				break;
			}
		}
		
		if(!controllo){
			new BoundaryFallimento("Username non disponibile");
			return ;
		}
		Utente u = new Utente(username, pw, " ", " ", "", " ",
				Locale.getDefault(), "", "");

		// rimuove l'utente root
		boolean root = amministratori
                    .get(0).getUsername().equals("root") &&
                amministratori.get(0).getPassword().equals("root");
        if (root)
            amministratori.remove(0);

		amministratori.add(u);
		
		SerializzaOggetti.serializza(amministratori, Constants.ADMIN_PATH);
		new BoundarySuccesso();
		
		
	}
	
}
