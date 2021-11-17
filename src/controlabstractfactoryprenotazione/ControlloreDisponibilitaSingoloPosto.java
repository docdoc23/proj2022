package controlabstractfactoryprenotazione;

import constants.Constants;
import entity.Appartamento;
import entity.CasaVacanza;
import entity.Locazione;
import entity.PostiDisponibili;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.CreaArrayDate;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class ControlloreDisponibilitaSingoloPosto implements ControlloreDisponibilita{

    private static String percorsoPrenotazioniAppartamenti = Constants.PRENOTAZIONE_APPARTAMENTO_PATH;
    private static String percorsoPrenotazioniCaseVacanza = Constants.PRENOTAZIONE_CASAVACANZA_PATH;


    @Override@SuppressWarnings("unchecked")
    public boolean controllo(Locazione loc, GregorianCalendar gcInizio, GregorianCalendar gcFine, String numeroPersone) throws DeserializzazioneException, SerializzazioneException, IOException {

        String percorso = "";
        if(loc.getClass()== Appartamento.class)
            percorso=percorsoPrenotazioniAppartamenti;
        if(loc.getClass()== CasaVacanza.class)
            percorso=percorsoPrenotazioniCaseVacanza;

        List<GregorianCalendar> datePrenotazione= CreaArrayDate.restituisciArrayDate(gcInizio, gcFine);
        ArrayList<PostiDisponibili> prenotateList = new ArrayList<>();
        ArrayList<PostiDisponibili> temp = new ArrayList<>();

        File file = new File(percorso);

        SerializzaOggetti sobj = new SerializzaOggetti();
        if (file.length() == 0) {
            for (GregorianCalendar data_prenotazione : datePrenotazione) {
                PostiDisponibili cp = new PostiDisponibili(loc.getNomeLocazione(), data_prenotazione);
                prenotateList.add(cp);

            }
            sobj.serializza(prenotateList, percorso);
            return true;
        } else {
            DeserializzaOggetti dobj = new DeserializzaOggetti();
            prenotateList = (ArrayList<PostiDisponibili>) dobj.deserializza(percorso);
            for (GregorianCalendar data_prenotazione : datePrenotazione) {
                for (PostiDisponibili camera_prenotata : prenotateList) {
                    if (camera_prenotata.getNomeLocazion().equals(loc.getNomeLocazione()) && data_prenotazione.equals(camera_prenotata.getData()))
                        return false;
                }
                PostiDisponibili nuovaData = new PostiDisponibili(loc.getNomeLocazione(), data_prenotazione);
                temp.add(nuovaData);
            }
            prenotateList.addAll(temp);
            sobj.serializza(prenotateList, percorso);
            return true;

        }
    }
}
