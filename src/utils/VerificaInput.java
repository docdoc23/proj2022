package utils;
import constants.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

// Questa classe è utilizzata per effettuare controlli sugli input dei form nelle pagine JSP.

public class VerificaInput {

	private static  String percorsoProvince = Constants.PROVINCE_PATH;

	// Costruttore

	public VerificaInput(){//default
		
	}
	// Metodo che controlla se il prezzo indicato in un form JSP sia effettivamente un numero intero, che non sia negativo e che non sia 
	// superiore a 1'000'000.
	
	public boolean verificaPrezzo(String prezzo){
        if(prezzo.trim().equals(""))
            return false;

		int prezzoInt;
		
		try{
			prezzoInt = Integer.parseInt(prezzo);
		} catch(Exception e){
			return false;
		}
		if(prezzoInt<=0||prezzoInt>1000000){
			return false;
		}

		return true;
	}

//metodo che controlla se l'getUtente ha inserito una provincia esistente e nel formato voluto
	public boolean verificaProvincia(String provincia) throws IOException {
		String pr = provincia.trim();
        if(provincia.equals(""))
            return false;
        else {
            FileReader f;
            f = new FileReader(percorsoProvince);
            //BufferedReader b;
            //b=new BufferedReader(f)
            try(BufferedReader b = new BufferedReader(f)){
            	String s;
                while (true) {
                    s = b.readLine();
                    if (s == null)
                        break;
                    if (s.trim().equalsIgnoreCase(pr))
                        return true;
                }
                
            }catch (IOException e) {
                e.printStackTrace();
            }
            return false;
            /*String s;
            while (true) {
                s = b.readLine();
                if (s == null)
                    break;
                if (s.trim().equalsIgnoreCase(pr))
                    return true;
            }
            return false;*/
        }
    }
    //metodo che controlla se data inizio e data fine siano state inserite correttamente
    public boolean verificaDate(String dataInizio, String dataFine){

        if(dataInizio.trim().equals("")||dataInizio.equals(""))
            return false;

        GregorianCalendar gcInizio;
        GregorianCalendar gcFine;

        try {
            gcInizio = TrasformaDate.trasformaInGregorianCalendar(dataInizio);
            gcFine = TrasformaDate.trasformaInGregorianCalendar(dataFine);
        }catch (Exception e ){
            return  false;
        }

        //controlla che le data non siano di anni passati
        if(gcInizio.get(Calendar.YEAR)<2017|| gcFine.get(Calendar.YEAR)<2017){
            return false;
        }
        //controlla che la dataFine non sia precedente alla dataInizio
        if(gcFine.get(Calendar.YEAR)>gcInizio.get(Calendar.YEAR))
            return true;
        if(gcFine.get(Calendar.YEAR)<gcInizio.get(Calendar.YEAR))
            return false;
        if(gcFine.get(Calendar.YEAR)==gcInizio.get(Calendar.YEAR)) {
            //Calendar.DAY_OF_YEAR restituisce il numero di giorni dall'inizio dell'anno
            if (gcInizio.get(Calendar.DAY_OF_YEAR) > gcFine.get(Calendar.DAY_OF_YEAR))
                return false;
        }
        return true;
    }

}