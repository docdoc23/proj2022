package controldecorator;


//ConcreteComponent
public class PrezzoBase implements ComponentePrezzo {

    int prezzo;

    public PrezzoBase(int prezzo){
        this.setPrezzo (prezzo);
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }


    @Override
    public int calcolaPrezzo() {
        return (this.prezzo);
    }
}
