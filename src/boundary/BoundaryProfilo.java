package boundary;

import control.ControlloreProfilo;
import entity.Utente;


public class BoundaryProfilo {

    private ControlloreProfilo cP;

    public BoundaryProfilo() {
        cP = new ControlloreProfilo();
    }

    public Utente ritornaUtente(String username) {
        return cP.ottieniUtente(username);
    }

    public int modificaUtente(String username, String nome, String cognome,
                              String email, String sesso, String nascita,
                              String vecchiaPassword, String nuovaPassword,
                              String confermaNuovaPassword) {
        return cP.modificaProfilo(username, nome, cognome, email, sesso,
                nascita, vecchiaPassword, nuovaPassword, confermaNuovaPassword);
    }
}