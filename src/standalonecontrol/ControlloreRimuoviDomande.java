package standalonecontrol;

import constants.Constants;
import entity.DomandaUtente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standaloneboundary.BoundaryFallimento;
import standaloneboundary.BoundarySuccesso;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControlloreRimuoviDomande {

    private JTextArea area;

    private String path = Constants.FAQ_PATH;

    private ControlloreLinguaAmministratore cl;

    public ControlloreRimuoviDomande(JTextArea area){
        this.area = area;
        this.cl = new ControlloreLinguaAmministratore();
    }

    public void rimuovi(String domanda) throws DeserializzazioneException, SerializzazioneException {
        ResourceBundle bundle = this.cl.getBundleFromProp();

        if(domanda.equals("")) {
            new BoundaryFallimento(bundle.getString("boundaryDomande_domanda_non_presente"));
            return;
        }
        SerializzaOggetti sobj= new SerializzaOggetti();
        DeserializzaOggetti dobj = new DeserializzaOggetti();
        ArrayList<DomandaUtente> domande = (ArrayList<DomandaUtente>) dobj.deserializza(path);

        for(int i=0; i<domande.size(); ++i){
            if(domande.get(i).getDomanda().equals(domanda)){
                domande.remove(i);
                sobj.serializza(domande,path);
                new BoundarySuccesso();
                return;
            }
        }

        new BoundaryFallimento(bundle.getString("boundaryDomande_domanda_non_presente"));
    }

    public void visualizzaDomandeSenzaRisposta(){
        ResourceBundle bundle = this.cl.getBundleFromProp();

        ArrayList<DomandaUtente> domandaUtente;
        DeserializzaOggetti dobj= new DeserializzaOggetti();
        File file = new File(path);
        String incipit= bundle.getString("boundaryDomande_domande_senza_risposta");
        String domande = "";

         if(file.length()!=0) {
             try {
                 domandaUtente = (ArrayList<DomandaUtente>) dobj.deserializza(path);
                 for (DomandaUtente f : domandaUtente) {
                     if (!f.getSettaRisposta()) {
                         domande = domande + f.getDomanda() + '\n';
                     }
                 }
                 area.setText("");
                 area.insert(incipit + '\n'+ domande, 0);
             } catch (DeserializzazioneException e) {
                 e.printStackTrace();
             }
         }

         else {
             area.setText("");
             area.insert(incipit + '\n' + domande, 0);
         }


    }
}
