package control;

import entity.Prenotazione;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class ThreadVisualizzaPrenotazioniProprietario implements Runnable {

    private String proprietario;
    private String percorso;
    private static ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
    private static ReentrantLock l = new ReentrantLock();

    public ThreadVisualizzaPrenotazioniProprietario( String proprietario, String percorso){
        
        this.percorso=percorso;
        this.proprietario=proprietario;

    }

    @Override
    public void run() {
        ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<>();

        File file = new File(percorso);
        if (file.length() != 0) {
            try {
                listaPrenotazioni = (ArrayList<Prenotazione>) DeserializzaOggetti.deserializza(percorso);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            for (Prenotazione prenotazione : listaPrenotazioni) {
                if (prenotazione.getProprietario().equals(proprietario)) {
                    l.lock();
                    try {
                        prenotazioni.add(prenotazione);
                    }finally {
                        l.unlock();
                    }
                }
            }
        }
    }

    public  static List<Prenotazione> getPrenotazioni(){
        return prenotazioni;
    }

}

