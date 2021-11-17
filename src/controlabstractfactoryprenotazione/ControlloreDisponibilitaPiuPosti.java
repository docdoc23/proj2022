package controlabstractfactoryprenotazione;

import constants.Constants;
import entity.*;
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


public class ControlloreDisponibilitaPiuPosti implements ControlloreDisponibilita{

    private static String percorsoPrenotazioniAlberghi = Constants.PRENOTAZIONE_ALBERGO_PATH;
    private static String percorsoPrenotazioniBeb = Constants.PRENOTAZIONE_BEB_PATH;
    private static String percorsoPrenotazioniOstelli = Constants.PRENOTAZIONE_OSTELLO_PATH;

    @Override
    public boolean controllo(Locazione loc, GregorianCalendar gcInizio, GregorianCalendar gcFine, String numeroPersone) throws DeserializzazioneException, SerializzazioneException, IOException {

        String percorso="";
        if(loc.getClass()== Albergo.class)
            percorso=percorsoPrenotazioniAlberghi;
        if(loc.getClass()== Beb.class)
            percorso=percorsoPrenotazioniBeb;
        if(loc.getClass()== Ostello.class)
            percorso=percorsoPrenotazioniOstelli;

        Integer totali = Integer.parseInt(loc.getPostiTotali().trim());
        Integer postiRichiesti = Integer.parseInt(numeroPersone.trim());
        List<GregorianCalendar> datePrenotazione= CreaArrayDate.restituisciArrayDate(gcInizio, gcFine);

        ArrayList<PostiDisponibili> prenotateList = new ArrayList<>();

        ArrayList<PostiDisponibili> temp = new ArrayList<>();
        //PostiDisponibili

        File file = new File(percorso);

        SerializzaOggetti sobj = new SerializzaOggetti();
        if (file.length() == 0) {
            for (GregorianCalendar data_prenotazione : datePrenotazione) {
                PostiDisponibili cp = new PostiDisponibili(loc.getNomeLocazione(), data_prenotazione);
                int contatoreaggiornato = cp.getContatore() + postiRichiesti ;
                cp.setContatore(contatoreaggiornato);
                if(totali<cp.getContatore())
                    return false;
                else {
                    prenotateList.add(cp);
                }

            }
            sobj.serializza(prenotateList, percorso);
            return true;
        } else {

            DeserializzaOggetti dobj = new DeserializzaOggetti();
            prenotateList = (ArrayList<PostiDisponibili>) dobj.deserializza(percorso);
            for (GregorianCalendar data_prenotazione : datePrenotazione) {

                boolean condizione = false;
                for (PostiDisponibili camera_prenotata : prenotateList) {
                    if (camera_prenotata.getNomeLocazion().equals(loc.getNomeLocazione()) && data_prenotazione.equals(camera_prenotata.getData())) {
                        if (totali <= camera_prenotata.getContatore())
                            return false;
                        if ((totali != camera_prenotata.getContatore())) {
                            int contatoreaggiornato = camera_prenotata.getContatore() + postiRichiesti;
                            camera_prenotata.setContatore(contatoreaggiornato);
                            condizione = true;
                            break;
                        }
                    }
                }
                if (condizione)
                    continue;
                PostiDisponibili nuovaData = new PostiDisponibili(loc.getNomeLocazione(), data_prenotazione);
                int contatoreaggiornato = nuovaData.getContatore() + postiRichiesti;
                nuovaData.setContatore(contatoreaggiornato);
                if(totali<nuovaData.getContatore()){
                    return false;
                }else {
                    temp.add(nuovaData);
                }
            }
            prenotateList.addAll(temp);
            sobj.serializza(prenotateList, percorso);
            return true;

        }
    }
}
