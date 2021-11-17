package controlabstractfactoryprenotazione;

public /*abstract class*/interface FactoryPrenotazione {

    public abstract ControlloreDisponibilita creaControlloreDisponibilita();
    public abstract ControlloreRegistraPrenotazione creaControlloreRegistraPrenotati();

}
