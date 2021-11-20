package utils;

import constants.Constants;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CreaArrayDate {


    @SuppressWarnings("unchecked")
    public static  List<GregorianCalendar> restituisciArrayDate(GregorianCalendar dataInizio, GregorianCalendar dataFine) throws IOException, SerializzazioneException, DeserializzazioneException {
        String percorsoTemp = Constants.TMPDATE_PATH;
        ArrayList<GregorianCalendar> elencoDate = new ArrayList<>();
        File f = new File(percorsoTemp);

        if(f.delete()){

            FileWriter w = new FileWriter(percorsoTemp);
            
            try(BufferedWriter b = new BufferedWriter(w)){
            	b.write("");
                b.flush();
                b.close();
            }catch(IOException e) {
            	e.printStackTrace();
            }
            
            
            
            
            //BufferedWriter b = new BufferedWriter(w);
            //b.write("");
            //b.flush();
            //b.close();

            SerializzaOggetti.serializza(elencoDate, percorsoTemp);


            while (!dataInizio.equals(dataFine)) {

                elencoDate = (ArrayList<GregorianCalendar>) DeserializzaOggetti.deserializza(percorsoTemp);
                elencoDate.add(dataInizio);
                SerializzaOggetti.serializza(elencoDate, percorsoTemp);
                dataInizio.add(Calendar.DAY_OF_MONTH, 1);
            }
            elencoDate = (ArrayList<GregorianCalendar>) DeserializzaOggetti.deserializza(percorsoTemp);
            elencoDate.add(dataInizio);
            SerializzaOggetti.serializza(elencoDate, percorsoTemp);
        }
        return elencoDate;
    }

}
