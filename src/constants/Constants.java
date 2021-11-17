package constants;


public interface Constants {
    //***** Percorsi utili *****//
    String ABS_PATH = "C:\\Users\\stefa\\eclipse-workspace\\MyPlaces\\"; /* NON MODIFICARE */
    String ALBERGHI_PATH =    ABS_PATH.concat("data\\location\\alberghi");
    String ADMIN_PATH =       ABS_PATH.concat("data\\amministratori");
    String APPART_PATH =      ABS_PATH.concat("data\\location\\appartamenti");
    String MSG_PATH =         ABS_PATH.concat("data\\messaggi");
    String BEB =              ABS_PATH.concat("data\\location\\beb");
    String CASEVACANZA_PATH = ABS_PATH.concat("data\\location\\casevacanza");
    String OSTELLI_PATH =     ABS_PATH.concat("data\\location\\ostelli");
    String TMPDATE_PATH =     ABS_PATH.concat("data\\tempdate");
    String UTENTI_PATH =      ABS_PATH.concat("data\\utenti");
    String RIMOSSE_PATH =     ABS_PATH.concat("data\\locazioniUtentiRimossi");
    String HOST_PORT =        "localhost:1122/";//1122
    String IMGS_PATH_REL_S =  "C:\\Users\\stefa\\eclipse-workspace\\MyPlaces\\WebContent\\css\\images\\";
    String PRENOTAZIONE_ALBERGO_PATH =  ABS_PATH.concat("data\\prenotazioni\\prenotazioniAlberghi");
    String PRENOTAZIONE_BEB_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniBeb");
    String PRENOTAZIONE_OSTELLO_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniOstelli");
    String PRENOTAZIONE_APPARTAMENTO_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniAppartamenti");
    String PRENOTAZIONE_CASAVACANZA_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniCaseVacanza");
    String PRENOTATI_ALBERGO_PATH = ABS_PATH.concat("data\\prenotazioni\\clientiPrenotati\\prenotatiAlbergo");
    String PRENOTATI_APPARTAMENTO_PATH = ABS_PATH.concat("data\\prenotazioni\\clientiPrenotati\\prenotatiAppartamento");
    String PRENOTATI_BEB_PATH = ABS_PATH.concat("data\\prenotazioni\\clientiPrenotati\\prenotatiBeb");
    String PRENOTATI_CASEVACANZA_PATH = ABS_PATH.concat("data\\prenotazioni\\clientiPrenotati\\prenotatiCaseVacanza");
    String PRENOTATI_OSTELLO_PATH = ABS_PATH.concat("data\\prenotazioni\\clientiPrenotati\\prenotatiOstello");
    String PROVINCE_PATH = ABS_PATH.concat("data\\fileProvince");
    String FAQ_PATH = ABS_PATH.concat("data\\fileFaq");
    String RECENSIONI_PATH = ABS_PATH.concat("data\\recensioni");
    String PACKAGE_LANGUAGE = "language.Lang";



    //***** Lingue *****//
    // Le stringhe che codificano la lingua devono corrispondere
    // a dei valori contenuti nel file Locale.java
    // (per la costruzione di new Locate)
    // e.g. italiano --> it
    //      inglese  --> en
    String IT = "it";
    String EN = "en";
    // Array contenente tutte le lingue disponibili del sistema
    String[] LANGS = {IT, EN};

    String LANG_DEFAULT = IT;



    //***** Stand alone *****//
    String USER_KEY = "usernameAdmin";
    String LINGUA_KEY = "linguaAdmin";

}
