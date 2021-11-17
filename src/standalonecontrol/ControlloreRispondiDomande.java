package standalonecontrol;

import constants.Constants;
import entity.DomandaUtente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standaloneboundary.BoundaryFallimento;
import standaloneboundary.BoundarySuccesso;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControlloreRispondiDomande {

    private String path = Constants.FAQ_PATH;
    private ArrayList<DomandaUtente> faqArray;

    private ControlloreLinguaAmministratore cl;

    public ControlloreRispondiDomande(){
        this.cl = new ControlloreLinguaAmministratore();
    }

    public void rispondiComeSuperUtente(String domanda,String risposta) throws DeserializzazioneException, SerializzazioneException {
        ResourceBundle bundle = this.cl.getBundleFromProp();

        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();
        faqArray=(ArrayList<DomandaUtente>) dobj.deserializza(path);

        if(risposta.equals("")) {
            new BoundaryFallimento(bundle.getString("boundaryDomande_risposte_vuote"));
            return;
        }

        for(DomandaUtente domandaUtente : faqArray){
            if(domandaUtente.getDomanda().equals(domanda) && !domandaUtente.getSettaRisposta()){
                domandaUtente.setRisposta(risposta);
                domandaUtente.setSettaRisposta(true);
                break;

            }

        }

        sobj.serializza(faqArray,path);
        new BoundarySuccesso();

    }


    public String[] ritornaDomandeSenzaRisposta(){

        ArrayList<DomandaUtente> domandaUtenteArray;
        DeserializzaOggetti dobj = new DeserializzaOggetti();
        ArrayList<String> domande = new ArrayList<String>();

        String dom[];

        dom=null;
        File file = new File(path);
        if(file.length()!=0) {
            try {


                domandaUtenteArray = (ArrayList<DomandaUtente>) dobj.deserializza(path);

                for (DomandaUtente domandaUtente : domandaUtenteArray) {
                    if (!domandaUtente.getSettaRisposta())
                        domande.add(domandaUtente.getDomanda());
                }
                dom = new String[domande.size()];
                for (int i = 0; i < domande.size(); ++i) {
                    dom[i] = domande.get(i);
                }

            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }
        }
        return dom;
    }
}
