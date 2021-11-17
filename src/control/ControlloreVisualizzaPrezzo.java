package control;

import controldecorator.*;
import entity.Locazione;

/**
 * Created by maria
 */

public class ControlloreVisualizzaPrezzo {


    private PrezzoBase prezzoBase;
    private ComponentePrezzo component; //oggetto necessario per applicare le varie decorazioni

    public ControlloreVisualizzaPrezzo(){//default

    }


    //Metodo che calcola il prezzo base e lo assegna al component, nel caso di ostelli e alberghi
    //al prezzo pernottamento si aggiunge il costo del tipo di pensione e poi si moltiplica il tutto per il numero di giorni
    public void applicaPrezzoBase(Locazione locazione,int numeroGiorni, String tipoPensione){

        int prezzo;

        //Polymorphism
        prezzo= locazione.calcolaPrezzoBase(locazione,numeroGiorni,tipoPensione);

        this.prezzoBase = new PrezzoBase(prezzo);
        this.component=this.prezzoBase;


    }

    // metodo che applica opportunamente le decorazioni richieste
   public void applicaServizi(int numeroGiorni, boolean parcheggio, boolean wifi, boolean pet){

       int prezzoaggiuntivo = 0;

       if(parcheggio) {
           prezzoaggiuntivo = 8*numeroGiorni;
           DecoratorPrezzoParcheggio dparc = new DecoratorPrezzoParcheggio(this.component, prezzoaggiuntivo);
           this.component=dparc;
       }
       if(wifi) {
           prezzoaggiuntivo = 4*numeroGiorni;
           DecoratorPrezzoWifi dwif = new DecoratorPrezzoWifi(this.component, prezzoaggiuntivo);
           this.component=dwif;
       }
       if(pet) {
           prezzoaggiuntivo = 12*numeroGiorni;
           DecoratorPrezzoPet dpet= new DecoratorPrezzoPet(this.component, prezzoaggiuntivo);
           this.component=dpet;
       }


   }

   //metodo che mostra effettivamente l'applicazione di uno o più decorazioni, stampando il prezzo risultante
   public int ritornaPrezzoConServizi(){
       return this.component.calcolaPrezzo();
   }

   //metodo che ritorna il prezzo base
   public int ritornaPrezzoBase(){
        return this.prezzoBase.calcolaPrezzo();

   }






}
