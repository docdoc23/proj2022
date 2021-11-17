package control;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ControlloreProfilo {

	
	public ControlloreProfilo() {//default
	}


    /**
     * Metodo per ricercare un tente all'interno del sistema
     * @param username utente che si intende cercare all'interno del sistema
     * @return istanza dell'entità Utente contenente tutte le informazione
     * necessarie dell'utente selezionato
     */
	public Utente ottieniUtente(String username) {


			ArrayList<Utente> utenti = new ArrayList<>();
            try {
                utenti = (ArrayList<Utente>)
                        DeserializzaOggetti
                                .deserializza(Constants.UTENTI_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            for (Utente anUtenti : utenti)
                if (anUtenti.getUsername().equals(username))
                    return anUtenti;

            return null;

	}

    /**
     * Metodo che consente la modifca del profilo. Viene effettuato un controllo
     * sull'input per verificare che tutti i parametri siano corretti, se tutto
     * è corretto seleziona l'Utente corrispondente e ne aggiorna i parametri
     * ai valori correnti.
     * @param username nome utente dell'utente che intende modificare il profilo
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param email indirizzo e-mail dell'utente
     * @param sesso sesso dell'utente
     * @param nascita data di nascita dell'utente
     * @param vecchiaPassword vecchia password dell'utente
     * @param nuovaPassword nuova password dell'utente
     * @param confermaNuovaPassword conferma della nuova password dell'utente
     * @return  1 --> Password vecchia non corretta
     *          2 --> La nuova password e la conferma della nuova password non sono uguali
     *          3 --> La nuova password è vuota
     *          4 --> Sessione utente scaduta
     *          5 --> Informazini campo "Sesso" errate
     *          6 --> Formato data errato
     *          0 --> Se tutto va bene
     */
	public int modificaProfilo(String username, String nome, String cognome,
                                            String email, String sesso, String nascita,
                                            String vecchiaPassword, String nuovaPassword,
                                            String confermaNuovaPassword) {


			ArrayList<Utente> utenti = new ArrayList<>();
            try {
                utenti = (ArrayList<Utente>) DeserializzaOggetti
                        .deserializza(Constants.UTENTI_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            if (username == null || nome == null ||
                    cognome == null || email == null ||
                    vecchiaPassword == null || nuovaPassword == null ||
                    confermaNuovaPassword == null) return 4;

            for (Utente anUtenti : utenti) {
                if (anUtenti.getUsername().equals(username)) {
                    if (!vecchiaPassword.equals("")) {
                        if (!vecchiaPassword.equals(anUtenti.getPassword()))
                            return 1;
                        else if (!nuovaPassword.equals(confermaNuovaPassword))
                            return 2;
                        else if (nuovaPassword.equals(""))
                            return 3;
                        else
                            anUtenti.setPassword(nuovaPassword);
                    } else {
                        if (!nuovaPassword.equals("") || !confermaNuovaPassword.equals(""))
                            return 1;
                    }

                    if (sesso != null) {
                        if (!sesso.equals("")) {
                            if (!(sesso.equals("M") || sesso.equals("F")))
                                return 5;
                        } else {
                            sesso = null;
                        }

                        anUtenti.setSesso(sesso);
                    }

                    if (nascita != null) {
                        if (!nascita.equals("")) {
                            String dateFormat = "dd/MM/yyyy";
                            if (nascita.length() != dateFormat.length())
                                return 6;

                            Date date = null;
                            try {
                                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                                date = sdf.parse(nascita);
                                if (!nascita.equals(sdf.format(date))) {
                                    date = null;
                                }
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                            if (date == null)
                                return 6;
                        } else {
                            nascita = null;
                        }

                        anUtenti.setNascita(nascita);
                    }

                    anUtenti.setNome(nome);
                    anUtenti.setCognome(cognome);
                    anUtenti.setEmail(email);
                }
            }

            try {
                SerializzaOggetti.serializza(utenti, Constants.UTENTI_PATH);
            } catch (SerializzazioneException e) {
                e.printStackTrace();
            }

            return 0;

	}

	protected int checkPsw(Utente user, String oldPsw,
                         String newPsw, String cNewPsw) {
        if (!oldPsw.equals("")) {
            if (!oldPsw.equals(user.getPassword()))
                return 1;
            else if (!newPsw
                    .equals(cNewPsw))
                return 2;
            else if (newPsw.equals(""))
                return 3;
            else
                user.setPassword(newPsw);
        } else {
            if (!newPsw.equals("") ||
                    !cNewPsw.equals(""))
                return 1;
        }

        return 0;
    }

}