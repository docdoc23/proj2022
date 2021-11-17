package controlabstractfactoryprenotazione;


import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.util.GregorianCalendar;

public /*abstract class*/interface ControlloreRegistraPrenotazione {

    public abstract void registra(String nomeLocazione, String proprietario, String cliente, GregorianCalendar dataInizio, GregorianCalendar dataFine, String tipo,String prezzo,String numeroPersone) throws SerializzazioneException, DeserializzazioneException;
}
