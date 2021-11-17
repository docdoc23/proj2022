package controlcontrolloregestionelocazione;


import control.FactoryInserimentoLocazione;
import entity.Locazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class ControlloreInserimentoLocazione {


    private List<Locazione> locazioniList = new ArrayList<>();

    private String command; //il command, settato dalla boundary quado chiama il controllore serve per identificare il tipo di locazione da creare
    private String path;


    public ControlloreInserimentoLocazione(String command) {
        this.command = command;
    }

    //metodo che chiama la factory, ne cambia lo stato, e richiede la creazione della locazione oppurtuna passandogli command
    public Locazione inserisciLocazione(String nomeLocazione, String postiTotali, String provincia, String indirizzo, String userLocatore, String prezzo,
                                        String descrizione, boolean parcheggio, boolean wifi, boolean pet) throws IOException {



        FactoryInserimentoLocazione factory = FactoryInserimentoLocazione.getFactoryInstance();
        factory.changeSettings(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
        return factory.createGenericLocation(command);


    }

    //metodo di utilità per selezionare il percorso corretto per la serializzazione del nuovo oggetto creato
    public void selezionaPercorso(Locazione locazione){

        path = locazione.selectPath();
        System.out.println(path);
    
    }

    //metodo che serializza effettivamente la nuova locazione creata
    public void serializzaLocazione(Locazione locazione) throws SerializzazioneException, DeserializzazioneException {

        File file = new File(path);
        SerializzaOggetti sobj = new SerializzaOggetti();
        DeserializzaOggetti dobj = new DeserializzaOggetti();



        if (file.length() == 0) {
            locazioniList.clear();
            locazioniList.add(locazione);
            sobj.serializza(locazioniList, path);
        }else{

        locazioniList = (ArrayList<Locazione>) dobj.deserializza(path);
        locazioniList.add(locazione);
        sobj.serializza(locazioniList, path);
		}



    }
}
