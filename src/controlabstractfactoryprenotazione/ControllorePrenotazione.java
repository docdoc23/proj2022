package controlabstractfactoryprenotazione;

import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.TrasformaDate;

import java.io.IOException;
import java.util.GregorianCalendar;

public class ControllorePrenotazione {

    FactoryPrenotazione fp;

/*
questo metodo a seconda del tipo di locazione istanzia una factory che si occuperà
di creare i corrispondenti controllori
 */

    public boolean controlloPrenotazione(Locazione locazione, String cliente, String dataInizio, String dataFine, String numeroPersone) throws DeserializzazioneException, SerializzazioneException, IOException {

        String nomeLocazione = locazione.getNomeLocazione();
        String proprietario = locazione.getUserLocatore();
        String prezzo = locazione.getPrezzo();
        GregorianCalendar gcInizio = TrasformaDate.trasformaInGregorianCalendar(dataInizio);
        GregorianCalendar gcInizio2 = TrasformaDate.trasformaInGregorianCalendar(dataInizio);
        GregorianCalendar gcFine = TrasformaDate.trasformaInGregorianCalendar(dataFine);

        if(locazione.getClass()== Albergo.class) {
             fp = FactoryPrenotazionePiuPosti.getFactoryPrenotazionePiuPosti();
            if(fp.creaControlloreDisponibilita().controllo(locazione,gcInizio,gcFine,numeroPersone)) {
                fp.creaControlloreRegistraPrenotati().registra(nomeLocazione, proprietario, cliente, gcInizio2, gcFine, "Albergo",prezzo,numeroPersone);
                return true;
            }
        }

        if(locazione.getClass()==Beb.class){
            fp = FactoryPrenotazionePiuPosti.getFactoryPrenotazionePiuPosti();
            if(fp.creaControlloreDisponibilita().controllo(locazione,gcInizio,gcFine,numeroPersone)) {
                fp.creaControlloreRegistraPrenotati().registra(nomeLocazione, proprietario, cliente, gcInizio2, gcFine, "Beb",prezzo,numeroPersone);
                return true;
            }
        }

        if(locazione.getClass()==Ostello.class){
            fp = FactoryPrenotazionePiuPosti.getFactoryPrenotazionePiuPosti();
            if(fp.creaControlloreDisponibilita().controllo(locazione,gcInizio,gcFine,numeroPersone)) {
                fp.creaControlloreRegistraPrenotati().registra(nomeLocazione, proprietario, cliente, gcInizio2, gcFine, "Ostello",prezzo,numeroPersone);
                return true;
            }
        }

        if(locazione.getClass()==Appartamento.class  ){
            fp = FactoryPrenotazioneSingoloPosto.getFactoryPrenotazioneSingoloPosto();
            if(fp.creaControlloreDisponibilita().controllo(locazione,gcInizio,gcFine,numeroPersone)) {
                fp.creaControlloreRegistraPrenotati().registra(nomeLocazione, proprietario, cliente, gcInizio2, gcFine, "Appartamento",prezzo,numeroPersone);
                return true;
            }
        }

        if(locazione.getClass()==CasaVacanza.class){
            fp = FactoryPrenotazioneSingoloPosto.getFactoryPrenotazioneSingoloPosto();
            if(fp.creaControlloreDisponibilita().controllo(locazione,gcInizio,gcFine,numeroPersone)) {
                fp.creaControlloreRegistraPrenotati().registra(nomeLocazione, proprietario, cliente, gcInizio2, gcFine, "CasaVacanza",prezzo,numeroPersone);
                return true;
            }
        }


        return false;
    }
}
