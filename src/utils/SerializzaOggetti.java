package utils;

import exception.SerializzazioneException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializzaOggetti {
	
	// Costruttore
	
	public SerializzaOggetti(){//default
		
	}
	
	// Metodo che prende come input il percorso del file e un oggetto ed effettua la serializzazione.

	public static void serializza(Object obj, String percorso) throws SerializzazioneException {
		try{
			FileOutputStream f = new FileOutputStream(percorso);
			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(obj);
			s.flush();
			s.close();
		} catch(IOException e) {
			throw new SerializzazioneException("Oggetto non serializzabile");
		}
	}
	
}
