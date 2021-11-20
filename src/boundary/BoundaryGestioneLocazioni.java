package boundary;

import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;


import java.io.IOException;
import java.util.List;

import controlcontrolloregestionelocazione.ControlloreInserimentoLocazione;
import controlcontrolloregestionelocazione.ControlloreRimuoviLocazione;
import controlcontrolloregestionelocazione.ControlloreVisualizzaLocazioni;


public class BoundaryGestioneLocazioni {

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getNomeLocazione() {
        return nomeLocazione;
    }

    public void setNomeLocazione(String nomeLocazione) {
        this.nomeLocazione = nomeLocazione;
    }

    public String getPostiTotali() {
        return postiTotali;
    }

    public void setPostiTotali(String postiTotali) {
        this.postiTotali = postiTotali;
    }

    
    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public String getUserLocatore() {
        return userLocatore;
    }

    public void setUserLocatore(String userLocatore) {
        this.userLocatore = userLocatore;
    }

    
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    
    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }
    
    public String getParcheggio() {
        return parcheggio;
    }

    public void setParcheggio(String parcheggio) {
        this.parcheggio = parcheggio;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    private String command;
    private String nomeLocazione;
    private String postiTotali;
    private String provincia;
    private String indirizzo;
    private String userLocatore;
    private String prezzo;
    private String descrizione;
    private String parcheggio;
    private String wifi;
    private String pet;

    private ControlloreInserimentoLocazione cil;

    public BoundaryGestioneLocazioni(){//default

    }

    public Locazione chiamaControlloreInserimento() throws IOException {

        cil= new ControlloreInserimentoLocazione(command);
        return cil.inserisciLocazione(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, Boolean.valueOf(parcheggio), Boolean.valueOf(wifi), Boolean.valueOf(pet));

    }

    public boolean verificaInserimento(Locazione locazione) throws SerializzazioneException, DeserializzazioneException {
        if (locazione.getNomeLocazione().equals("") || locazione.getPostiTotali().equals("") ||  locazione.getProvincia().equals("") ||  locazione.getIndirizzo().equals("")  || locazione.getUserLocatore().equals("") ||  locazione.getPrezzo().equals("") ||
                locazione.getDescrizione().equals("")) {
            return false;
        }
        if(locazione.getClass()== Albergo.class){
            Albergo albergo=(Albergo)locazione;
            if(albergo.getTipoPensione().equals("")|| albergo.getOrarioColazione().equals("")||  albergo.getOrarioPranzo().equals("") ||  albergo.getOrarioCena().equals(""))
                return false;
        }
        else if(locazione.getClass()== Appartamento.class){
            Appartamento appartamento=(Appartamento) locazione;
            if(appartamento.getNumeroLetti().equals("")||  appartamento.getNumeroStanze().equals("")||  appartamento.getNumeroBagni().equals(""))
                return false;

        }
        else if(locazione.getClass()== Beb.class){
            Beb beb=(Beb) locazione;
            if(beb.getOrarioColazione().equals(""))
                return false;

        }
        else if(locazione.getClass()== CasaVacanza.class){
            CasaVacanza casa=(CasaVacanza) locazione;
            if(casa.getNumeroLetti().equals("")|| casa.getNumeroCamere().equals("")||  casa.getNumeroBagni().equals(""))
                return false;

        }
        else if(locazione.getClass()== Ostello.class){
            Ostello ostello=(Ostello)locazione;
            if(ostello.getTipoPensione().equals(""))
                return false;

        }
        return true;


    }

    public void registraLocazione(Locazione locazione) throws SerializzazioneException, DeserializzazioneException {

        cil.selezionaPercorso(locazione);
        cil.serializzaLocazione(locazione);

    }




    public List<Locazione> chiamaControlloreVisualizza(String username) throws DeserializzazioneException {
            ControlloreVisualizzaLocazioni crl = new ControlloreVisualizzaLocazioni();

            List<Locazione> locazioni;
            locazioni = crl.visualizzaLocazioni(username);

            return locazioni;

    }

    public String chiamaControlloreRimozione(Locazione locazione) throws SerializzazioneException, DeserializzazioneException {
            ControlloreRimuoviLocazione cr =  new ControlloreRimuoviLocazione();

            return cr.rimuovi(locazione);
    }





}
