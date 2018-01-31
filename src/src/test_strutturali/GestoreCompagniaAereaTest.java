package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.GestoreCompagniaAerea;

public class GestoreCompagniaAereaTest {

	GestoreCompagniaAerea gestore1;
	GestoreCompagniaAerea gestore2;
	
	@Before
	public void setUp() throws Exception {
		gestore1 = new GestoreCompagniaAerea();
		gestore2 = new GestoreCompagniaAerea("Mario", "Rossi", "mario.rossi@gmail.com", "psw");
	}

	//Costruttore senza parametri
	@Test
	public void testGestoreCompagniaAerea() {
		assertNotNull(gestore1);
		assertNull(gestore1.get_nome());
		assertNull(gestore1.get_cognome());
		assertNull(gestore1.get_mail());
		assertNull(gestore1.get_password());
		assertFalse(gestore1.get_login());
	}

	//Costruttore con parametri nome, cognome, mail, password
	@Test
	public void testGestoreCompagniaAereaStringStringStringString() {
		assertNotNull(gestore2);
		assertEquals("Mario", gestore2.get_nome());
		assertEquals("Rossi", gestore2.get_cognome());
		assertEquals("mario.rossi@gmail.com", gestore2.get_mail());
		assertEquals("psw", gestore2.get_password());
		assertFalse(gestore2.get_login());
	}

	@Test
	public void testSetGet_nome() {
		gestore1.set_nome("Giuseppe");
		assertEquals("Giuseppe", gestore1.get_nome());
	}

	@Test
	public void testSetGet_cognome() {
		gestore1.set_cognome("Bianchi");
		assertEquals("Bianchi", gestore1.get_cognome());
	}

	@Test
	public void testSetGet_mail() {
		gestore1.set_mail("giuseppe.bianchi@gmail.com");
		assertEquals("giuseppe.bianchi@gmail.com", gestore1.get_mail());
	}

	@Test
	public void testSetGet_password() {
		gestore1.set_password("pass");
		assertEquals("pass", gestore1.get_password());
	}

	@Test
	public void testSetGet_login() {
		gestore1.set_login();
		assertTrue(gestore1.get_login());
	}

	@Test
	public void testSet_logout() {
		gestore1.set_logout();
		assertFalse(gestore1.get_login());
	}

}
