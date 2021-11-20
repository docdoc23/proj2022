package control;

import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AdapterRicercaPerLocazione implements ControlloreRicercaPerLocazione {


    //aggregazione
    private ControlloreRicercaGlobale crg;

    public AdapterRicercaPerLocazione(ControlloreRicercaGlobale c){
        this.crg = c;
    }

    /*
    questa viene utilizzata per adattare le funzionalità implementate nel ControlloreRicercaGlobale
     */

    /*
    questo metodo filtra per tipo di locazione le locazione restituite dal metodo "ricercaGlobale"
     */
    @Override@SuppressWarnings("unchecked")
    public Object ricerca(String tipoLocazione, String provincia, String prezzo,int numeroGiorni) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, DeserializzazioneException, IOException, SerializzazioneException, InterruptedException {

        List<Locazione> elencoLocazioni;
        elencoLocazioni =  crg.ricercaGlobale(provincia,prezzo,numeroGiorni);

        switch (tipoLocazione) {
            case "Albergo":
                ArrayList<Albergo> alberghiDisponibili = new ArrayList<>(); //Qui verranno inseriti gli alberghi da restituire all'getUtente


                for (Locazione loc : elencoLocazioni) {
                    if (loc.getClass() == Albergo.class) {
                        Albergo albergo = (Albergo) loc;
                        alberghiDisponibili.add(albergo);
                    }
                }
                return alberghiDisponibili;
            case "Appartamento":
                ArrayList<Appartamento> appartamentiDisponibili = new ArrayList<>(); //Qui verranno inseriti gli appartamenti da restituire all'getUtente

                for (Locazione loc : elencoLocazioni) {
                    if (loc.getClass() == Appartamento.class) {
                        Appartamento appartamento = (Appartamento) loc;
                        appartamentiDisponibili.add(appartamento);
                    }
                }
                return appartamentiDisponibili;

            case "Beb":
            	ArrayList<Beb> bebDisponibili = new ArrayList<>(); //Qui verranno inseriti i beb da restituire all'getUtente


                for (Locazione loc : elencoLocazioni) {
                    if (loc.getClass() == Beb.class) {
                        Beb beb = (Beb) loc;
                        bebDisponibili.add(beb);
                    }
                }
                return bebDisponibili;
            case "CasaVacanza":
            	ArrayList<CasaVacanza> casaVacanzeDisponibili = new ArrayList<>(); //Qui verranno inseriti gli appartamenti da restituire all'getUtente


                for (Locazione loc : elencoLocazioni) {
                    if (loc.getClass() == CasaVacanza.class) {
                        CasaVacanza casaVacanza = (CasaVacanza) loc;
                        casaVacanzeDisponibili.add(casaVacanza);
                    }
                }
                return casaVacanzeDisponibili;
            case "Ostello":
            	ArrayList<Ostello> ostelliDisponibili = new ArrayList<>(); //Qui verranno inseriti gli ostelli da restituire all'getUtente


                for (Locazione loc : elencoLocazioni) {
                    if (loc.getClass() == Ostello.class) {
                        Ostello ostello = (Ostello) loc;
                        ostelliDisponibili.add(ostello);
                    }
                }
                return ostelliDisponibili;
            default:
                return new ArrayList<Locazione>();
         }

    }
    /*
   questo metodo filtra in base a determinate caratteristiche le locazioni restituite del metodo "ricerca"
    */
    @Override@SuppressWarnings("unchecked")
    public Object ricercaAvanzata(String tipoLocazione,String provincia, String prezzo,int numeroGiorni, String sParchegio, String sWifi, String sPet ,String caratteristica) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, DeserializzazioneException, IOException, SerializzazioneException, InterruptedException {

        Boolean parcheggio=false; 
        Boolean wifi =false;
        Boolean pet=false;

        if (sParchegio.equals("true")) {
            parcheggio = true;
        }
        if (sWifi.equals("true")) {
            wifi = true;
        }
        if (sPet.equals("true")) {
            pet = true;
        }

        switch (tipoLocazione) {
            case "Albergo":
            	ArrayList<Albergo> elencoAlberghi = (ArrayList<Albergo>) ricerca("Albergo", provincia, prezzo, numeroGiorni);
            	ArrayList<Albergo> alberghiDisponibili = new ArrayList<>(); //Qui verranno inseriti gli alberghi da restituire all'getUtente

                for (Albergo albergo : elencoAlberghi) {
                    if ((albergo.isParcheggio() == parcheggio) &&
                            (albergo.isWifi() == wifi) &&
                            (albergo.isPet() == pet) &&
                            (albergo.getTipoPensione().equals(caratteristica))) {
                        alberghiDisponibili.add(albergo);
                    }
                }
                return alberghiDisponibili;

            case "Appartamento":
            	ArrayList<Appartamento> elencoAppartamenti = (ArrayList<Appartamento>) ricerca("Appartamento", provincia, prezzo, numeroGiorni);
            	ArrayList<Appartamento> appartamentiDisponibili = new ArrayList<>(); //Qui verranno inseriti gli appartamenti da restituire all'getUtente

                for (Appartamento appartamento : elencoAppartamenti) {
                    if ((appartamento.isParcheggio() == parcheggio) &&
                            (appartamento.isWifi() == wifi) &&
                            (appartamento.isPet() == pet) &&
                            (Integer.parseInt(appartamento.getNumeroStanze()) >= Integer.parseInt(caratteristica.trim()))) {
                        appartamentiDisponibili.add(appartamento);
                    }
                }
                return appartamentiDisponibili;

            case "Beb":
            	ArrayList<Beb> elencoBeb = (ArrayList<Beb>) ricerca("Beb", provincia, prezzo, numeroGiorni);
            	ArrayList<Beb> bebDisponibili = new ArrayList<>(); //Qui verranno inseriti i beb da restituire all'getUtente


                for (Beb beb : elencoBeb) {
                    if ((beb.isParcheggio() == parcheggio) &&
                            (beb.isWifi() == wifi) &&
                            (beb.isPet() == pet)) {
                        bebDisponibili.add(beb);
                    }
                }
                return bebDisponibili;
            case "CasaVacanza":
            	ArrayList<CasaVacanza> elencoCasaVacanze = (ArrayList<CasaVacanza>) ricerca("CasaVacanza", provincia, prezzo, numeroGiorni);
            	ArrayList<CasaVacanza> casaVacanzeDisponibili = new ArrayList<>(); //Qui verranno inseriti gli appartamenti da restituire all'getUtente

                for (CasaVacanza casa : elencoCasaVacanze) {
                    if ((casa.isParcheggio() == parcheggio) &&
                            (casa.isWifi() == wifi) &&
                            (casa.isPet() == pet) &&
                            (Integer.parseInt(casa.getNumeroCamere()) >= Integer.parseInt(caratteristica.trim()))) {

                        casaVacanzeDisponibili.add(casa);
                    }
                }
                return casaVacanzeDisponibili;

            case "Ostello":

            	ArrayList<Ostello> elencoOstelli = (ArrayList<Ostello>) ricerca("Ostello", provincia, prezzo, numeroGiorni);
            	ArrayList<Ostello> ostelliDisponibili = new ArrayList<>(); //Qui verranno inseriti gli ostelli da restituire all'Utente

                for (Ostello ostello : elencoOstelli) {
                    if ((ostello.isParcheggio() == parcheggio) &&
                            (ostello.isWifi() == wifi) &&
                            (ostello.isPet() == pet) &&
                            ostello.getTipoPensione().equals(caratteristica)) {
                        ostelliDisponibili.add(ostello);
                    }
                }

                return ostelliDisponibili;
            default:
                return new ArrayList<Locazione>();
        }
    }


}
