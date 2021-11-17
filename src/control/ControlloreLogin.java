package control;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.io.File;
import java.util.ArrayList;


public class ControlloreLogin {

	private String username;
	private String password;
	private boolean loggedIn;


	public ControlloreLogin() {

		this.username = "";
		this.password = "";
		this.loggedIn = false;
	}


	/**
	 * Se @username e @password sono presenti nel sistema, l'utente associato ad @username viene
     * autenticato e può accedere ai servizi offerti dal sistema
	 * @param username utente che vuole usufruire di servizi del sistema
	 * @param password codice personale dell'utente che gli permette l'accesso al sistema
	 */
	public void login(String username, String password)
			throws DeserializzazioneException {


            File file = new File(Constants.UTENTI_PATH);
            ArrayList<Utente> utenti = (ArrayList<Utente>) DeserializzaOggetti
                    .deserializza(Constants.UTENTI_PATH);

            if (file.length() == 0)
                return;

            for (Utente anUtenti : utenti) {
                if (anUtenti.getUsername().equals(username)) {
                    if (anUtenti.getPassword().equals(password)) {
                        loggedIn = true;
                        this.username = username;
                        this.password = password;
                    }

                    break;
                }
            }


    }

    /**
     * Ritorna lo stato di autenticazione dell'utente
     * @return stato di autenticazione corrente
     */
	public boolean getLogged(){
		return this.loggedIn;
	}

    /**
     * L'utente vine deautenticato dal sistema
     */
	public void logout(){
		this.username = "";
		this.password = "";
		this.loggedIn = false;
	}

    /**
     * Restituisce l'username dell'utente associato all'istanza corrente
     * @return usernmae dell'utente corrente
     */
	public String getUser(){
		return this.username;
	}

    /**
     * Restituisce la passowrd dell'utente associato all'istanza corrente
     * @return password dell'utente corrente
     */
	public String getPsw() {
        return this.password;
    }
}