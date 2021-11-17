package controldecorator;


//ConcreteDecorator 2
public class DecoratorPrezzoPet extends DecoratorPrezzo {

    private int prezzoPet;

    public DecoratorPrezzoPet(ComponentePrezzo component, int prezzoPet){
        super(component);
        this.prezzoPet = prezzoPet;

    }

    protected int applyPet(int input){
        /*i-n-t output = input + this.prezzoPet
        return output*/
        
        return input + this.prezzoPet;
    }

    @Override
    public int calcolaPrezzo() {
        int preliminaryPrice = super.calcolaPrezzo();
        preliminaryPrice = this.applyPet(preliminaryPrice);
        return preliminaryPrice;
    }

}

