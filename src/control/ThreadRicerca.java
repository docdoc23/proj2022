package control;

import entity.Locazione;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadRicerca implements Runnable{

    private String percorso;
    private String provincia;
    private String prezzo;
    private int numeroGiorni;
    private static ArrayList<Locazione> locazioni= new ArrayList<>();
    private static ReentrantLock l = new ReentrantLock();

    public ThreadRicerca(String percorso,String provincia,String prezzo,int numeroGiorni){
        this.percorso=percorso;
        this.provincia=provincia;
        this.prezzo=prezzo;
        this.numeroGiorni=numeroGiorni;
    
    }
    @Override
    public void run() {
        ArrayList<Locazione> locazioniTemp = new ArrayList<>();

        File file = new File(percorso);
        if(file.length()==0){
            return;
        }
        if(file.length()!=0){
            DeserializzaOggetti dobj = new DeserializzaOggetti();
            try {
                locazioniTemp = (ArrayList<Locazione>) dobj.deserializza(percorso);
            }catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            for (Locazione loc : locazioniTemp) {
                if ((loc.getProvincia().equalsIgnoreCase(provincia)) &&  //controllo sulla provincia
                        ((Integer.parseInt(loc.getPrezzo().trim()))*numeroGiorni) <= ((Integer.parseInt(prezzo.trim()))*numeroGiorni)) {//controllo sul prezzo
                            l.lock();
                            try{
                                locazioni.add(loc);
                            }finally {
                                l.unlock();
                            }
                }
            }
        }
    }

    public static List<Locazione> getLocazioni(){
        return  locazioni;
    }
}
