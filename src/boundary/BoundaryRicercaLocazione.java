package boundary;

import control.AdapterRicercaPerLocazione;
import control.ControlloreRicercaGlobale;
import control.ControlloreRicercaPerLocazione;
import entity.Locazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.CreaArrayDate;
import utils.TrasformaDate;
import utils.VerificaInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class BoundaryRicercaLocazione {

    private ControlloreRicercaGlobale globale;
    private ControlloreRicercaPerLocazione ctrl;

    public BoundaryRicercaLocazione(){
        globale = new ControlloreRicercaGlobale();
        ctrl = new AdapterRicercaPerLocazione(globale);
    }


    @SuppressWarnings("unchecked")
    public Object  ricerca(int commandInt, int commandavAnzataInt,String provincia,String prezzo,
                                         String parcheggio,String wifi, String pet, String caratteristica,String dataInizio,String dataFine) throws DeserializzazioneException, ClassNotFoundException, IOException, InstantiationException, SerializzazioneException, IllegalAccessException, InterruptedException {

        GregorianCalendar gcInizio = TrasformaDate.trasformaInGregorianCalendar(dataInizio);
        GregorianCalendar gcFine = TrasformaDate.trasformaInGregorianCalendar(dataFine);
        List<GregorianCalendar> date = CreaArrayDate.restituisciArrayDate(gcInizio,gcFine);
        int numeroGiorni = date.size();

        List<Locazione> elencoLocazioni = new ArrayList<>();

        if(commandavAnzataInt==10) {
                if(commandInt==100){
                    elencoLocazioni = globale.ricercaGlobale(provincia,prezzo,numeroGiorni);
                }
                if (commandInt == 0) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricerca("Albergo", provincia, prezzo,numeroGiorni);
                } else if (commandInt == 1) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricerca("Appartamento", provincia, prezzo,numeroGiorni);
                } else if (commandInt == 2) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricerca("Beb", provincia, prezzo,numeroGiorni);
                } else if (commandInt == 3) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricerca("CasaVacanza", provincia, prezzo,numeroGiorni);

                } else if (commandInt == 4) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricerca("Ostello", provincia, prezzo, numeroGiorni);
                }
        }else{
                if(commandavAnzataInt==0) {
                    elencoLocazioni= (ArrayList<Locazione>) ctrl.ricercaAvanzata("Albergo",provincia,prezzo,numeroGiorni, parcheggio, wifi, pet,caratteristica);
                }
                else if(commandavAnzataInt==1) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricercaAvanzata("Appartamento",provincia,prezzo,numeroGiorni, parcheggio, wifi, pet,caratteristica);
                }
                else if(commandavAnzataInt==2) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricercaAvanzata("Beb",provincia,prezzo,numeroGiorni, parcheggio, wifi, pet,"caratt");
                }
                else if(commandavAnzataInt==3) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricercaAvanzata("CasaVacanza",provincia,prezzo,numeroGiorni, parcheggio, wifi, pet, caratteristica);
                }
                else if(commandavAnzataInt==4) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricercaAvanzata("Ostello", provincia, prezzo,numeroGiorni, parcheggio, wifi, pet, caratteristica);
                }

            }

            return elencoLocazioni;
    }

    public boolean controlloDati(String provincia, String prezzo, String dataInizio, String dataFine) throws IOException {
        VerificaInput vi = new VerificaInput();
        return (vi.verificaProvincia(provincia)&&vi.verificaDate(dataInizio,dataFine)&&vi.verificaPrezzo(prezzo));

    }

}
