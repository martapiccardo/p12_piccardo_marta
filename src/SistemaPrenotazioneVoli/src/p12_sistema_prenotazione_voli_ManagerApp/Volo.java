package p12_sistema_prenotazione_voli_ManagerApp;

import java.util.Calendar;

/**
 * Classe Volo
 * Un oggetto della classe Volo rappresenta un volo composto da un identificativo univoco idVolo, da un aereo 
 * attribuito a tale volo (della stessa CompagniaAerea), da un luogoPartenza e un luogoDestinazione, da una 
 * dataOraPartenza, da una durata, da un prezzoBiglietto e dalle miglia totali che percorre tale volo.
 * 
 * @author Marta
 * @version 1.0
 */
public class Volo {
	
	/** Attributi */
	private String idVolo;
	private Aereo aereo;
	private String luogoPartenza;
	private String luogoDestinazione;
	private Calendar dataOraPartenza;
	private float durata;
	private float prezzoBiglietto;
	private int miglia;
	
	public Volo(){
		this.idVolo = null;
		this.aereo = null;
		this.luogoPartenza = null;
		this.luogoDestinazione= null;
		this.dataOraPartenza = null;
		this.durata = 0.0f;
		this.prezzoBiglietto = 0.0f;
		this.miglia = 0;
	}
	
	public Volo(String idVolo, Aereo aereo, String luogoPartenza, String luogoDestinazione, Calendar dataOraPartenza,
				float durata, float prezzoBiglietto, int miglia){
		this.idVolo = idVolo;
		this.aereo = aereo;
		this.luogoPartenza = luogoPartenza;
		this.luogoDestinazione= luogoDestinazione;
		this.dataOraPartenza = dataOraPartenza;
		this.durata = durata;
		this.prezzoBiglietto = prezzoBiglietto;
		this.miglia = miglia;
	}
	
	/**
	 * Getter per l'attributo idVolo del volo.
	 * 
	 * @return 			l'identificativo univoco del volo attualmente salvato.
	 */
	public String get_idVolo() { return this.idVolo; }
	
	/**
	 * Setter per l'attributo idVolo del volo.
	 * 
	 * @param idVolo	identificativo univoco del volo che si vuole settare.
	 */
	public void set_idVolo(String idVolo) { this.idVolo = idVolo; }

	
	/**
	 * Getter per l'attributo aereo del volo.
	 * 
	 * @return			l'aereo attualmente salvato per tale volo.
	 */
	public Aereo get_aereo() { return this.aereo; }
	
	/**
	 * Setter per l'attributo aereo del volo.
	 * 
	 * @param aereo		aereo del volo che si vuole settare.
	 */
	public void set_aereo(Aereo aereo) { this.aereo = aereo; }
	
	
	/**
	 * Getter per l'attributo luogoPartenza del volo
	 * 
	 * @return			il luogo di partenza del volo attualmente salvato.
	 */
	public String get_luogoPartenza() { return this.luogoPartenza; }
	
	/**
	 * Setter per l'attributo luogoPartenza del volo.
	 * 
	 * @param luogoPartenza	luogo di partenza del volo che si vuole settare.
	 */
	public void set_luogoPartenza(String luogoPartenza) { this.luogoPartenza = luogoPartenza; }
	
	
	/**
	 * Getter per l'attributo luogoDestinazione del volo
	 * 
	 * @return			il luogo di destinazione del volo attualmente salvato.
	 */
	public String get_luogoDestinazione() { return this.luogoDestinazione; }
	
	/**
	 * Setter per l'attributo luogoDestinazione del volo.
	 * 
	 * @param luogoDestinazione		luogo di destinazione del volo che si vuole settare.
	 */
	public void set_luogoDestinazione(String luogoDestinazione) { this.luogoDestinazione = luogoDestinazione; }

	
	/**
	 * Getter per l'attributo dataOraPartenza del volo.
	 * 
	 * @return			 la data e l'ora di partenza (come oggetto Calendar) del volo attualmente salvate.
	 */
	public Calendar get_dataOraPartenza() { return this.dataOraPartenza; }
	
	/**
	 * Setter per l'attributo dataOraPartenza del volo.
	 * 
	 * @param dataOraPartenza	la data e l'ora di partenza (come oggetto Calendar) del volo che si vogliono settare.
	 */
	public void set_dataOraPartenza(Calendar dataOraPartenza) { this.dataOraPartenza = dataOraPartenza; }

	
	/**
	 * Getter per l'attributo durata del volo.
	 * 
	 * @return 			la durata del volo attualmente salvata.
	 */
	public float get_durata() { return this.durata; }
	
	/**
	 * Setter per l'attributo durata del volo.
	 * 
	 * @param durata	la durata del volo che si vuole settare.
	 */
	public void set_durata(float durata) { this.durata = durata; }
	
	
	/**
	 * Getter per l'attributo prezzoBiglietto del volo.
	 * 
	 * @return			il prezzo del biglietto del volo attualmente salvato.
	 */
	public float get_prezzoBiglietto() { return this.prezzoBiglietto; }
	
	/**
	 * Setter per l'attributo prezzoBiglietto del volo.
	 * 
	 * @param prezzoBiglietto	il prezzo del biglietto del volo che si vuole settare.
	 */
	public void set_prezzoBiglietto(float prezzoBiglietto) { this.prezzoBiglietto = prezzoBiglietto; }
	
	
	/**
	 * Getter per l'attributo miglia del volo.
	 * 
	 * @return 			le miglia del volo attualmente salvate.
	 */
	public int get_miglia() { return this.miglia; }
	
	/**
	 * Setter per l'attributo miglia del volo.
	 * 
	 * @param miglia	le miglia del volo che si vogliono settare.
	 */
	public void set_miglia(int miglia) { this.miglia = miglia; }

}
