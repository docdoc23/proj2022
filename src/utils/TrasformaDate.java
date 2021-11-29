package utils;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class TrasformaDate {

    public TrasformaDate(){//default
    }

    public static GregorianCalendar trasformaInGregorianCalendar(String data){

        //FIREFOX
        /*
        int giorno = Integer.parseInt(data.substring(0, 2))
        int meseUtente = Integer.parseInt(data.substring(3, 5))
        int mese= meseUtente-1
        int anno = Integer.parseInt(data.substring(6, 10))
        */


        int giorno = Integer.parseInt(data.substring(8, 10));
        int meseUtente = Integer.parseInt(data.substring(5, 7));
        int mese= meseUtente-1;
        int anno = Integer.parseInt(data.substring(0, 4));

        if(mese==0)
            return new GregorianCalendar(anno,Calendar.JANUARY,giorno);
        else if(mese==1)
           return new GregorianCalendar(anno,Calendar.FEBRUARY,giorno);
        else if(mese==2)
            return new GregorianCalendar(anno,Calendar.MARCH,giorno);
        else if(mese==3)
            return new GregorianCalendar(anno,Calendar.APRIL,giorno);
        else if(mese==4)
            return new GregorianCalendar(anno,Calendar.MAY,giorno);
        else if(mese==5)
            return new GregorianCalendar(anno,Calendar.JUNE,giorno);
        else if(mese==6)
            return new GregorianCalendar(anno,Calendar.JULY,giorno);
        else if(mese==7)
            return new GregorianCalendar(anno,Calendar.AUGUST,giorno);
        else if(mese==8)
            return new GregorianCalendar(anno,Calendar.SEPTEMBER,giorno);
        else if(mese==9)
            return new GregorianCalendar(anno,Calendar.OCTOBER,giorno);
        else if(mese==10)
            return new GregorianCalendar(anno,Calendar.NOVEMBER,giorno);
        else
            return new GregorianCalendar(anno,Calendar.DECEMBER,giorno);

    }

    public static void main(String[] args){
        GregorianCalendar d = TrasformaDate.trasformaInGregorianCalendar("2017-08-10");
        System.out.println("data inizio"+d.get(Calendar.DATE) + d.get(Calendar.MONTH) +d.get(Calendar.YEAR));

    }

}