package controldecorator;


//Decorator
public abstract class DecoratorPrezzo implements ComponentePrezzo {

    private ComponentePrezzo component;

    protected DecoratorPrezzo(ComponentePrezzo component){
        this.component = component;
    }

    @Override
    public int calcolaPrezzo() {
        /* i-n-t resultsFromRedirection = this.component.calcolaPrezzo()
        return resultsFromRedirection*/
        return this.component.calcolaPrezzo();
    }
}
