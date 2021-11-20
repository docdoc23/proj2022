package control;

import constants.Constants;
import entity.PostiDisponibili;
import entity.Prenotazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.CreaArrayDate;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ControlloreRimuoviPrenotazione {

    private static String percorsoPrenotatiAlbergo = Constants.PRENOTATI_ALBERGO_PATH;
    private static String percorsoPrenotatiAppartamento = Constants.PRENOTATI_APPARTAMENTO_PATH;
    private static String percorsoPrenotatiBeb = Constants.PRENOTATI_BEB_PATH;
    private static String percorsoPrenotatiCasaVacanza = Constants.PRENOTATI_CASEVACANZA_PATH;
    private static String percorsoPrenotatiOstello = Constants.PRENOTATI_OSTELLO_PATH;

    private static String percorsoPrenotazioniAlberghi = Constants.PRENOTAZIONE_ALBERGO_PATH;
    private static String percorsoPrenotazioniBeb = Constants.PRENOTAZIONE_BEB_PATH;
    private static String percorsoPrenotazioniOstelli = Constants.PRENOTAZIONE_OSTELLO_PATH;
    private static String percorsoPrenotazioniAppartamenti = Constants.PRENOTAZIONE_APPARTAMENTO_PATH;
    private static String percorsoPrenotazioniCaseVacanza = Constants.PRENOTAZIONE_CASAVACANZA_PATH;


    /*
     metodo che rimuove una prenotazione, per farlo oltre ad eliminare un oggetto prenotazione deve
     eliminare anche i posti occupati relativi alle date per le quali l'utente si era precedentente prenotato
     per questo motivo se la prenotazione è relativa ad una locazione con più posti al giorno(come un albergo)
     viene sfruttato il metodo privato "rimuoviPostiOccupati" mentre se la prenotazione è relativa ad un locazione
     come una casa vacanza che viene considerata come una locazione con un singolo posto quindi viene invato il
     metodo privato "rimuoviSingoloPosto"
     */
    public void rimuovi(Prenotazione prenotazione) throws DeserializzazioneException, SerializzazioneException, IOException {

        switch (prenotazione.getTipo()) {
            case "Albergo":
                rimuoviPrenotazione(prenotazione, percorsoPrenotatiAlbergo);
                rimuoviPostiOccupati(prenotazione, percorsoPrenotazioniAlberghi);

                break;
            case "Appartamento":
                rimuoviPrenotazione(prenotazione, percorsoPrenotatiAppartamento);
                rimuoviSingoloPosto(prenotazione, percorsoPrenotazioniAppartamenti);

                break;
            case "Beb":
                rimuoviPrenotazione(prenotazione, percorsoPrenotatiBeb);
                rimuoviPostiOccupati(prenotazione, percorsoPrenotazioniBeb);

                break;
            case "CasaVacanza":
                rimuoviPrenotazione(prenotazione, percorsoPrenotatiCasaVacanza);
                rimuoviSingoloPosto(prenotazione, percorsoPrenotazioniCaseVacanza);


                break;
            case "Ostello":
                rimuoviPrenotazione(prenotazione, percorsoPrenotatiOstello);
                rimuoviPostiOccupati(prenotazione, percorsoPrenotazioniOstelli);
                break;
                
            default :
            	break;
        }



    }


    private void rimuoviPrenotazione(Prenotazione prenotazione, String percorsoPrenotati) throws SerializzazioneException, DeserializzazioneException {
        String nomeLocazione = prenotazione.getNomeLocazione();
        String cliente = prenotazione.getCliente();
        GregorianCalendar dataInizio = prenotazione.getDataInizio();
        GregorianCalendar dataFine = prenotazione.getDataFine();
        ArrayList<Prenotazione> prenotazioniClienti;

        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();

        prenotazioniClienti=(ArrayList<Prenotazione>) dobj.deserializza(percorsoPrenotati);

        for(int i=0; i<prenotazioniClienti.size();++i){
            if(prenotazioniClienti.get(i).getNomeLocazione().equals(nomeLocazione)&&
                    prenotazioniClienti.get(i).getCliente().equals(cliente)&&
                    prenotazioniClienti.get(i).getDataInizio().equals(dataInizio)&&
                    prenotazioniClienti.get(i).getDataFine().equals(dataFine)) {
                prenotazioniClienti.remove(i);
            }

        }

        sobj.serializza(prenotazioniClienti,percorsoPrenotati);

    }

    @SuppressWarnings("unchecked")
    private void rimuoviPostiOccupati(Prenotazione prenotazione, String percorsoCamere) throws DeserializzazioneException, SerializzazioneException, IOException {
        String nomeLocazione = prenotazione.getNomeLocazione();
        GregorianCalendar dataInizio = prenotazione.getDataInizio();
        GregorianCalendar dataFine = prenotazione.getDataFine();
        ArrayList<PostiDisponibili> postiDisponibili;
        Integer postiOccupati = Integer.parseInt(prenotazione.getNumeroPersone().trim());

        List<GregorianCalendar> datePrenotazione;
        datePrenotazione = CreaArrayDate.restituisciArrayDate(dataInizio, dataFine);

        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();

        postiDisponibili = (ArrayList<PostiDisponibili>) dobj.deserializza(percorsoCamere);
        for( GregorianCalendar data: datePrenotazione){
            for (int i = 0; i< postiDisponibili.size(); ++i){
                if(postiDisponibili.get(i).getNomeLocazion().equals(nomeLocazione) && postiDisponibili.get(i).getData().equals(data)){
                     //se  è presente in postiDisponibili il contatore è almeno 1--> ha senso decrementarlo
                    int contatoreaggiornato=(postiDisponibili.get(i).getContatore()-postiOccupati);
                    if(contatoreaggiornato == 0) {
                        postiDisponibili.remove(i);
                    }
                    else
                        {
                            postiDisponibili.get(i).setContatore(contatoreaggiornato);}

                    break;
                }

            }
        }
        sobj.serializza(postiDisponibili,percorsoCamere);
    }

    @SuppressWarnings("unchecked")
    private void rimuoviSingoloPosto(Prenotazione prenotazione, String percorsoCamere) throws DeserializzazioneException, SerializzazioneException, IOException {
        String nomeLocazione = prenotazione.getNomeLocazione();
        GregorianCalendar dataInizio = prenotazione.getDataInizio();
        GregorianCalendar dataFine = prenotazione.getDataFine();
        ArrayList<PostiDisponibili> postiDisponibili;

        List<GregorianCalendar> datePrenotazione;

        datePrenotazione = CreaArrayDate.restituisciArrayDate(dataInizio, dataFine);

        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();


        postiDisponibili = (ArrayList<PostiDisponibili>) dobj.deserializza(percorsoCamere);
        for( GregorianCalendar data: datePrenotazione){
            for (int i = 0; i< postiDisponibili.size(); ++i){
                if(postiDisponibili.get(i).getNomeLocazion().equals(nomeLocazione) && postiDisponibili.get(i).getData().equals(data)){
                        postiDisponibili.remove(i);
                }

            }
        }
        sobj.serializza(postiDisponibili,percorsoCamere);
    }







}


