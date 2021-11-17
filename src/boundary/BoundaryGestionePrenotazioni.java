package boundary;

import control.ControlloreRimuoviPrenotazione;
import control.ControlloreVisualizzaPrenotazioni;
import controlabstractfactoryprenotazione.ControllorePrenotazione;
import entity.Locazione;
import entity.Prenotazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.io.IOException;

import java.util.List;


public class BoundaryGestionePrenotazioni {


    
    private ControlloreVisualizzaPrenotazioni cvp;
    

    public BoundaryGestionePrenotazioni(){//default

    }


    public boolean effettuaPrenotazione(Locazione locazione, String cliente, String dataInizio, String dataFine, String numeroPersone) throws DeserializzazioneException, SerializzazioneException, IOException {

        ControllorePrenotazione cp = new ControllorePrenotazione();

        return cp.controlloPrenotazione(locazione,cliente,dataInizio,dataFine,numeroPersone);

    }


    public List<Prenotazione> visualizzaPerViaggiatore(String cliente) throws DeserializzazioneException, InterruptedException {
        cvp = new ControlloreVisualizzaPrenotazioni();
        return cvp.visualizzaPrenotazioniPerViaggiatore(cliente);
    }

    public List<Prenotazione> visualizzaPerProprietario(String proprietario) throws DeserializzazioneException, InterruptedException {
        cvp= new ControlloreVisualizzaPrenotazioni();
        return cvp.visualizzaPrenotazioniPerProprietario(proprietario);
    }

    public void avvioRimozione(Prenotazione prenotazione) throws DeserializzazioneException, SerializzazioneException, IOException {
    	ControlloreRimuoviPrenotazione crp = new ControlloreRimuoviPrenotazione();
        crp.rimuovi(prenotazione);
    }
}
