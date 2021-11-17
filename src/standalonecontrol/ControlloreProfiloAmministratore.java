package standalonecontrol;

import constants.Constants;
import control.ControlloreProfilo;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by alfredo
 */

// Classe che consente la gestione del profilo personale, in particolare la restituzione dei valori
// associati ad un particolare getUtente, modificarli, e modificare l'immagine.
public class ControlloreProfiloAmministratore
extends ControlloreProfilo {

    // Costruttore
    public ControlloreProfiloAmministratore() {//default
    }

    // Viene dato in input un username, il metodo ricerca nel file l'oggetto Utente corrispondente e lo restituisce.
    @SuppressWarnings("unchecked")
    @Override
    public Utente ottieniUtente(String username) {

        ArrayList<Utente> utenti = null;
        try {
            utenti = (ArrayList<Utente>) DeserializzaOggetti
                    .deserializza(Constants.ADMIN_PATH);
        } catch (DeserializzazioneException e) {
            e.printStackTrace();
        }

        assert utenti != null;
        for (Utente anUtenti : utenti)
            if (anUtenti.getUsername().equals(username))
                return anUtenti;

        return null;

    }

    // Metodo che consente la modifca del profilo, viene effettuato un controllo sull'input per verificare che tutti i parametri
    // siano corretti, se tutto è corretto prende l'getUtente corrispondente dal file, ne modifica i parametri e lo salva sul file.
    // L'unico campo che non è modificabile èl'username, perchè consente di identificare univocamente l'getUtente nel sistema.

    // Ritorna: 1 --> Password vecchia non corretta
    //			3 --> La nuova password è vuota
    //			4 --> Sessione getUtente scaduta
    //			0 --> Se tutto va bene
    //         -1 --> Se c'è un errore di serilizzazione/deserializzazione
    @SuppressWarnings("unchecked")
    public int modificaProfilo(String username,
                                            String email,
                                            String vecchiaPassword, String nuovaPassword, String confermaNuovaPassword, Locale lang) {
        ArrayList<Utente> utenti;
        try {
            utenti = (ArrayList<Utente>) DeserializzaOggetti
                    .deserializza(Constants.ADMIN_PATH);
        } catch (DeserializzazioneException e) {
            e.printStackTrace();
            return -1;
        }

        if (username == null || email == null ||
                vecchiaPassword == null || nuovaPassword == null ||
                confermaNuovaPassword == null) return 4;

        for (Utente anUtenti : utenti) {
            if (anUtenti.getUsername().equals(username)) {

                int result = this.checkPsw(anUtenti, vecchiaPassword,
                        nuovaPassword, confermaNuovaPassword);
                if (result != 0)
                    return result;

                anUtenti.setEmail(email);

                if (lang != null)
                    anUtenti.setLingua(lang);
            }
        }

        try {
            SerializzaOggetti.serializza(utenti, Constants.ADMIN_PATH);
        } catch (SerializzazioneException e) {
            e.printStackTrace();
            return -1;
        }

        return 0;
    }

}