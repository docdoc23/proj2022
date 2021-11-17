package controldecorator;


//ConcreteDecorator 3
public class DecoratorPrezzoWifi extends DecoratorPrezzo {

    private int prezzoWifi;

    public DecoratorPrezzoWifi(ComponentePrezzo component, int prezzoWifi){
        super(component);
        this.prezzoWifi = prezzoWifi;

    }

    protected int applyWifi(int input){
        /*i-n-t output = input + this.prezzoWifi
        return output*/
        
        return input + this.prezzoWifi;
    }

    @Override
    public int calcolaPrezzo() {
        int preliminaryPrice = super.calcolaPrezzo();
        preliminaryPrice = this.applyWifi(preliminaryPrice);
        return preliminaryPrice;
    }

}
