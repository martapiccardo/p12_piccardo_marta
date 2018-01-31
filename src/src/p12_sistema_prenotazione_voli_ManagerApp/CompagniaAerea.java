package p12_sistema_prenotazione_voli_ManagerApp;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Classe CompagniaAerea
 * Un oggetto della classe CompagniaAerea rappresenta una compagnia aerea, costituita da un nomeCompagniaAerea che la 
 * identifica univocamente, da una listaAerei che identifica gli aerei associati a tale compagnia, da una 
 * listaAbbonamenti che identifica gli abbonamenti associati a tale compagnia e da una listaVoli che identifica i voli
 * associati a tale compagnia.
 * 
 * @author Marta
 * @version 1.0
 */
public class CompagniaAerea {

	/** Attributi */
	private String nomeCompagniaAerea;
	
	/** Associazioni */
	private ArrayList<Aereo> listaAerei;
	private ArrayList<Abbonamento> listaAbbonamenti;
	private ArrayList<Volo> listaVoli;
	
	
	public CompagniaAerea(){
		this.nomeCompagniaAerea = null;
		this.set_listaAerei(new ArrayList<Aereo>());
		this.set_listaAbbonamenti(new ArrayList<Abbonamento>());
		this.set_listaVoli(new ArrayList<Volo>());
	}
	
	/**
	 * Getter per l'attributo nomeCompagniaAerea.
	 * 
	 * @return 		il nome attuale della Compagnia Aerea.
	 */
	public String get_nomeCompagniaAerea() { return this.nomeCompagniaAerea; }
	
	/**
	 * Setter per l'attributo nomeCompagniaAerea.
	 * 
	 * @param nomeCompagniaAerea 	il nome della Compagnia Aerea che si vuole settare.
	 */
	public void set_nomeCompagniaAerea(String nomeCompagniaAerea) { this.nomeCompagniaAerea = nomeCompagniaAerea; }

	
	/**
	 * Getter riferito all'associazione listaAerei.
	 * 
	 * @return 		la lista degli aerei della Compagnia attualmente salvati.
	 */
	public ArrayList<Aereo> get_listaAerei() { return this.listaAerei; }
	
	/**
	 * Setter riferito all'associazione listaAerei.
	 * 
	 * @param listaAerei	la lista deglia aerei della Compagnia che si vuole settare.
	 */
	public void set_listaAerei(ArrayList<Aereo> listaAerei) { this.listaAerei = listaAerei; }

	
	/**
	 * Getter riferito all'associazione listaAbbonamenti.
	 * 
	 * @return		la lista degli abbonamenti della Compagnia attualmente salvati.
	 */
	public ArrayList<Abbonamento> get_listaAbbonamenti() { return this.listaAbbonamenti; }
	
	/**
	 * Setter riferito all'associazione listaAbbonamenti.
	 * 
	 * @param listaAbbonamenti	la lista degli abbonamenti della Compagnia che si vuole settare.
	 */
	public void set_listaAbbonamenti(ArrayList<Abbonamento> listaAbbonamenti) { this.listaAbbonamenti = listaAbbonamenti; }

	
	/**
	 * Getter riferito all'associazione listaVoli.
	 * 
	 * @return		la lista dei voli della Compagnia attualmente salvati.
	 */
	public ArrayList<Volo> get_listaVoli() { return this.listaVoli; }
	
	/**
	 * Setter riferito all'associazione listaVoli.
	 * 
	 * @param listaVoli	la lista dei voli della Compagnia che si vuole settare.
	 */
	public void set_listaVoli(ArrayList<Volo> listaVoli) {this.listaVoli = listaVoli; }
	
	
	/**
	 * Funzione che stampa a video la lista degli aerei salvati.
	 */
	public void print_listaAerei(){
		System.out.println(nomeCompagniaAerea + " : lista aerei");
		for(int i = 0; i < listaAerei.size(); i++){
			System.out.println("");
			System.out.println(i+1 +") ID: " + listaAerei.get(i).get_idAereo());
			System.out.println("   TIPOLOGIA: " + listaAerei.get(i).get_tipologia());
			System.out.println("   POSTI: " + listaAerei.get(i).get_numeroPosti());
		}
		System.out.println("");
	}
	
	/**
	 * Funzione che stampa a video la lista dei voli salvati.
	 */
	public void print_listaVoli(){
		System.out.println(nomeCompagniaAerea + " : lista voli");
		int giorno, mese, anno, ora, minuti;
		String troncato;
		for(int i = 0; i < listaVoli.size(); i++){
			giorno = listaVoli.get(i).get_dataOraPartenza().get(Calendar.DAY_OF_MONTH);
			mese = listaVoli.get(i).get_dataOraPartenza().get(Calendar.MONTH) + 1;
			anno = listaVoli.get(i).get_dataOraPartenza().get(Calendar.YEAR);
			ora = listaVoli.get(i).get_dataOraPartenza().get(Calendar.HOUR_OF_DAY);
			minuti = listaVoli.get(i).get_dataOraPartenza().get(Calendar.MINUTE);
			System.out.println("");
			System.out.println(i+1 +") ID: " + listaVoli.get(i).get_idVolo());
			System.out.println("   ID AEREO: " + listaVoli.get(i).get_aereo().get_idAereo());
			System.out.println("   PARTENZA: " + listaVoli.get(i).get_luogoPartenza());
			System.out.println("   DESTINAZIONE: " + listaVoli.get(i).get_luogoDestinazione());
			System.out.println("   DATA: " + giorno + "/" + mese + "/" + anno);
			System.out.println("   ORA: " + ora + " h e " + minuti + " min");
			troncato = String.format ("%.2f", listaVoli.get(i).get_durata());
			System.out.println("   DURATA: " + troncato + " (hh,mm)");
			troncato = String.format ("%.2f", listaVoli.get(i).get_prezzoBiglietto());
			System.out.println("   PREZZO BIGLIETTO: " + troncato +" €");
			System.out.println("   MIGLIA: " + listaVoli.get(i).get_miglia());
		}
		System.out.println("");
	}
	
	/**
	 * Funzione che stampa a video la lista degli abbonamenti salvati.
	 */
	public void print_listaAbbonamenti(){
		System.out.println(nomeCompagniaAerea + " : lista abbonamenti");
		String troncato;
		for(int i = 0; i < listaAbbonamenti.size(); i++){
			System.out.println("");
			System.out.println(i+1 +") ID: " + listaAbbonamenti.get(i).get_idAbbonamento());
			troncato = String.format ("%.2f", listaAbbonamenti.get(i).get_costo());
			System.out.println("   COSTO: " + troncato +" €");
			System.out.println("   MIGLIA: " + listaAbbonamenti.get(i).get_miglia());
		}
		System.out.println("");
	}
}
