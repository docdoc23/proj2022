package control;

import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.io.IOException;


public interface ControlloreRicercaPerLocazione {

    Object ricerca(String tipo, String provincia, String prezzo,int numeroGiorni) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, DeserializzazioneException, IOException, SerializzazioneException, InterruptedException;


    Object ricercaAvanzata(String tipo, String provincia,String prezzo,int numeroGiorni, String parcheggio, String wifi, String pet,String caratteristica) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, DeserializzazioneException, IOException, SerializzazioneException, InterruptedException;
}
