package controlabstractfactoryprenotazione;


public class FactoryPrenotazionePiuPosti implements FactoryPrenotazione {


    private FactoryPrenotazionePiuPosti(){//default
    }

    //Instance necessaria per il singleton
    private static FactoryPrenotazionePiuPosti instance = null;

    //Metodo necessario per il singleton
    public static final synchronized  FactoryPrenotazionePiuPosti getFactoryPrenotazionePiuPosti(){
        if(FactoryPrenotazionePiuPosti.instance==null)
            FactoryPrenotazionePiuPosti.instance = new FactoryPrenotazionePiuPosti();
        return instance;
    }
    

    @Override
    public ControlloreRegistraPrenotazione creaControlloreRegistraPrenotati() {
        return new ControlloreRegistraPrenotazioniPiuPosti();
    }


	@Override
	public ControlloreDisponibilita creaControlloreDisponibilita() {
		
		return new ControlloreDisponibilitaPiuPosti();
	}


}
