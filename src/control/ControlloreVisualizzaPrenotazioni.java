package control;

import constants.Constants;
import entity.Prenotazione;
import exception.DeserializzazioneException;

import java.util.ArrayList;
import java.util.List;

public class ControlloreVisualizzaPrenotazioni {

    private static String percorsoPrenotatiAlbergo = Constants.PRENOTATI_ALBERGO_PATH;
    private static String percorsoPrenotatiAppartamento = Constants.PRENOTATI_APPARTAMENTO_PATH;
    private static String percorsoPrenotatiBeb = Constants.PRENOTATI_BEB_PATH;
    private static String percorsoPrenotatiCasaVacanza = Constants.PRENOTATI_CASEVACANZA_PATH;
    private static String percorsoPrenotatiOstello = Constants.PRENOTATI_OSTELLO_PATH;

    /*
    metodo utilizzato per restituire le prenotazioni effettuate dal viaggiatore, essendo le prenotazioni
    salvate su 5 file (distinti dal tipo locazione), vengono sfruttati 5 thread ognuno dei quali restitusce
    le prenotazioni associate al viaggiatore
     */

    public List<Prenotazione> visualizzaPrenotazioniPerViaggiatore (String cliente) throws DeserializzazioneException, InterruptedException {

        ArrayList<Prenotazione> prenotazioneTotali = new ArrayList<>();

        ThreadVisualizzaPrenotazioniViaggiatore tAlb = new ThreadVisualizzaPrenotazioniViaggiatore(cliente, percorsoPrenotatiAlbergo);
        ThreadVisualizzaPrenotazioniViaggiatore tApp = new ThreadVisualizzaPrenotazioniViaggiatore(cliente, percorsoPrenotatiAppartamento);
        ThreadVisualizzaPrenotazioniViaggiatore tBeb = new ThreadVisualizzaPrenotazioniViaggiatore(cliente, percorsoPrenotatiBeb);
        ThreadVisualizzaPrenotazioniViaggiatore tCasa = new ThreadVisualizzaPrenotazioniViaggiatore(cliente, percorsoPrenotatiCasaVacanza);
        ThreadVisualizzaPrenotazioniViaggiatore tOst = new ThreadVisualizzaPrenotazioniViaggiatore(cliente, percorsoPrenotatiOstello);

        Thread t1= new Thread(tAlb);
        Thread t2 = new Thread(tApp);
        Thread t3 = new Thread(tBeb);
        Thread t4 = new Thread(tCasa);
        Thread t5 = new Thread(tOst);

        
        t3.start();
        t4.start();
        t5.start();
        t1.start();
        t2.start();

        t2.join();
        t3.join();
        t4.join();
        t1.join();
        t5.join();

        prenotazioneTotali.addAll(ThreadVisualizzaPrenotazioniViaggiatore.getPrenotazioni());

        return prenotazioneTotali;
    }

    /*
    metodo utilizzato per restituire le prenotazioni relative alle locazioni
    del proprietario, essendo le prenotazioni salvate su 5 file (distinti dal tipo locazione),
    vengono sfruttati 5 thread ognuno dei quali restituscele prenotazioni associate al viaggiatore
     */

    public List<Prenotazione> visualizzaPrenotazioniPerProprietario (String proprietario) throws DeserializzazioneException, InterruptedException {
        ArrayList<Prenotazione> prenotazioneTotali = new ArrayList<>();

        ThreadVisualizzaPrenotazioniProprietario tAlb = new ThreadVisualizzaPrenotazioniProprietario(proprietario, percorsoPrenotatiAlbergo);
        ThreadVisualizzaPrenotazioniProprietario tApp = new ThreadVisualizzaPrenotazioniProprietario(proprietario, percorsoPrenotatiAppartamento);
        ThreadVisualizzaPrenotazioniProprietario tBeb = new ThreadVisualizzaPrenotazioniProprietario(proprietario, percorsoPrenotatiBeb);
        ThreadVisualizzaPrenotazioniProprietario tCasa = new ThreadVisualizzaPrenotazioniProprietario(proprietario, percorsoPrenotatiCasaVacanza);
        ThreadVisualizzaPrenotazioniProprietario tOst = new ThreadVisualizzaPrenotazioniProprietario(proprietario, percorsoPrenotatiOstello);

        Thread t1= new Thread(tAlb);
        Thread t2 = new Thread(tApp);
        Thread t3 = new Thread(tBeb);
        Thread t4 = new Thread(tCasa);
        Thread t5 = new Thread(tOst);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();


        prenotazioneTotali.addAll(ThreadVisualizzaPrenotazioniProprietario.getPrenotazioni());

        return prenotazioneTotali;
    }





}
