package p12_sistema_prenotazione_voli_ManagerApp;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe Sistema
 * Un oggetto della classe Sistema è costituito da: 
 * - una mappaGestori, ovvero una HashMap che ha come chiavi le mail dei gestori delle compagnie aeree (identificativo
 * 	 univoco degli oggetti-GestoreCompagniaAerea) e come valori gli oggetti-GestoreCompagniaAerea
 * - una mappa , ovvero una HashMap che ha come chiavi le mail dei gestori delle compagnie aeree e come valori gli 
 *   oggetti-CompagniaAerea che identificano la compagnia aerea associata a tali gestori
 * - un GestoreCompagniaAerea che identifica il gestore che attualmente sta usando il sistema, il quale viene settato
 *   solo nel caso in cui l'autenticazione di tale gestore va a buon fine.
 *  
 * @author Marta Piccardo
 * @version 1.0
 */
public class Sistema {
	
	// Attributi 
	private GestoreCompagniaAerea gestore;
	
	// Associazioni 
	//dove String indica la mail dei GestoriCompagniaAerea (identificativo univoco)
	private Map<String ,GestoreCompagniaAerea> mappaGestori;
	private Map<String, CompagniaAerea> mappa;

	
	public Sistema (){
		this.gestore = new GestoreCompagniaAerea();
		this.mappaGestori = new HashMap<String, GestoreCompagniaAerea>();
		this.mappa = new HashMap<String, CompagniaAerea>();
	}
	
	/**
	 * Setter per l'attributo gestore di sistema.
	 * 
	 * @param gestore	gestore che si è loggato.
	 */
	public void set_gestore(GestoreCompagniaAerea gestore){
		this.gestore = gestore;
	}
	
	/**
	 * Funzione che reinizializza il l'attributo gestore.
	 */
	public void reset_gestore(){
		this.gestore = new GestoreCompagniaAerea();
	}
	
	/**
	 * Getter per l'attributo gestore di sistema.
	 * 
	 * @return 			gestore attualmente loggato.
	 */
	public GestoreCompagniaAerea get_gestore(){
		return this.gestore;
	}
	
	/**
	 * Getter per l'attributo mappaGestori di sistema.
	 * 
	 * @return 			HashMap (chiave=mail, valore=GestoreCompagniaAerea).
	 */
	public Map<String, GestoreCompagniaAerea> get_mappaGestori(){
		return this.mappaGestori;
	}
	
	/**
	 * Getter per l'attributo mappa (che associa ad ogni gestore una compagnia) di sistema. 
	 * 
	 * @return 			HashMap (chiave=mail, valore=CompagniaAerea).
	 */
	public Map<String, CompagniaAerea> get_mappa(){
		return this.mappa;
	}
	
	/**
	 * Questa funzione permette di aggiungere al sistema un nuovo gestore insieme alla 
	 * compagnia di cui è responsabile. Ha lo scopo di facilitare i test, in quanto simula
	 * l'esecuzione di un'altra applicazione che prevede l'inserimento di gestori e compagnie.
	 * 
	 * @param nome			nome del gestore che si vuole aggiungere al sistema
	 * @param cognome		cognome del gestore che si vuole aggiungere al sistema
	 * @param mail			mail (id univoco) del gestore che si vuole aggiungere al sistema
	 * @param password		password del gestore che si vuole aggiungere al sistema
	 * @param nomeCompagniaAerea	compagnia aerea di cui è responsabile il gestore
	 */
	public void aggiungi_gestore(String nome, String cognome, String mail, String password, String nomeCompagniaAerea){
		GestoreCompagniaAerea gestore = new GestoreCompagniaAerea(nome, cognome, mail, password);
		CompagniaAerea compagnia = new CompagniaAerea();
		compagnia.set_nomeCompagniaAerea(nomeCompagniaAerea);
		this.mappaGestori.put(mail, gestore);
		this.mappa.put(mail, compagnia);
	}
	

	//********** Metodi per: AEREI **********/
	/**
	 * UC18 : Inserire un nuovo aereo
	 * Questa funzione permette di creare un nuovo aereo e di inserirlo nella lista "listaAerei" con identificativo
	 * univoco (nomeAereo), tipologia e numero posti. Prima della creazione dell'aereo la funzione controlla che non
	 * sia già presente nella lista un'altro aereo con lo stesso identificativo.
	 * 
	 * @param idAereo		identificativo univoco per aereo
	 * @param tipologia		String che indica se continentale/intercontinentale...
	 * @param numeroPosti	numero totale di posti presenti sull'aereo
	 * @return 				true se l'inserimento dell'aereo è andato a buon fine, false altrimenti.
	 */
	public boolean inserireNuovoAereo(String idAereo, String tipologia, int numeroPosti){
		//se il gestore è loggato
		if(gestore.get_login()){
			//se non esiste già un altro idAereo uguale
			if(idAereo != null && tipologia != null && numeroPosti != 0){
				if(ricercareAereo(idAereo)!=null)
					return false;
				Aereo nuovo_aereo = new Aereo(idAereo, tipologia, numeroPosti);
				mappa.get(gestore.get_mail()).get_listaAerei().add(nuovo_aereo);
				return true;
			}
			
		}
		return false;
	}
	
	/**
	 * UC19 : Ricercare un aereo
	 * Questa funzione permette di ricercare all'interno della lista "listaAerei" l'aereo corrispondente al nomeAereo
	 * (identificativo univoco) passato per parametro.
	 * 
	 * @param idAereo		identificativo univoco per aereo
	 * @return 				Aereo se presente nella lista, null altrimenti.
	 */
	public Aereo ricercareAereo(String idAereo){
		//Questa funzione torna l'oggetto-aereo se va a buon fine la ricerca, null altrimenti
		if(gestore.get_login()){
			for(int i = 0; i < mappa.get(gestore.get_mail()).get_listaAerei().size(); i ++){
				if(mappa.get(gestore.get_mail()).get_listaAerei().get(i).get_idAereo().equals(idAereo)){
					System.out.println(mappa.get(gestore.get_mail()).get_listaAerei().get(i).get_idAereo());
					return mappa.get(gestore.get_mail()).get_listaAerei().get(i);
				}
			}
		}
		return null;		
	}
	
	/**
	 * UC20	: Cancellare un aereo
	 * Questa funzione permette di eliminare all'interno della lista "listaAerei" l'aereo corrispondente al nomeAereo
	 * (identificativo univoco) passato per parametro. Si serve della funzione "ricercareAereo(String nomeAereo)";
	 * se questa ritona un Aereo allora la funzione scorre la lista per l'eliminazione.
	 * 
	 * @param idAereo		identificativo univoco per aereo
	 * @return				true se leliminazione è andata a buon fine, false altrimenti.
	 */
	public boolean cancellareAereo(String idAereo){
		if(gestore.get_login()){
			Aereo aereo_temp = this.ricercareAereo(idAereo);
			if(aereo_temp != null){
				mappa.get(gestore.get_mail()).get_listaAerei().remove(aereo_temp);
				return true;
			}
		}
		return false;
	}

	
	//********** Metodi per: VOLI **********/
	
	/**
	 * UC21	: Inserire un nuovo volo
	 * Questa funzione permette di creare un nuovo volo e di inserirlo nella lista "listaVoli" con identificativo
	 * univoco (idVolo), aereo, luogoPartenza, luogoDestinazione, dataOraPartenza, durata, prezzo biglietto, miglia.
	 * Prima della creazione del volo la funzione controlla che non sia già presente nella lista un'altro volo con
	 * lo stesso identificativo. Controlla inoltre che l'aereo passato per parametro sia presente nella "listaAerei". 
	 * 
	 * @param idVolo			identificativo univoco per volo
	 * @param idAereo			id dell'aereo riferito al volo; deve essere presente nella listaAerei
	 * @param luogoPartenza		String che indica il luogo di partenza
	 * @param luogoDestinazione	String che indica il luogo di arrivo
	 * @param dataOraPartenza	data e ora di partenza	
	 * @param durata			durata del volo
	 * @param prezzoBiglietto	prezzo del biglietto
	 * @param miglia			miglia del volo
	 * @return					true se l'inserimento del volo è andato a buon fine, false altrimenti.
	 */
	public boolean inserireNuovoVolo(String idVolo, String idAereo, String luogoPartenza, String luogoDestinazione, 
			Calendar dataOraPartenza, float durata, float prezzoBiglietto, int miglia){
		if(gestore.get_login()){
			//se tutti i parametri sono diversi da null/0.0f/0
			if(idVolo != null && idAereo != null && luogoPartenza != null && luogoDestinazione != null &&
			 dataOraPartenza != null && durata != 0.0f && prezzoBiglietto != 0.0f && miglia != 0){
				//se esiste l'aereo con idAereo
				Aereo aereo_temp = this.ricercareAereo(idAereo);
				if(aereo_temp != null){
					//se non esiste un altro volo con lo stesso idVolo
					if(ricercareVolo(idVolo)!=null)
						return false;
					Volo nuovo_volo = new Volo(idVolo, aereo_temp, luogoPartenza, luogoDestinazione, 
							dataOraPartenza, durata, prezzoBiglietto, miglia);
					mappa.get(gestore.get_mail()).get_listaVoli().add(nuovo_volo);
					return true;
				}
			}
			
		}
		return false;		
	}
	
	/**
	 * UC22	: Ricercare un volo
	 * Questa funzione permette di ricercare all'interno della lista "listaVoli" il volo corrispondente a idVolo
	 * (identificativo univoco) passato per parametro.
	 * 
	 * @param idVolo	identificativo univoco per volo
	 * @return			Volo se presente nella lista, null altrimenti.
	 */
	public Volo ricercareVolo(String idVolo){
		//Questa funzione torna l'oggetto-volo se va a buon fine la ricerca, null altrimenti
		if(gestore.get_login()){
			for(int i = 0; i < mappa.get(gestore.get_mail()).get_listaVoli().size(); i ++){
				if(mappa.get(gestore.get_mail()).get_listaVoli().get(i).get_idVolo().equals(idVolo)){
					return mappa.get(gestore.get_mail()).get_listaVoli().get(i);
				}
			}
		}
		return null;		
	}
	
	/**
	 * UC23	: Modificare un volo
	 * Questa funzione permette di modificare un volo all'interno della "listaVoli". Controlla che il volo
	 * corrispondente all'idVolo passato per parametro esista, in caso affermativo modifica gli attributi di 
	 * tale volo solo se hanno un valore diverso da quello nullo (per oggetti), 0.0f (float) e di 0 (per int).
	 * Controlla inoltre che l'idAereo passato per parametro corriponda ad un aereo realmente esistente in "listaAerei".
	 * 
	 * @param idVolo				identificativo univoco del volo che si vuole modificare
	 * @param idAereo				identificativo univoco del nuovo aereo se inserito, null altrimenti
	 * @param luogoPartenza			nuovo luogo di partenza se inserito, null altrimenti
	 * @param luogoDestinazione		nuovo luogo di destinazione se inserito, null altrimenti
	 * @param dataOraPartenza		nuova data e ora di partenza se inserita, null altrimenti
	 * @param durata				nuova durata del volo se inserito, 0.0f altrimenti
	 * @param prezzoBiglietto		nuovo prezzo del biglietto se inserito, 0.0f altrimenti
	 * @param miglia				nuove miglia del volo se inserito, 0 altrimenti
	 * @return						true se la modifica del volo è andata a buon fine, false altrimenti
	 */
	public boolean modificareVolo(String idVolo, String idAereo, String luogoPartenza, String luogoDestinazione,
			Calendar dataOraPartenza, float durata, float prezzoBiglietto, int miglia){
		if(gestore.get_login()){
			Volo volo = ricercareVolo(idVolo);
			if(volo!= null){
					if(idAereo != null){
						System.out.println(ricercareAereo(idAereo));
						Aereo aereo_temp = ricercareAereo(idAereo);
						if(aereo_temp != null){
							ricercareVolo(idVolo).set_aereo(aereo_temp);
						}
						else{
							return false;
						}
					}
						
					if(luogoPartenza != null)
						ricercareVolo(idVolo).set_luogoPartenza(luogoPartenza);
					if(luogoDestinazione != null)
						ricercareVolo(idVolo).set_luogoDestinazione(luogoDestinazione);
					if(dataOraPartenza != null)
						ricercareVolo(idVolo).set_dataOraPartenza(dataOraPartenza);
					if(durata != 0.0f)
						ricercareVolo(idVolo).set_durata(durata);
					if(prezzoBiglietto != 0.0f)
						ricercareVolo(idVolo).set_prezzoBiglietto(prezzoBiglietto);
					if(miglia != 0)
						ricercareVolo(idVolo).set_miglia(miglia);
				return true;
				
			}
		}
		return false;
	}
	
	/**
	 * UC24	: Cancellare un volo
	 * Questa funzione permette di eliminare all'interno della lista "listaVoli" il volo corrispondente a idVolo
	 * (identificativo univoco) passato per parametro. Si serve della funzione "ricercareVolo(String idVolo)";
	 * se questa ritona un volo allora la funzione scorre la lista per l'eliminazione. Non elimina l'aereo associato
	 * al volo dalla lista "listaAerei".
	 * 
	 * @param idVolo	identificativo univo per volo
	 * @return			true se leliminazione è andata a buon fine, false altrimenti.
	 */
	public boolean cancellareVolo(String idVolo){
		if(gestore.get_login()){
			Volo volo_temp = this.ricercareVolo(idVolo);
			if(volo_temp != null){
				mappa.get(gestore.get_mail()).get_listaVoli().remove(volo_temp);
				return true;
			}
		}
		return false;
	}
	
	
	//********** Metodi per: ABBONAMENTI **********/
	
	/**
	 * UC25 : Inserire un nuovo abbonamento
	 * Questa funzione permette di creare un nuovo abbonamento e di inserirlo nella lista "listaAbbonamenti" con 
	 * identificativo univoco (idAbbonamento), costo e miglia. Prima della creazione dell'abbonamento la funzione 
	 * controlla che non sia già presente nella lista un'altro abbonamento con lo stesso identificativo.
	 * 
	 * @param idAbbonamento		identificativo univoco per abbonamento
	 * @param costo				costo delleabbonamento
	 * @param miglia			totale miglia che prevede l'abbonamento
	 * @return					true se l'inserimento dell'abboanemnto è andato a buon fine, false altrimenti.
	 */
	public boolean inserireNuovoAbbonamento(String idAbbonamento, float costo, int miglia){
		//se il gestore è loggato
		if(gestore.get_login()){
			if(idAbbonamento != null && costo != 0.0f && miglia != 0){
				//se non esiste già un altro idAbbonamento uguale
				if(ricercareAbbonamento(idAbbonamento)!=null)
					return false;
				
				Abbonamento nuovo_abbonamento = new Abbonamento(idAbbonamento, costo, miglia);
				mappa.get(gestore.get_mail()).get_listaAbbonamenti().add(nuovo_abbonamento);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * UC26	: Ricercare un abbonamento
	 * Questa funzione permette di ricercare all'interno della lista "listaAbbonamenti" l'abbonamento corrispondente 
	 * a idAbbonamento (identificativo univoco) passato per parametro.
	 * 
	 * @param idAbbonamento		identificativo univoco per abbonamento
	 * @return					Abbonamento se presente nella lista, null altrimenti.
	 */
	public Abbonamento ricercareAbbonamento(String idAbbonamento){
		//Questa funzione torna l'oggetto-abbonamento se va a buon fine la ricerca, null altrimenti
		if(gestore.get_login()){
			for(int i = 0; i < mappa.get(gestore.get_mail()).get_listaAbbonamenti().size(); i ++){
				if(mappa.get(gestore.get_mail()).get_listaAbbonamenti().get(i).get_idAbbonamento().equals(idAbbonamento)){
					return mappa.get(gestore.get_mail()).get_listaAbbonamenti().get(i);
				}
			}
		}
		return null;		
	}
	
	/**
	 * UC27	: Cancellare un abbonamento
	 * Questa funzione permette di eliminare all'interno della lista "listaAbbonamenti" l'abbonamento corrispondente
	 * a idAbbonamento (identificativo univoco) passato per parametro. Si serve della funzione 
	 * "ricercareAbbonamento(String idAbbonamento)";se questa ritona un Abbonamento allora la funzione scorre la 
	 * lista per l'eliminazione.
	 * 
	 * @param idAbbonamento		identificativo univoco per abbonamento
	 * @return					true se leliminazione è andata a buon fine, false altrimenti.
	 */
	public boolean cancellareAbbonamento(String idAbbonamento){
		if(gestore.get_login()){
			Abbonamento abbonamento_temp = this.ricercareAbbonamento(idAbbonamento);
			if(abbonamento_temp != null){
				mappa.get(gestore.get_mail()).get_listaAbbonamenti().remove(abbonamento_temp);
				return true;
			}
		}
		return false;
	}
	
	//********** Metodi per: AUTENTICAZIONE/DEAUTENTICAZIONE **********/
	
	/**
	 * UC3	: Autenticarsi
	 * Questa funzione permette al gestore di autenticarsi fornendo mail (identificativo univoco) e password.
	 * Controlla che la mail sia presente nel sistema, in caso affermativo controlla che la password corrisponda:
	 * solo in questo caso la funzione imposta il flag autenticato a true ed imposta l'attributo (della classe)
	 * "gestore" con tutti i dati a lui relativi.
	 * 
	 * @param mail			identificativo univoco per gestore compagnia aerea
	 * @param password		password del gestore che si vuole autenticare
	 * @return				true se l'autenticazione è andata a buon fine, false altrimenti
	 */
	public boolean login(String mail, String password){
		if(mappa.containsKey(mail)){
			if(mappaGestori.get(mail).get_password().equals(password)){
				mappaGestori.get(mail).set_login();
				set_gestore(mappaGestori.get(mail));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * UC11	: Deautenticarsi
	 * Questa funzione permette al gestore attualmente autenticato nel sistema di deautenticarsi. 
	 * Controlla se il flag autenticato è impostato a true: in caso affermativo lo resetta a false ed imposta il
	 * parametro (della classe) "gestore" a null.
	 *  
	 * @return			true se la deautenticazione è andata a buon fine, false altrimenti.
	 */
	public boolean logout(){
		if(gestore.get_login()){
			mappaGestori.get(gestore.get_mail()).set_logout();
			reset_gestore();
			return true;
		}
		return false;		
	}

}
