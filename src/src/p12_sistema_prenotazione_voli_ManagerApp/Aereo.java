package p12_sistema_prenotazione_voli_ManagerApp;

/**
 * Classe Aereo
 * Un oggetto della classe Aereo rappresenta un aereo composto da un identificativo univoco idAereo, una tipologia e 
 * da un numeroPosti che identifica il numero totale di posti presente su tale aereo.
 * 
 * @author Marta
 * @version 1.0
 */
public class Aereo {
	
	/** Attributi */
	private String idAereo;
	private String tipologia;
	private int numeroPosti;
	
	public Aereo(){
		this.idAereo = null;
		this.tipologia = null;
		this.numeroPosti = 0;
	}
	
	public Aereo(String nomeAereo, String tipologia, int numeroPosti){
		this.idAereo = nomeAereo;
		this.tipologia = tipologia;
		this.numeroPosti = numeroPosti;
	}
	
	/**
	 * Getter per l'attributo idAereo dell'aereo.
	 * 
	 * @return			l'idAereo dell'aereo attualemte salvato.
	 */
	public String get_idAereo() { return this.idAereo; }
	
	/**
	 * Setter per l'attributo idAereo.
	 * 
	 * @param idAereo	identificativo univoco dell'aereo che si vuole settare.
	 */
	public void set_idAereo(String idAereo) { this.idAereo = idAereo; }
	
	
	/**
	 * Getter per l'attributo tipologia dell'aereo.
	 * 
	 * @return			la tipologia dell'aereo attualmente salvata.
	 */
	public String get_tipologia() { return this.tipologia; }
	
	/**
	 * Setter per l'attributo tipologia dell'aereo.
	 * 
	 * @param tipologia	stringa che identifica la tipologia dell'aereo che si vuole settare.
	 */
	public void set_tipologia(String tipologia) { this.tipologia = tipologia; }
	
	
	/**
	 * Getter per l'attributo numeroPosti dell'aereo.
	 * 
	 * @return			il numero totale di posti presenti sull'aereo.
	 */
	public int get_numeroPosti() { return this.numeroPosti; }
	
	/**
	 * Setter per l'attributo numeroPosti dell'aereo.
	 * 
	 * @param numeroPosti	il numero totale di posti dell'aereo che si vuole settare.
	 */
	public void set_numeroPosti(int numeroPosti) { this.numeroPosti = numeroPosti; }

}
