package p12_sistema_prenotazione_voli_ManagerApp;

/**
 * Classe Abbonamento
 * Un oggetto della classe Abbonamento rappresenta un abbonamento per una compagnia aerea, il quale prevede il 
 * pagamento anticipato diun quantitativo di miglia da spendere in voli della stessa compagnia. L'abbonamento è 
 * costituito da un identificativo univoco idAbbonamento, da un costo e da un quantitativo di miglia.
 * 
 * @author Marta
 * @version 1.0
 */
public class Abbonamento {
	
	/** Attributi */
	private String idAbbonamento;
	private float costo;
	private int miglia;
	
	public Abbonamento(){
		this.idAbbonamento = null;
		this.costo = 0.0f;
		this.miglia = 0;
	}
	
	public Abbonamento(String idAbbonamento, float costo, int miglia){
		this.idAbbonamento = idAbbonamento;
		this.costo = costo;
		this.miglia = miglia;
	}
	
	
	/**
	 * Getter per l'attibuto idAbbonamento dell'abbonamento.
	 * 
	 * @return	l'identificativo univoco dell'abbonamento attualmente salvato.
	 */
	public String get_idAbbonamento() { return this.idAbbonamento; }
	
	/**
	 * Getter per l'attributo idAbbonamento dell'abbonamento.
	 * 
	 * @param idAbbonamento		l'identificativo univoco dell'abbonamento che si vuole settare.
	 */
	public void set_idAbbonamento(String idAbbonamento) { this.idAbbonamento = idAbbonamento; }
	
	
	/**
	 * Getter per l'attributo costo dell'abbonamento.
	 * 
	 * @return	l'ammontare del costo dell'abbonamento attualmente salvato.
	 */
	public float get_costo() { return this.costo; }
	
	/**
	 * Setter per l'attributo costo dell'abbonamento.
	 * 
	 * @param costo				l'ammontare del costo dell'abbonamento che si vuole settare.
	 */
	public void set_costo(float costo) { this.costo = costo; }
	
	
	/**
	 * Getter per l'attributo miglia dell'abbonamento.
	 * 
	 * @return	il quantitativo di miglia che prevede l'abbonamento attualmente salvato.
	 */
	public int get_miglia() { return this.miglia; }
	
	/**
	 * Setter per l'attributo miglia dellabbonamento.
	 * 
	 * @param miglia 			il quantitativo di miglia dell'abbonamento che si vuole salvare.
	 */
	public void set_miglia(int miglia) { this.miglia = miglia; }
	
}
