package decoratortest;

import control.ControlloreVisualizzaPrezzo;
import entity.Beb;
import entity.Locazione;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DecoratorTestBeB {
	
	private static int numeroGiorni=4;

    //parcheggio price= 8/gg;
    //wifi price= 4/gg;
    //pet price= 12/gg;

    private ControlloreVisualizzaPrezzo cvp = new ControlloreVisualizzaPrezzo();

    @Before
    public void creaPrecondizioni(){
        Locazione locazione= new Beb("Luna","7","Roma","Piazza del Popolo n.3","Maria","20","Accogliente",
                true,true,true,"7:00");

        cvp.applicaPrezzoBase(locazione, numeroGiorni, "mezza pensione");
    }

    @Test
    public void testBase(){

        int totale=cvp.ritornaPrezzoBase();
        Assert.assertEquals(80,totale);

    }

    
    @Test
    public void testNoServizi(){

        cvp.applicaServizi(numeroGiorni,false,false,false);
        int totale=cvp.ritornaPrezzoConServizi();
        Assert.assertEquals(80,totale);

    }
    
    @Test
    public void testTuttiServizi(){

        cvp.applicaServizi(numeroGiorni,true, true, true);
        int totale=cvp.ritornaPrezzoConServizi();
        Assert.assertEquals(176,totale);

    }


    @Test
    public void testPet(){

        cvp.applicaServizi(numeroGiorni,false,false,true);
        int totale=cvp.ritornaPrezzoConServizi();
        Assert.assertEquals(128,totale);

    }


    @Test
    public void testWifiPet(){

        cvp.applicaServizi(numeroGiorni,false,true,true);
        int totale=cvp.ritornaPrezzoConServizi();
        Assert.assertEquals(144,totale);

    }
	
}
