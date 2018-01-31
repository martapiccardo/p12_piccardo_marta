package test_strutturali;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Abbonamento;
import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;
import p12_sistema_prenotazione_voli_ManagerApp.Volo;

public class SistemaTest {

	Sistema sistema;
	
	@Before
	public void setUp() throws Exception {
		sistema = new Sistema();
	}
	
	@After
	public void tearDown() throws Exception {
		sistema = null;
	}
	
	@Test
	public void testSistema() {
		assertNotNull(sistema);
		assertNotNull(sistema.get_gestore());
		assertEquals(null,sistema.get_gestore().get_nome());
		assertEquals(null, sistema.get_gestore().get_cognome());
		assertEquals(null, sistema.get_gestore().get_mail());
		assertEquals(null, sistema.get_gestore().get_password());
		assertFalse(sistema.get_gestore().get_login());
		assertTrue(sistema.get_mappaGestori().isEmpty());
		assertTrue(sistema.get_mappa().isEmpty());
	}

	@Test
	public void testAggiungi_gestore() {
		int dim = sistema.get_mappaGestori().size();
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		//controllo che le HashMap siano cresciute di dimensione
		assertEquals(dim+1, sistema.get_mappaGestori().size());
		assertEquals(dim+1, sistema.get_mappa().size());
		//controllo che le HashMap contengano come chiave la mail del nuovo gestore
		assertTrue(sistema.get_mappaGestori().containsKey("mario.rossi@gmail.com"));
		assertTrue(sistema.get_mappa().containsKey("mario.rossi@gmail.com"));
		//controllo che l'oggetto gestore sia stato aggiunto correttamente
		assertEquals("Mario", sistema.get_mappaGestori().get("mario.rossi@gmail.com").get_nome());
		assertEquals("Rossi", sistema.get_mappaGestori().get("mario.rossi@gmail.com").get_cognome());
		assertEquals("psw", sistema.get_mappaGestori().get("mario.rossi@gmail.com").get_password());
		assertFalse(sistema.get_mappaGestori().get("mario.rossi@gmail.com").get_login());
		assertEquals("Alitalia", sistema.get_mappa().get("mario.rossi@gmail.com").get_nomeCompagniaAerea());
	}

	@Test
	public void testInserireNuovoAereo() {
		//Controllo1 : se il getore non è loggato non può aggiungere un aereo
		assertFalse(sistema.inserireNuovoAereo("id_aereo_1", "jet", 156));
		
		//Controllo2 : se il gestore è loggato allora inserireNuovoAereo va a buon fine e..
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		assertTrue(sistema.login("mario.rossi@gmail.com", "psw"));
		int dim = sistema.get_mappa().get("mario.rossi@gmail.com").get_listaAerei().size();
		assertTrue(sistema.inserireNuovoAereo("id_aereo_1", "jet", 156));
		// .. la lista aerei deve essere aumentata di dimensione e..
		assertEquals(dim+1, sistema.get_mappa().get("mario.rossi@gmail.com").get_listaAerei().size());
		// .. l'aereo aggiunto è stato inserito correttamente nella listaAerei della compagnia del gestore
		assertNotNull(sistema.ricercareAereo("id_aereo_1"));
		assertEquals("jet", sistema.ricercareAereo("id_aereo_1").get_tipologia());
		assertEquals(156, sistema.ricercareAereo("id_aereo_1").get_numeroPosti());
		
		//Controllo3 : se si vuole inserire un nuovo aereo con un idAereo già esistente allora 
		// inserireNuovoAereo non va a buon fine 
		assertFalse(sistema.inserireNuovoAereo("id_aereo_1", "Boeing777", 300));
		
		//Controllo4 : se si vuole inserire un nuovo aereo con parametri nulli/0  allora 
		// inserireNuovoAereo non va a buon fine 
		assertFalse(sistema.inserireNuovoAereo(null, "Boeing777", 300));
		assertFalse(sistema.inserireNuovoAereo("id_aereo_3", null, 300));
		assertFalse(sistema.inserireNuovoAereo("id_aereo_3", "Boeing777", 0));
		
		
	}

	@Test
	public void testRicercareAereo() {
		//Controllo1 : se il gestore non è loggato la ricerca torna null
		assertNull(sistema.ricercareAereo("id_aereo1"));
		
		//Controllo2 : se il gestore è loggato ma l'idAereo non esiste allora la ricerca non va a buon fine
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		sistema.login("mario.rossi@gmail.com", "psw");
		sistema.inserireNuovoAereo("id_aereo_1", "jet", 156);
		assertNull(sistema.ricercareAereo("idaereo1"));
		
		//Controllo3 : se il gestore è loggato e l'idAereo è giusto allora la ricerca va a buon fine
		Aereo aereo = sistema.ricercareAereo("id_aereo_1");
		assertNotNull(aereo);
		assertEquals("jet", aereo.get_tipologia());
		assertEquals(156, aereo.get_numeroPosti());
	}

	@Test
	public void testCancellareAereo() {
		//Controllo1 : se il gestore non è loggato non può cancellare un aereo
		assertFalse(sistema.cancellareAereo("id_aereo_1"));
		
		//Controllo2 : se il gestore è loggato ma l'idAereo non esiste allora cancellareAereo non va a buon fine
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		assertTrue(sistema.login("mario.rossi@gmail.com", "psw"));
		assertFalse(sistema.cancellareAereo("id_aereo_1"));
		
		//Controllo3 : se il gestore è loggato e esiste l'aereo con idAereo allora cancellareAereo
		// va a buon fine e..
		sistema.inserireNuovoAereo("id_aereo_1", "jet", 156);
		int dim = sistema.get_mappa().get("mario.rossi@gmail.com").get_listaAerei().size();
		assertTrue(sistema.cancellareAereo("id_aereo_1"));
		assertNull(sistema.ricercareAereo("id_aereo_1"));
		//.. la lista aerei deve essere diminuita di dimensione
		assertEquals(dim-1, sistema.get_mappa().get("mario.rossi@gmail.com").get_listaAerei().size());
	}

	@Test
	public void testInserireNuovoVolo() {
		//Controllo1 : se il getore non è loggato allora inserireNuovoVolo non va a buon fine
		Calendar dataOra = Calendar.getInstance();
		dataOra.set(2018, 4, 1, 7, 30 );
		assertFalse(sistema.inserireNuovoVolo("id_volo",  "id_aereo_2", "Milano", "Roma",
				dataOra, 1.1f, 80.0f, 380));
		
		//Controllo2 : se il gestore è loggato ma l'aereo per il volo non esiste allora
		// inserireNuovoAereo non va a buon fine
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		assertTrue(sistema.login("mario.rossi@gmail.com", "psw"));
		sistema.inserireNuovoAereo("id_aereo", "jet", 156);
		assertFalse(sistema.inserireNuovoVolo( "id_volo",  "id_aereo_2", "Milano", "Roma",
				dataOra, 1.1f, 80.0f, 380));
		
		//Controllo3 : se il gestore è loggato e l'aereo per il volo esiste allora
		// inserireNuovoAereo va a buon fine e..
		int dim = sistema.get_mappa().get("mario.rossi@gmail.com").get_listaVoli().size();
		assertTrue(sistema.inserireNuovoVolo( "id_volo",  "id_aereo", "Milano", "Roma",
				dataOra, 1.1f, 80.0f, 380));
		// .. la lista voli deve essere aumentata di dimensione e ..
		assertEquals(dim+1, sistema.get_mappa().get("mario.rossi@gmail.com").get_listaVoli().size());
		// .. il volo aggiunto è stato inserito correttamente nella listaVoli della compagnia del gestore
		Volo volo = sistema.ricercareVolo("id_volo");
		assertNotNull(volo);
		assertEquals("id_aereo", volo.get_aereo().get_idAereo());
		assertEquals("Milano", volo.get_luogoPartenza());
		assertEquals("Roma", volo.get_luogoDestinazione());
		assertEquals(dataOra, volo.get_dataOraPartenza());
		assertEquals(1.1f,volo.get_durata(), 0.01f);
		assertEquals(80.0f, volo.get_prezzoBiglietto(), 0.01f);
		assertEquals(380,volo.get_miglia());
		
		//Controllo4 : se si vuole inserire un nuovo volo con un idVolo già esistente allora 
		// inserireNuovoVolo non va a buon fine 
		assertFalse(sistema.inserireNuovoVolo("id_volo", "id_aereo", "Roma", "Buenos Aires",
				dataOra, 14.15f, 1000.0f, 6930));
		
		//Controllo5 : se si vuole inserire un nuovo volo con parametri nulli/0.0f/0  allora 
		// inserireNuovoVolo non va a buon fine
		assertFalse(sistema.inserireNuovoVolo(null, "id_aereo", "Roma", "Buenos Aires",
				dataOra, 14.15f, 1000.0f, 6930));
		assertFalse(sistema.inserireNuovoVolo("id_volo", null, "Roma", "Buenos Aires",
				dataOra, 14.15f, 1000.0f, 6930));
		assertFalse(sistema.inserireNuovoVolo("id_volo", "id_aereo", null, "Buenos Aires",
				dataOra, 14.15f, 1000.0f, 6930));
		assertFalse(sistema.inserireNuovoVolo("id_volo", "id_aereo", "Roma", null,
				dataOra, 14.15f, 1000.0f, 6930));
		assertFalse(sistema.inserireNuovoVolo("id_volo", "id_aereo", "Roma", "Buenos Aires",
				null, 14.15f, 1000.0f, 6930));
		assertFalse(sistema.inserireNuovoVolo("id_volo", "id_aereo", "Roma", "Buenos Aires",
				dataOra, 0.0f, 1000.0f, 6930));
		assertFalse(sistema.inserireNuovoVolo("id_volo", "id_aereo", "Roma", "Buenos Aires",
				dataOra, 14.15f, 0.0f, 6930));
		assertFalse(sistema.inserireNuovoVolo("id_volo", "id_aereo", "Roma", "Buenos Aires",
				dataOra, 14.15f, 1000.0f, 0));
	}

	@Test
	public void testRicercareVolo() {
		//Controllo1 : se il gestore non è loggato la ricerca torna null
		assertNull(sistema.ricercareVolo("id_volo"));
		
		//Controllo2 : se il gestore è loggato ma l'idVolo è sbagliato la ricerca non va a buon fine
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		sistema.login("mario.rossi@gmail.com", "psw");
		Calendar dataOra = Calendar.getInstance();
		dataOra.set(2018, 4, 1, 7, 30 );
		assertTrue(sistema.inserireNuovoAereo("id_aereo", "jet", 156));
		assertTrue(sistema.inserireNuovoVolo( "id_volo", "id_aereo", "Milano", "Roma", dataOra, 1.1f, 80.0f, 380));
		assertNull(sistema.ricercareVolo("idvolo1"));
		
		//Controllo3 : se il gestore è loggato e l'idVolo esiste allora la ricerca torna il volo
		Volo volo = sistema.ricercareVolo("id_volo");
		assertNotNull(volo);
		assertEquals("id_aereo", volo.get_aereo().get_idAereo());
		assertEquals("Milano", volo.get_luogoPartenza());
		assertEquals("Roma", volo.get_luogoDestinazione());
		assertEquals(dataOra, volo.get_dataOraPartenza());
		assertEquals(1.1f,volo.get_durata(), 0.01f);
		assertEquals(80.0f, volo.get_prezzoBiglietto(), 0.01f);
		assertEquals(380,volo.get_miglia());
	}

	@Test
	public void testModificareVolo() {
		//Controllo1 : se il gestore non è loggato allora non si può modificare un volo
		Calendar dataOra = Calendar.getInstance();
		dataOra.set(2018, 4, 1, 7, 30 );
		assertFalse(sistema.modificareVolo("id_volo", "id_aereo", "Roma", "Buenos Aires",dataOra, 14.15f, 1000.0f, 6930));
		
		//Controllo2 : se il gestore è loggato ma non esiste il volo allora modificareVolo
		// non va a buon fine
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		assertTrue(sistema.login("mario.rossi@gmail.com", "psw"));
		assertFalse(sistema.modificareVolo("id_volo", "id_aereo", "Roma", "Buenos Aires",
				 dataOra, 14.15f, 1000.0f, 6930));
		
		//Controllo3 : se il gestore è loggato, idAereo è diverso da null ma non esiste in listaAerei
		// allora modificareVolo non va buon fine
		sistema.inserireNuovoAereo("id_aereo", "Boeing777", 300);
		sistema.inserireNuovoVolo( "id_volo_1",  "id_aereo", "Milano", "Roma", dataOra, 1.1f, 80.0f, 380);
		assertFalse(sistema.modificareVolo("id_volo_1", "fvgb", "Roma", "Buenos Aires", dataOra, 14.15f, 1000.0f, 6930));
		
		
		//Controllo4 : se il gestore è loggato, il volo esiste e tutti i parametri sono diversi da null/0/0.0f 
		// allora modificareVolo va buon fine
		sistema.inserireNuovoVolo( "id_volo",  "id_aereo", "Milano", "Roma", dataOra, 1.1f, 80.0f, 380);
		sistema.inserireNuovoAereo("id_aereo_1", "jet", 156);
		dataOra.set(2018, 3, 23, 17, 45);
		assertTrue(sistema.modificareVolo("id_volo", "id_aereo_1", "Roma", "Buenos Aires", dataOra, 14.15f, 1000.0f, 6930));
		
		//Controllo5 : se il gestore è loggato, il volo esiste e tutti i parametri sono null/0/0.0f 
		//tranne idAereo allora modificareVolo va buon fine
		assertTrue(sistema.modificareVolo("id_volo", "id_aereo_1", null, null, null, 0.0f, 0.0f, 0));
		
		//Controllo6 : se il gestore è loggato, il volo esiste e tutti i parametri sono null/0/0.0f 
		// tranne luogoPartenza allora modificareVolo va buon fine
		assertTrue(sistema.modificareVolo("id_volo", null, "Pisa" , null, null, 0.0f, 0.0f, 0));
		
		//Controllo7 : se il gestore è loggato, il volo esiste e tutti i parametri sono null/0/0.0f 
		// tranne luogoDestinazione allora modificareVolo va buon fine
		assertTrue(sistema.modificareVolo("id_volo", null, null , "Londra", null, 0.0f, 0.0f, 0));
		
		//Controllo8 : se il gestore è loggato, il volo esiste e tutti i parametri sono null/0/0.0f
		// tranne dataOra allora modificareVolo va buon fine
		dataOra.set(2018, 7, 21, 15, 10);
		assertTrue(sistema.modificareVolo("id_volo", null, null , null, dataOra, 0.0f, 0.0f, 0));
		
		//Controllo9 : se il gestore è loggato, il volo esiste e tutti i parametri sono null/0/0.0f 
		// tranne durata allora modificareVolo va buon fine
		assertTrue(sistema.modificareVolo("id_volo", null, null , null, null, 2.15f, 0.0f, 0));
		
		//Controllo10 : se il gestore è loggato, il volo esiste e tutti i parametri sono null/0/0.0f 
		// tranne prezzoBiglietto allora modificareVolo va buon fine
		assertTrue(sistema.modificareVolo("id_volo", null, null , null, null, 0.0f, 55.0f, 0));
		
		//Controllo11 : se il gestore è loggato, il volo esiste e tutti i parametri sono null/0/0.0f 
		// tranne miglia allora modificareVolo va buon fine
		assertTrue(sistema.modificareVolo("id_volo", null, null , "Londra", null, 0.0f, 0.0f, 700));
	}

	@Test
	public void testCancellareVolo() {
		//Controllo1 : se il getore non è loggato allora non si può cancellare un volo
		assertFalse(sistema.cancellareVolo("id_volo"));
		
		//Controllo2 : se il gestore è loggato ma l'idVolo non esiste allora cancellareVolo non va a buon fine
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		assertTrue(sistema.login("mario.rossi@gmail.com", "psw"));
		assertFalse(sistema.cancellareVolo("id_volo"));
		
		//Controllo3 : se il gestore è loggato e esiste il volo con idVolo allora cancellareVolo
		// va a buon fine e..
		sistema.inserireNuovoAereo("id_aereo", "jet", 156);
		Calendar dataOra = Calendar.getInstance();
		dataOra.set(2018, 4, 1, 7, 30 );
		sistema.inserireNuovoVolo( "id_volo", "id_aereo", "Milano", "Roma", dataOra, 1.1f, 80.0f, 380);
		int dim = sistema.get_mappa().get("mario.rossi@gmail.com").get_listaVoli().size();
		assertTrue(sistema.cancellareVolo("id_volo"));
		// .. la lista voli deve essere diminuita di dimensione
		assertEquals(dim-1, sistema.get_mappa().get("mario.rossi@gmail.com").get_listaVoli().size());
		// .. e l'aereo corrispondente non deve essere eliminato
		assertNull(sistema.ricercareVolo("id_volo"));
		assertNotNull(sistema.ricercareAereo("id_aereo"));
		

	}

	@Test
	public void testInserireNuovoAbbonamento() {
		//Controllo1 : se il gestore non è loggato allora non si può aggiungere un abbonamento
		assertFalse(sistema.inserireNuovoAbbonamento("id_abbonamento", 350.5f, 12000));

		//Controllo2 : se il gestore è loggato allora inserireNuovoAbbonamento va a buon fine e ..
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		assertTrue(sistema.login("mario.rossi@gmail.com", "psw"));
		int dim = sistema.get_mappa().get("mario.rossi@gmail.com").get_listaAbbonamenti().size();
		assertTrue(sistema.inserireNuovoAbbonamento("id_abbonamento", 350.5f, 12000));
		// .. la lista abbonamenti deve essere aumentata di dimensione e..
		assertEquals(dim+1, sistema.get_mappa().get("mario.rossi@gmail.com").get_listaAbbonamenti().size());
		//.. l'abbonamento aggiunto è stato inserito correttamente nella listaAbbonamenti della 
		// compagnia del gestore
		Abbonamento abbonamento = sistema.ricercareAbbonamento("id_abbonamento");
		assertNotNull(abbonamento);
		assertEquals(350.5f, abbonamento.get_costo(), 0.01f);
		assertEquals(12000, abbonamento.get_miglia());
		
		//Controllo3 : se il gestore è loggato ma idAbbonamento è già esistente
		// allora inserireNuovoAbbonamento non va a buon fine 
		assertFalse(sistema.inserireNuovoAbbonamento("id_abbonamento", 500.0f, 27000));
		
		//Controllo4 :se il gestore è loggato ma alcuni parametri sono nulli/0.0f/0  allora 
		// inserireNuovoAbbonamento non va a buon fine
		assertFalse(sistema.inserireNuovoAbbonamento(null, 500.0f, 27000));
		assertFalse(sistema.inserireNuovoAbbonamento("id_abbonamento", 0.0f, 27000));
		assertFalse(sistema.inserireNuovoAbbonamento("id_abbonamento", 500.0f, 0));
	}
	
	//*******DA QUIIIIIIIIII!!!!!!!!!!!!!!!!!!!!!!!**************************************
	@Test
	public void testRicercareAbbonamento() {
		//Controllo1 : se il gestore non è loggato la ricerca torna null
		assertNull(sistema.ricercareAbbonamento("id_abbonamento"));
		
		//Controllo2 : se il gestore è loggato ma l'idAbbonamento è inesistente la ricerca non va a buon fine
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		sistema.login("mario.rossi@gmail.com", "psw");
		sistema.inserireNuovoAbbonamento("id_abbonamento", 350.5f, 12000);
		assertNull(sistema.ricercareAbbonamento("idAbb"));
		
		//Controllo3 : se il gestore è loggato e l'idAbbonamento esiste allora la ricerca va a buon fine
		Abbonamento abbonamento = sistema.ricercareAbbonamento("id_abbonamento");
		assertNotNull(abbonamento);
		assertEquals(350.5f, abbonamento.get_costo(), 0.01f);
		assertEquals(12000, abbonamento.get_miglia());
	}

	@Test
	public void testCancellaAbbonamentoo() {
		//Controllo1 : se il getore non è loggato allora non può cancellare un abbonamento
		assertFalse(sistema.cancellareAbbonamento("id_abbonamento"));
		
		//Controllo2 : se il gestore è loggato ma l'idAbbonamento non esiste allora 
		// cancellareAbbonamento non va a buon fine
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		assertTrue(sistema.login("mario.rossi@gmail.com", "psw"));
		assertFalse(sistema.cancellareAbbonamento("idAbb"));
		
		//Controllo3 : se il gestore è loggato e esiste l'abbonamento con idAbbonamento allora 
		//cancellareAbbonamento va a buon fine e..
		sistema.inserireNuovoAbbonamento("id_abbonamento", 500.0f, 27000);
		int dim = sistema.get_mappa().get("mario.rossi@gmail.com").get_listaAbbonamenti().size();
		assertTrue(sistema.cancellareAbbonamento("id_abbonamento"));
		assertNull(sistema.ricercareAbbonamento("id_abbonamento"));
		// .. la dimensione della listaAbbonamenti deve essere diminuita
		assertEquals(dim-1, sistema.get_mappa().get("mario.rossi@gmail.com").get_listaAbbonamenti().size());
	}

	@Test
	public void testLogin() {
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "psw", "Alitalia");
		sistema.aggiungi_gestore("Giuseppe", "Bianchi", "giuseppe.bianchi@gmail.com", "pass", "Emirates");
		
		//Controllo1 : se la password è sbagliata allora login non va a buon fine
		assertFalse(sistema.login("mario.rossi@gmail.com", "pw"));
		
		//Controllo2: se la mail è sbagliata allora login non va a buon fine
		assertFalse(sistema.login("rossi@gmail.com", "psw"));
		
		//Controllo3 : se mail e password sono corrette allora login va a buon fine e..
		assertTrue(sistema.login("giuseppe.bianchi@gmail.com", "pass"));
		// .. l'attributo "gestore" di sistema è stato settato correttamente
		assertEquals("Giuseppe", sistema.get_gestore().get_nome());
		assertEquals("Bianchi", sistema.get_gestore().get_cognome());
		assertEquals("giuseppe.bianchi@gmail.com", sistema.get_gestore().get_mail());
		assertEquals("pass", sistema.get_gestore().get_password());
		assertTrue(sistema.get_gestore().get_login());
		assertTrue(sistema.get_mappaGestori().get("giuseppe.bianchi@gmail.com").get_login());	
	}

	@Test
	public void testLogout() {
		sistema.aggiungi_gestore("Giuseppe", "Bianchi", "giuseppe.bianchi@gmail.com", "pass", "Emirates");
		
		//Controllo1 : se il gestore non ha effettuato il login, allora il logout non va a buon fine
		assertFalse(sistema.logout());
		
		//Controllo2 : se il gestore ha effettuo il login allora il logout va a buon fine
		sistema.login("giuseppe.bianchi@gmail.com", "pass");
		assertTrue(sistema.logout());
		assertFalse(sistema.get_mappaGestori().get("giuseppe.bianchi@gmail.com").get_login());
		assertNull(sistema.get_gestore().get_nome());
		assertNull(sistema.get_gestore().get_cognome());
		assertNull(sistema.get_gestore().get_mail());
		assertNull(sistema.get_gestore().get_password());
		assertFalse(sistema.get_gestore().get_login());
	}

}
