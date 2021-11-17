package decoratorTest;

import control.ControlloreVisualizzaPrezzo;
import entity.Albergo;
import entity.Locazione;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DecoratorTestAlb {
	
	private static int numeroGiorni=3;

    //parcheggio price= 8/gg;
    //wifi price= 4/gg;
    //pet price= 12/gg;

    private ControlloreVisualizzaPrezzo cvp = new ControlloreVisualizzaPrezzo();

    @Before
    public void creaPrecondizioni(){
        Locazione locazione= new Albergo("Sole","10","Roma","Piazza del Popolo n.4","Maria","30","Albergo Centrale",
                true,true,true,"completa", "7:00","13:15","20:00");

        cvp.applicaPrezzoBase(locazione, numeroGiorni, "mezza pensione");
    }

    @Test
    public void testBase(){

        int totale=cvp.ritornaPrezzoBase();
        Assert.assertEquals(150,totale);

    }

    @Test
    public void testNoServizi(){

        cvp.applicaServizi(numeroGiorni,false,false,false);
        int totale=cvp.ritornaPrezzoConServizi();
        Assert.assertEquals(150,totale);

    }

    @Test
    public void testTuttiServizi(){

        cvp.applicaServizi(numeroGiorni,true, true, true);
        int totale=cvp.ritornaPrezzoConServizi();
        Assert.assertEquals(222,totale);

    }

    @Test
    public void testWifi(){

        cvp.applicaServizi(numeroGiorni,true,false,false);
        int totale=cvp.ritornaPrezzoConServizi();
        Assert.assertEquals(174,totale);

    }


    @Test
    public void testParcheggioPet(){

        cvp.applicaServizi(numeroGiorni,true,false,true);
        int totale=cvp.ritornaPrezzoConServizi();
        Assert.assertEquals(210,totale);

    }
}
