package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Sistema;

public class UC11_Deautenticarsi {

	Sistema sistema;
	boolean annulla;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : gestore che vuole effettuare il logout deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "mr125", "Alitalia");
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
	}

	//NB : Non essendoci interfaccia grafica il volere del gestore di annullare il logout
	// viene simulato attraverso un boolean "annulla"(true se ha confermato di voler annullare
	// il logout, false altrimenti)
	
	//Deautenticazione : riuscita								(scenario principale)
	@Test
	public void UC11test1() {
		annulla = false;
		if(!annulla){
			assertTrue(sistema.logout());
			assertNull(sistema.get_gestore().get_nome());
			assertNull(sistema.get_gestore().get_cognome());
			assertNull(sistema.get_gestore().get_mail());
			assertNull(sistema.get_gestore().get_password());
			assertFalse(sistema.get_gestore().get_login());
			assertFalse(sistema.get_mappaGestori().get("cverdi@gmail.com").get_login());
		}
	}
	
	//Deautenticazione : annullata per volere del gestore		(scenario alternativo 3a)
	@Test
	public void UC11test2() {
		annulla = true;
		if(annulla)
			assertTrue(sistema.get_mappaGestori().get("cverdi@gmail.com").get_login());
	}
	
}
