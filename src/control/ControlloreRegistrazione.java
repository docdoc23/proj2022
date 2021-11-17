package control;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;

import java.util.Locale;

public class ControlloreRegistrazione {

	private ArrayList<Utente> utenti;

	public ControlloreRegistrazione() {

            this.utenti = new ArrayList<>();
	}


    /**
     * Inserimento nuovo Utente nel sistema.
     * Viene restituito un intero che indica il tipo di errore.
     * @param username nome utente univoco che identifica l'utente nel sistema
     * @param password codice di sicurezza associato a @username per accedere ai servizi del sistema
     * @param password2 conferma del corretto inserimento di @password
     * @param nome nome dell'utente che intende rgistrarsi nel sistema
     * @param cognome cognome dell'utente che intende registrarsi nel sistema
     * @param email indirizzo e-mail dell'utente che intende registrarsi nel sistema
     * @param lingua lingua corrente del sistema su ci si effettua la registrazione
     * @param nascita data di nascita dell'utente
     * @param sesso sesso dell'utente
     * @return  0 --> Tutto ok
     *          1 --> C'è qualche campo del form vuoto
     *          2 --> Le due password non sono uguali
     *          3 --> Lo username inserito è già stato utilizzato
     * @throws DeserializzazioneException
     * @throws SerializzazioneException
     */
	public int registrazione(String username, String password,
										  String password2, String nome,
                                          String cognome, String email, Locale lingua,
                                          String nascita, String sesso)
            throws DeserializzazioneException, SerializzazioneException {

            if (username == null ||
                    password == null ||
                    password2 == null ||
                    nome == null ||
                    cognome == null ||
                    email == null ||
                    username.equals("") ||
                    password.equals("") ||
                    password2.equals("") ||
                    nome.equals("") ||
                    cognome.equals("") ||
                    email.equals("")) return 1;

            if (!password.equals(password2)) return 2;

            String immagine = "profiloDefault.png";
            Utente ut = new Utente(username, password, nome,
                    cognome, email, immagine, lingua, nascita, sesso);

            File file = new File(Constants.UTENTI_PATH);
            if (file.length() == 0) {
                utenti.add(ut);
                SerializzaOggetti.serializza(utenti, Constants.UTENTI_PATH);
                return 0;
            }

            utenti = (ArrayList<Utente>) DeserializzaOggetti
                    .deserializza(Constants.UTENTI_PATH);

            for (Utente utente : utenti)
                if (utente.getUsername().equals(username))
                    return 3;

            utenti.add(ut);
            SerializzaOggetti.serializza(utenti, Constants.UTENTI_PATH);

            return 0;

	}
}
