package controlabstractfactoryprenotazione;

import constants.Constants;
import entity.Prenotazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ControlloreRegistraPrenotazioniPiuPosti implements ControlloreRegistraPrenotazione {

    private static String percorsoPrenotatiAlbergo = Constants.PRENOTATI_ALBERGO_PATH;
    private static String percorsoPrenotatiBeb = Constants.PRENOTATI_BEB_PATH;
    private static String percorsoPrenotatiOstello = Constants.PRENOTATI_OSTELLO_PATH;

    @Override@SuppressWarnings("unchecked")
    public void registra(String nomeLocazione, String proprietario, String cliente, GregorianCalendar dataInizio, GregorianCalendar dataFine, String tipo, String prezzo, String numeroPersone) throws SerializzazioneException, DeserializzazioneException {
        ArrayList<Prenotazione> listaPrenotazione =  new ArrayList<>();
        SerializzaOggetti sobj  = new SerializzaOggetti();

        String percorso = "";
        if(tipo.equals("Albergo"))
            percorso=percorsoPrenotatiAlbergo;
        if(tipo.equals("Beb"))
            percorso=percorsoPrenotatiBeb;
        if(tipo.equals("Ostello"))
            percorso=percorsoPrenotatiOstello;

        File file =  new File(percorso);
        if(file.length()==0){
            Prenotazione clientePrenotazione = new Prenotazione(nomeLocazione,proprietario,cliente,dataInizio,dataFine,tipo,prezzo,numeroPersone);
            sobj.serializza(listaPrenotazione,percorso);
            listaPrenotazione.add(clientePrenotazione);
            

        }else{

            DeserializzaOggetti dobj = new DeserializzaOggetti();
            int precedenteRegistrazione=0;
            listaPrenotazione = (ArrayList<Prenotazione>) dobj.deserializza(percorso);
            

            for(Prenotazione prenotazione : listaPrenotazione){
                if(prenotazione.getNomeLocazione().equals(nomeLocazione)&& prenotazione.getCliente().equals(cliente)&&
                        prenotazione.getDataInizio().equals(dataInizio)&& prenotazione.getDataFine().equals(dataFine)){
                    precedenteRegistrazione+=1;
                }
            }
            if(precedenteRegistrazione==0) {
                Prenotazione clientePrenotazione = new Prenotazione(nomeLocazione, proprietario, cliente, dataInizio, dataFine,tipo,prezzo,numeroPersone);
                
                sobj.serializza(listaPrenotazione, percorso);
                clientePrenotazione.setTipo(tipo);
                listaPrenotazione.add(clientePrenotazione);
            }
        }
    }


}
