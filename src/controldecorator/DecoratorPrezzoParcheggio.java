package controldecorator;


//ConcreteDecorator 1
public class DecoratorPrezzoParcheggio extends DecoratorPrezzo {

    private int prezzoParcheggio;

    public DecoratorPrezzoParcheggio(ComponentePrezzo component, int prezzoParcheggio){
        super(component);
        this.prezzoParcheggio = prezzoParcheggio;

    }

    protected int applyParcheggio(int input){
        /*i-n-t output = input + this.prezzoParcheggio
        return output*/
        
        return input + this.prezzoParcheggio;
    }

    @Override
    public int calcolaPrezzo() {
        int preliminaryPrice = super.calcolaPrezzo();
        preliminaryPrice = this.applyParcheggio(preliminaryPrice);
        return preliminaryPrice;
    }

}
