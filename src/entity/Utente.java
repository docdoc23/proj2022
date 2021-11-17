package entity;

import java.util.Locale;

public class Utente implements java.io.Serializable {

	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String email;
	private String immagine;
	private String nascita;
	private String sesso;
	private Locale lingua;
	
	// Costruttore
	public Utente(String username, String password, String nome,
				  String cognome, String email,
                  String immagine, Locale lingua,
                  String nascita, String sesso) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.immagine = immagine;
		this.nascita = nascita;
		this.sesso = sesso;
		this.lingua = lingua != null ? lingua : Locale.getDefault();
	}

	// Getters e Setters
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImmagine() {
		return this.immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

    public String getSesso() {
        return this.sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getNascita() {
        return this.nascita;
    }

    public void setNascita(String nascita) {
        this.nascita = nascita;
    }

    public Locale getLingua() {
        return this.lingua;
    }

    public void setLingua(Locale lingua) {
        this.lingua = lingua;
    }
}