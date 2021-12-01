package constants;

//import java.nio.file.Path;
//import java.nio.file.Paths;

public enum Constants {;
    //***** Percorsi utili *****//
	public static final String ABS_PATH = "C:\\Users\\stefa\\eclipse-workspace\\MyPlaces\\"; /* NON MODIFICARE */
    
	public static final String ALBERGHI_PATH =    ABS_PATH.concat("data\\location\\alberghi");
	public static final String ADMIN_PATH =       ABS_PATH.concat("data\\amministratori");
	public static final String APPART_PATH =      ABS_PATH.concat("data\\location\\appartamenti");
	public static final String MSG_PATH =         ABS_PATH.concat("data\\messaggi");
	public static final String BEB =              ABS_PATH.concat("data\\location\\beb");
	public static final String CASEVACANZA_PATH = ABS_PATH.concat("data\\location\\casevacanza");
	public static final String OSTELLI_PATH =     ABS_PATH.concat("data\\location\\ostelli");
	public static final String TMPDATE_PATH =     ABS_PATH.concat("data\\tempdate");
	public static final String UTENTI_PATH =      ABS_PATH.concat("data\\utenti");
	public static final String RIMOSSE_PATH =     ABS_PATH.concat("data\\locazioniUtentiRimossi");
	public static final String HOST_PORT =        "localhost:1122/";//1122
	public static final String IMGS_PATH_REL_S =  "C:\\Users\\stefa\\eclipse-workspace\\MyPlaces\\WebContent\\css\\images\\";
	public static final String PRENOTAZIONE_ALBERGO_PATH =  ABS_PATH.concat("data\\prenotazioni\\prenotazioniAlberghi");
	public static final String PRENOTAZIONE_BEB_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniBeb");
	public static final String PRENOTAZIONE_OSTELLO_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniOstelli");
	public static final String PRENOTAZIONE_APPARTAMENTO_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniAppartamenti");
	public static final String PRENOTAZIONE_CASAVACANZA_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniCaseVacanza");
	public static final String PRENOTATI_ALBERGO_PATH = ABS_PATH.concat("data\\prenotazioni\\clientiPrenotati\\prenotatiAlbergo");
	public static final String PRENOTATI_APPARTAMENTO_PATH = ABS_PATH.concat("data\\prenotazioni\\clientiPrenotati\\prenotatiAppartamento");
	public static final String PRENOTATI_BEB_PATH = ABS_PATH.concat("data\\prenotazioni\\clientiPrenotati\\prenotatiBeb");
	public static final String PRENOTATI_CASEVACANZA_PATH = ABS_PATH.concat("data\\prenotazioni\\clientiPrenotati\\prenotatiCaseVacanza");
	public static final String PRENOTATI_OSTELLO_PATH = ABS_PATH.concat("data\\prenotazioni\\clientiPrenotati\\prenotatiOstello");
	public static final String PROVINCE_PATH = ABS_PATH.concat("data\\fileProvince");
    public static final String FAQ_PATH = ABS_PATH.concat("data\\fileFaq");
    public static final String RECENSIONI_PATH = ABS_PATH.concat("data\\recensioni");
    public static final String PACKAGE_LANGUAGE = "language.Lang";



    //***** Lingue *****//
    // Le stringhe che codificano la lingua devono corrispondere
    // a dei valori contenuti nel file Locale.java
    // (per la costruzione di new Locate)
    // e.g. italiano --> it
    //      inglese  --> en
    public static final String IT = "it";
    public static final String EN = "en";
    // Array contenente tutte le lingue disponibili del sistema
    public static final String[] LANGS = {IT, EN};

    public static final String LANG_DEFAULT = IT;



    //***** Stand alone *****//
    public static final String USER_KEY = "usernameAdmin";
    public static final String LINGUA_KEY = "linguaAdmin";

}
