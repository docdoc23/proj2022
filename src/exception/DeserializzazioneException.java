package exception;

public class DeserializzazioneException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	// Costruttori

	public DeserializzazioneException(){//default
		
	}
	
	public DeserializzazioneException(String m){
		super(m);
	}

}
