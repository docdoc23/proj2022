package control;

import java.io.IOException;

import entity.*;




public class FactoryInserimentoLocazione {

    private static FactoryInserimentoLocazione instance = null;

    //stato comune a tutte le locazioni
    private String nomeLocazione;
    private String postiTotali;
    private String provincia;
    private String indirizzo;
    private String userLocatore;
    private String prezzo;
    private String descrizione;
    private boolean parcheggio;
    private boolean wifi;
    private boolean pet;

    protected FactoryInserimentoLocazione(){

    }

    //metodo che permette di cambiare lo stato del singleton, viene chiamato ad ogni nuovo inserimento per settare opportunamente i parametri
    public void changeSettings(String nomeLocazione,String postiTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
                               String descrizione, boolean parcheggio, boolean wifi, boolean pet) {
        this.nomeLocazione = nomeLocazione;
        this.postiTotali=postiTotali;
        this.provincia= provincia;
        this.indirizzo = indirizzo;
        this.userLocatore = userLocatore;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.parcheggio = parcheggio;
        this.wifi = wifi;
        this.pet = pet;
    }


    //metodo che ritorna l'istanza (singleton) della factory
    public static final synchronized  FactoryInserimentoLocazione getFactoryInstance(){
        if (FactoryInserimentoLocazione.instance == null)
            FactoryInserimentoLocazione.instance = new FactoryInserimentoLocazione();
        return instance;
    }

    // metodo che effettivamente crea il tipo di locazione richiesto ritornando puerò un oggetto di tipo Locazione
    public Locazione createGenericLocation(String type) throws IOException {

        switch (type){
            case "0": return new Albergo(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, "", "", "", "");
            case "1": return new Appartamento(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, "", "", false, "");
            case "2": return new Beb(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, "");
            case "3": return new CasaVacanza(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, "", "", false, "");
            case "4": return new Ostello(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, "");
            default: throw new IOException("Invalid type : " + type);
        }


    }

}
