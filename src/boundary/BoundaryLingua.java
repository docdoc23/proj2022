package boundary;

import control.ControlloreLingua;

import java.util.Locale;
import java.util.ResourceBundle;


public class BoundaryLingua {

    private ControlloreLingua cL;

    public BoundaryLingua() {
        cL = new ControlloreLingua();
    }

    public ResourceBundle riceviBundle(Locale locale) {
        return ControlloreLingua.getBundle(locale);
    }

    public Locale riceviLocaleDaString(String lang) {
                return cL.getLocaleFromString(lang);
    }

    public String riceviStringDaLocale(Locale locale) {
        return cL.getStringFromLocale(locale);
    }

    public Locale riceviLinguaDefault() {
        return cL.getLang();
    }

    public  Locale riceviLingua(String user) {
        return cL.getLang(user);
    }

    public Locale aggiornaPreferenze(String username, String lang) {
        return cL.checkUpdatePref(username, lang);
    }
}
