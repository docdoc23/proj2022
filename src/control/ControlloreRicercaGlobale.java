package control;

import constants.Constants;
import entity.Locazione;
import exception.DeserializzazioneException;

import java.util.ArrayList;
import java.util.List;

public class ControlloreRicercaGlobale {
    //percorsi
    private String percorsoAlbergo = Constants.ALBERGHI_PATH;
    private String percorsoAppartamento = Constants.APPART_PATH;
    private String percorsoBeb = Constants.BEB;
    private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
    private String percorsoOstello = Constants.OSTELLI_PATH;

    //costruttore
    public ControlloreRicercaGlobale(){//default
    }

    /*
    questo metodo ricerca tutte le locazioni presenti nel sistema in base alla provincia e al
    prezzo(per una notte) calcolato per il numero di giorni richiesti, vengono utilizzati 5 thread
    in modo da fa partire nello stesso momento la ricerca sui 5 file sui quali sono memorizzati i diversi
    tipo di locazioni
     */
    public List<Locazione> ricercaGlobale(String provincia, String prezzo,int numeroGiorni) throws DeserializzazioneException, InterruptedException {

      

        ArrayList<Locazione> locazioniTotali = new ArrayList<>();

        ThreadRicerca tAlb= new ThreadRicerca(percorsoAlbergo,provincia,prezzo,numeroGiorni);
        ThreadRicerca tApp= new ThreadRicerca(percorsoAppartamento,provincia,prezzo,numeroGiorni);
        ThreadRicerca tBeb= new ThreadRicerca(percorsoBeb,provincia,prezzo,numeroGiorni);
        ThreadRicerca tCasa= new ThreadRicerca(percorsoCasaVacanza,provincia,prezzo,numeroGiorni);
        ThreadRicerca tOst= new ThreadRicerca(percorsoOstello,provincia,prezzo,numeroGiorni);

        Thread t1= new Thread(tAlb);
        Thread t3 = new Thread(tBeb);
        Thread t2 = new Thread(tApp);
        Thread t5 = new Thread(tOst);
        Thread t4 = new Thread(tCasa);

        t1.start();
        t4.start();
        t2.start();
        t5.start();
        t3.start();
        
        t2.join();
        t1.join();
        t3.join();
        t5.join();
        t4.join();

        locazioniTotali.addAll(ThreadRicerca.getLocazioni());
        return locazioniTotali;

    }

}
