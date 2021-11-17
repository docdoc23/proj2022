package boundary;

import control.ControlloreRegistrazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.util.Locale;


public class BoundaryRegistrazione {

    private ControlloreRegistrazione cR;

    public BoundaryRegistrazione() {
        cR = new ControlloreRegistrazione();
    }

    public int registrazione(String username, String password,
                             String pasword2, String nome,
                             String cognome, String email,
                             Locale lingua, String nascita,
                             String sesso)
    throws DeserializzazioneException, SerializzazioneException {
        return cR.registrazione(username, password, pasword2,
                nome, cognome, email, lingua, nascita, sesso);
    }
}
