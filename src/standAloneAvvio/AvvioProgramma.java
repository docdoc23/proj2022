package standAloneAvvio;

import constants.Constants;
import entity.Utente;
import exception.SerializzazioneException;
import standaloneboundary.BoundaryAvvio;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class AvvioProgramma {
	
	private String percorsoAmministratori = Constants.ADMIN_PATH;
	
	// Costruttore
	private AvvioProgramma(){
		
	}
	
	// Se il file percorsoAmministratori è vuoto, il metodo aggiunge l'amministratore root
	private void checkRoot() throws SerializzazioneException{
		File file = new File(percorsoAmministratori);
		if(file.length() == 0){
			ArrayList<Utente> amministratori = new ArrayList<>();
			//Utente
			Utente u = new Utente("root","root",
                    "root","root","root",
                    "", Locale.getDefault(),
                    "", ""
            );
			amministratori.add(u);
			SerializzaOggetti
					.serializza(amministratori,
                            percorsoAmministratori);
		}
	}
	
	public static void main(String[] args) throws SerializzazioneException {
		AvvioProgramma ap = new AvvioProgramma();
		ap.checkRoot();		
		new BoundaryAvvio();
	}
}
