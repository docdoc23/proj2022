package control;


import constants.Constants;
import entity.Locazione;
import entity.Prenotazione;
import entity.Recensione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ControlloreRecensione {

    private String percorsoRecensioni = Constants.RECENSIONI_PATH;

    /*
    metodo utilizzato per inserire una nuova recensione, prima di farlo vengono controllati i possibili errrori
    e restituiti degli interi ad indicare l'errore:
    1) per indicare che non è stato inserito il nome della locazione
    2) per indicare che la locazioni che si vuole recensire è presente nel sistema
    3) per indicare che prima di recensire una locazione è neccessaria almeno una prenotazione per quella locazione
    4) per indicare che il testo della recensione deve essere composto da almeno 10 caratteri
    5) per indicare che il testo della recensione non può essere composto da più di 300 caratteri
    se non si verifica nessuno degli errori precedenti viene restituito 0
     */

    public int  inserisci(String nomeLocazione,String tipoLocazione, String nomeRecensore, int numeroStelle, String testoRecensione) throws DeserializzazioneException, SerializzazioneException {
        if(nomeLocazione.equals(""))
            return 1;
        if(!controlloEsistenzaLocazione(nomeLocazione,tipoLocazione))
            return 2;
        if(!controlloEsistenzaPrenotazione(nomeLocazione,tipoLocazione,nomeRecensore))
            return 3;
        if(testoRecensione.length()<10)
            return 4;
        if(testoRecensione.length()>300)
            return 5;

        ArrayList<Recensione> recensioni = new ArrayList<>();
        Recensione recensione = new Recensione(nomeLocazione, nomeRecensore, numeroStelle, testoRecensione);
        File file = new File(percorsoRecensioni);
        if(file.length()==0) {
            recensioni.add(recensione);
            SerializzaOggetti.serializza(recensioni,percorsoRecensioni);
        }else {
            recensioni = (ArrayList<Recensione>) DeserializzaOggetti.deserializza(percorsoRecensioni);
            recensioni.add(recensione);
            SerializzaOggetti.serializza(recensioni,percorsoRecensioni);
        }

        return 0;

    }

    private boolean controlloEsistenzaLocazione(String nomeLocazione,String tipoLocazione) throws DeserializzazioneException {

        String percorsoLocazioni="";

        switch (tipoLocazione) {
            case "Albergo":
                percorsoLocazioni = Constants.ALBERGHI_PATH;
                break;
            case "Appartamento":
                percorsoLocazioni = Constants.APPART_PATH;
                break;
            case "Beb":
                percorsoLocazioni = Constants.BEB;
                break;
            case "CasaVacanza":
                percorsoLocazioni = Constants.CASEVACANZA_PATH;
                break;
            case "Ostello":
                percorsoLocazioni = Constants.OSTELLI_PATH;
                break;
            default :
            	break;
        }

        File file = new File(percorsoLocazioni);
        if(file.length()!=0) {

            ArrayList<Locazione> locazioni = (ArrayList<Locazione>) DeserializzaOggetti.deserializza(percorsoLocazioni);
            for (Locazione loc : locazioni) {
                if (loc.getNomeLocazione().equalsIgnoreCase(nomeLocazione))
                    return true;
            }
        }
            return false;

    }

    private boolean controlloEsistenzaPrenotazione(String nomeLocazione,String tipoLocazione, String nomeRecensore) throws DeserializzazioneException {

        String percorsoPrenotazioni="";

        switch (tipoLocazione) {
            case "Albergo":
                percorsoPrenotazioni = Constants.PRENOTATI_ALBERGO_PATH;
                break;
            case "Appartamento":
                percorsoPrenotazioni = Constants.PRENOTATI_APPARTAMENTO_PATH;
                break;
            case "Beb":
                percorsoPrenotazioni = Constants.PRENOTATI_BEB_PATH;
                break;
            case "CasaVacanza":
                percorsoPrenotazioni = Constants.PRENOTATI_CASEVACANZA_PATH;
                break;
            case "Ostello":
                percorsoPrenotazioni = Constants.PRENOTATI_OSTELLO_PATH;
                break;
            default :
            	break;
        }

        File file = new File(percorsoPrenotazioni);
        if(file.length()!=0) {

            ArrayList<Prenotazione> prenotazioni = (ArrayList<Prenotazione>) DeserializzaOggetti.deserializza(percorsoPrenotazioni);

            for (Prenotazione pre : prenotazioni) {
                if (pre.getNomeLocazione().equals(nomeLocazione) &&
                        pre.getCliente().equals(nomeRecensore))
                    return true;
            }
        }

        return false;
    }

    /*
    metodo utilizzato per restuire le recensioni in base al nome della locazione
     */

    public List<Recensione> ritornaRecensioni(String nomeLocazione) throws DeserializzazioneException {


        File file = new File(percorsoRecensioni);

        if(file.length()!=0){

            ArrayList<Recensione> recensioniPerLaLocazione= new ArrayList<>();

            ArrayList<Recensione> recensioniSalvate = (ArrayList<Recensione>) DeserializzaOggetti.deserializza(percorsoRecensioni);
            for(Recensione rec:  recensioniSalvate){
                if(rec.getNomeLocazione().equals(nomeLocazione)){

                    recensioniPerLaLocazione.add(rec);
                }
            }
            return recensioniPerLaLocazione;
        }
        else return new ArrayList<>();
    }

    /*
     metodo utilizzato per calcolare la media dei voti per una locazione, nel caso non esistano recensioni
     per la locazione considerata allora viene ritornato il valore 6 che è un valore non presente tra le
     possibili soluzioni(dato che i voti possibili variano da zero a sei
     */
    public int calcolaMedia(String nomeLocazione) throws DeserializzazioneException {
    	List<Recensione>recensioni = ritornaRecensioni(nomeLocazione);
        int numeroRecensioni = recensioni.size();

        if(numeroRecensioni==0)
            return 6;

        int sommaTotale=0;
        for(Recensione recensione: recensioni){
            sommaTotale += recensione.getNumeroStelle();
        }


        return sommaTotale/numeroRecensioni;

    }

}
