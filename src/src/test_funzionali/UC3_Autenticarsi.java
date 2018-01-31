package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.GestoreCompagniaAerea;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;

public class UC3_Autenticarsi {

	Sistema sistema;
	
	@Before
	public void setUp() throws Exception {
		//Precondizione : gestore che vuole effettuare il login devono essere registrato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "mr125", "Alitalia");
		GestoreCompagniaAerea gestore2 = new GestoreCompagniaAerea();
		gestore2.set_nome("Carlo");
		gestore2.set_cognome("Verdi");
		gestore2.set_mail("cverdi@gmail.com");
		gestore2.set_password("psw");
		sistema.aggiungi_gestore(gestore2.get_nome(), gestore2.get_cognome(), gestore2.get_mail(),
				gestore2.get_password(), "Airfrance");
	}

	//Autenticazione : riuscita								(scenario principale)
	@Test
	public void UC3test1() {
		sistema.login("cverdi@gmail.com", "psw");
		assertTrue(sistema.get_gestore().get_login());
	}
	
	//Autenticazione : fallita per mail sbagliata			(scenario alternativo 4a)
	@Test
	public void UC3test2() {
		sistema.login("mario.rossi", "mr125");
		assertNull(sistema.get_gestore().get_nome());
		assertNull(sistema.get_gestore().get_cognome());
		assertNull(sistema.get_gestore().get_mail());
		assertNull(sistema.get_gestore().get_password());
		assertFalse(sistema.get_gestore().get_login());
	}
	
	//Autenticazione : fallita per password sbagliata		(scenario alternativo 4a)
	@Test
	public void UC3test3() {
		sistema.login("mario.rossi@gmail.com", "ffff");
		assertNull(sistema.get_gestore().get_nome());
		assertNull(sistema.get_gestore().get_cognome());
		assertNull(sistema.get_gestore().get_mail());
		assertNull(sistema.get_gestore().get_password());
		assertFalse(sistema.get_gestore().get_login());
	}

}
