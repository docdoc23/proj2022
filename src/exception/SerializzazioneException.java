package exception;

public class SerializzazioneException extends Exception {

	private static final long serialVersionUID = 1L;
	
	// Costruttori

	public SerializzazioneException(){//default
		
	}
	
	public SerializzazioneException(String m){
		super(m);
	}
	
	
	
}
