package standalonecontrol;

import constants.Constants;
import controlcontrolloregestionelocazione.ControlloreRimuoviLocazione;
import controlcontrolloregestionelocazione.ControlloreVisualizzaLocazioni;
import entity.Locazione;
import entity.Prenotazione;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standaloneboundary.BoundaryFallimento;
import standaloneboundary.BoundarySuccesso;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControlloreRimuoviDatiUtente {

    private static String percorsoLocazioniRimosse = Constants.RIMOSSE_PATH;
    public String percorsoUtenti = Constants.UTENTI_PATH;
    private static String percorsoPrenotazioniAlberghi = Constants.PRENOTATI_ALBERGO_PATH;
    private static String percorsoPrenotazioniBeb = Constants.PRENOTATI_BEB_PATH;
    private static String percorsoPrenotazioniOstelli = Constants.PRENOTATI_OSTELLO_PATH;
    private static String percorsoPrenotazioniAppartamenti = Constants.PRENOTATI_APPARTAMENTO_PATH;
    private static String percorsoPrenotazioniCaseVacanza = Constants.PRENOTATI_CASEVACANZA_PATH;

    private ControlloreLinguaAmministratore cl;

    public ControlloreRimuoviDatiUtente() {
        this.cl = new ControlloreLinguaAmministratore();
    }

    @SuppressWarnings("unchecked")
    public void rimuovi(String username) throws DeserializzazioneException, SerializzazioneException {

        DeserializzaOggetti du = new DeserializzaOggetti();
        ArrayList<Utente> utenti = (ArrayList<Utente>)du.deserializza(percorsoUtenti);

        for(Utente utente:utenti){
            if(utente.getUsername().equals(username)){
                ControlloreVisualizzaLocazioni cvl= new ControlloreVisualizzaLocazioni();
                List<Locazione> locazioniUtenteNonRimosso=  cvl.visualizzaLocazioni(username);
                System.out.println("username utente tra quelli non rimossi "+ username+" la prima delle sue locazioni "+
                            locazioniUtenteNonRimosso.get(0).getNomeLocazione());

                for(int i =0; i<locazioniUtenteNonRimosso.size(); ++i) {
                    ControlloreRimuoviLocazione crl = new ControlloreRimuoviLocazione();
                    crl.rimuovi(locazioniUtenteNonRimosso.get(i));
                }
                 rimuoviPrenotazioni(username);
                 new BoundarySuccesso();
                return;
            }
        }
        rimuoviPerUtenteRimosso(username);

    }
    @SuppressWarnings("unchecked")
    private void rimuoviPerUtenteRimosso(String username) throws DeserializzazioneException,SerializzazioneException{
        ResourceBundle bundle = this.cl.getBundleFromProp();

        DeserializzaOggetti dobj = new DeserializzaOggetti();
        ArrayList<Locazione> locazioniUtenteRimosso = (ArrayList<Locazione>) dobj.deserializza(percorsoLocazioniRimosse);

        for(int i=0; i<locazioniUtenteRimosso.size();++i) {
            if (locazioniUtenteRimosso.get(i).getUserLocatore().equals(username)) {
                System.out.println("username utente tra quelli  rimossi "+ username+" la prima delle sue locazioni "+ locazioniUtenteRimosso.get(0).getNomeLocazione());
                ControlloreRimuoviLocazione crl = new ControlloreRimuoviLocazione();
                crl.rimuovi(locazioniUtenteRimosso.get(i));
                rimuoviPrenotazioni(username);
                locazioniUtenteRimosso.remove(i);
                new BoundarySuccesso();
                return;
            }
        }
        SerializzaOggetti sobj = new SerializzaOggetti();
        sobj.serializza(locazioniUtenteRimosso,percorsoLocazioniRimosse);
        new BoundaryFallimento(bundle.getString("boundaryRimuoviDati_user_errato"));
    }
    @SuppressWarnings("unchecked")
    private void rimuoviPrenotazioni(String username) throws SerializzazioneException, DeserializzazioneException {
        File fileAlb = new File(percorsoPrenotazioniAlberghi);
        File fileApp = new File(percorsoPrenotazioniAppartamenti);
        File fileBeb = new File(percorsoPrenotazioniBeb);
        File fileCasa = new File(percorsoPrenotazioniCaseVacanza);
        File fileOst = new File(percorsoPrenotazioniOstelli);

        if(fileAlb.length()!=0){
            DeserializzaOggetti dAlb = new DeserializzaOggetti();
            ArrayList<Prenotazione> clienteAlbergo = (ArrayList<Prenotazione>) dAlb.deserializza(percorsoPrenotazioniAlberghi);
            for (int i = 0; i < clienteAlbergo.size(); ++i) {
                if (clienteAlbergo.get(i).getCliente().equals(username)) {
                    clienteAlbergo.remove(i);
                }
            }
            SerializzaOggetti sAlb = new SerializzaOggetti();
            sAlb.serializza(clienteAlbergo, percorsoPrenotazioniAlberghi);
        }

        if(fileApp.length()!=0){
            DeserializzaOggetti dApp = new DeserializzaOggetti();
            ArrayList<Prenotazione> clienteAppartamento = (ArrayList<Prenotazione>) dApp.deserializza(percorsoPrenotazioniAppartamenti);
            for (int i = 0; i < clienteAppartamento.size(); ++i) {
                if (clienteAppartamento.get(i).getCliente().equals(username)) {
                    clienteAppartamento.remove(i);
                }
            }

            SerializzaOggetti sApp = new SerializzaOggetti();
            sApp.serializza(clienteAppartamento, percorsoPrenotazioniAppartamenti);
        }

        if(fileBeb.length()!=0){
            DeserializzaOggetti dBeb = new DeserializzaOggetti();
            ArrayList<Prenotazione> clienteBeb = (ArrayList<Prenotazione>) dBeb.deserializza(percorsoPrenotazioniBeb);
            for (int i = 0; i < clienteBeb.size(); ++i) {
                if (clienteBeb.get(i).getCliente().equals(username)) {
                    clienteBeb.remove(i);
                }
            }
            SerializzaOggetti sBeb = new SerializzaOggetti();
            sBeb.serializza(clienteBeb, percorsoPrenotazioniBeb);

        }

        if(fileCasa.length()!=0){
            DeserializzaOggetti dCasa = new DeserializzaOggetti();
            ArrayList<Prenotazione> clienteCasaVacanza = (ArrayList<Prenotazione>) dCasa.deserializza(percorsoPrenotazioniCaseVacanza);
            for (int i = 0; i < clienteCasaVacanza.size(); ++i) {
                if (clienteCasaVacanza.get(i).getCliente().equals(username)) {
                    clienteCasaVacanza.remove(i);
                }
            }
            SerializzaOggetti sCasa = new SerializzaOggetti();
            sCasa.serializza(clienteCasaVacanza, percorsoPrenotazioniCaseVacanza);

        }

        if(fileOst.length()!=0) {
            DeserializzaOggetti dOst = new DeserializzaOggetti();
            ArrayList<Prenotazione> clienteOstello = (ArrayList<Prenotazione>) dOst.deserializza(percorsoPrenotazioniOstelli);
            for (int i = 0; i < clienteOstello.size(); ++i) {
                if (clienteOstello.get(i).getCliente().equals(username)) {
                    clienteOstello.remove(i);
                }
            }

            SerializzaOggetti sOst = new SerializzaOggetti();
            sOst.serializza(clienteOstello, percorsoPrenotazioniOstelli);
        }

    }
}
