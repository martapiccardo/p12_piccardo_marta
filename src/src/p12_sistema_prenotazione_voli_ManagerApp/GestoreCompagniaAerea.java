package p12_sistema_prenotazione_voli_ManagerApp;

/**
 * Classe GestoreCompagniaAerea
 * Un oggetto della classe GestoreCompagniaAerea rapprensenta un gestore di una compagnia composto da: nome,
 * cognome, mail (identificatore univoco), password ed un boolean "autenticato" il quale se posto a true indica che
 * tale gestore è stato autenticato con successo, false altrimenti.
 *  
 * @author Marta
 * @version 1.0
 */
public class GestoreCompagniaAerea {

	/** Attributi */
	private String nome;
	private String cognome;
	private String mail;
	private String password;
	private boolean autenticato;
	
	public GestoreCompagniaAerea(){
		this.nome = null;
		this.cognome = null;
		this.mail = null;
		this.password = null;
		this.autenticato = false;
	}
	
	public GestoreCompagniaAerea(String nome, String cognome, String mail, String password){
		this.nome = nome;
		this.cognome = cognome;
		this.mail = mail;
		this.password = password;
		this.autenticato = false;
	}

	/**
	 * Getter per l'attributo nome.
	 * 
	 * @return		il nome del gestore attualmente salvato.
	 */
	public String get_nome() { return this.nome; }
	
	/**
	 * Setter per l'attributo nome.
	 * 
	 * @param nome	il nome del gestore che si vuole settare.
	 */
	public void set_nome(String nome) { this.nome = nome; }
	
	
	/**
	 * Getter per l'attributo cognome del gestore.
	 * 
	 * @return		il cognome del gestore attualmente salvato.
	 */
	public String get_cognome() { return this.cognome; }
	
	/**
	 * Setter per l'attributo cognome del gestore.
	 *  
	 * @param cognome	il cognome del gestore che si vuole settare.
	 */
	public void set_cognome(String cognome) { this.cognome = cognome; }
	
	
	/**
	 * Getter per l'attributo mail del gestore.
	 * 
	 * @return		la mail del gestore attualmente salvata.
	 */
	public String get_mail() { return this.mail; }
	
	/**
	 * Setter per l'attributo mail del gestore.
	 * 
	 * @param mail	la mail del gestore (identificativo univoco) che si vuole settare.
	 */
	public void set_mail(String mail) { this.mail = mail; }
	
	
	/**
	 * Getter per l'attributo password del gestore.
	 * 
	 * @return		la password del gestore attualmente salvata.
	 */
	public String get_password() { return this.password; }
	
	/**
	 * Setter per l'attributo password del gestore.
	 * 
	 * @param password	la password del gestore che si vuole settare.
	 */
	public void set_password(String password) { this.password = password; }
	
	
	/**
	 * Getter per l'attributo autenticato del gestore.
	 * 
	 * @return		true se il gestore è attualmente autenticato, false altrimenti.
	 */
	public boolean get_login() { return this.autenticato; }
	
	/**
	 * Funzione che permette di settare a true l'atrributo autenticato del gestore.
	 */
	public void set_login() { this.autenticato = true; }
	
	/**
	 * Funzione che permette di settare a false l'atrributo autenticato del gestore.
	 */
	public void set_logout() { this.autenticato = false; }
	
}
