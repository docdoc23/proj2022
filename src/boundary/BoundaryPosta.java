package boundary;

import control.ControllorePosta;
import entity.Messaggio;


import java.util.List;


public class BoundaryPosta {

    private ControllorePosta cP;

    public BoundaryPosta() {
        cP = new ControllorePosta();
    }

    public Messaggio ritornaMessaggioCod(int codice) {
        return cP.ricercaMessaggioPerCodice(codice);
    }

    public List<Messaggio> ritornaMessaggi(String username) {
        return cP.ricercaMessaggi(username);
    }

    public int inviaMessaggio(String oggetto, String mittentte,
                              String destinatario, String contenuto) {
        return cP.scriviMessaggio(oggetto, mittentte,
                destinatario, contenuto);
    }

    public int cancellaMessaggio(int codice) {
        return cP.eliminaMessaggio(codice);
    }
}