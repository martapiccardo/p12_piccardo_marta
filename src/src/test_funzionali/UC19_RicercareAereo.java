package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;

public class UC19_RicercareAereo {

	Sistema sistema;
	Aereo aereo;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : gestore che vuole ricercare un aereo deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
		Aereo aereo1 = new Aereo("id_aereo_1", "jet", 156);
		Aereo aereo2 = new Aereo();
		aereo2.set_idAereo("id_aereo_2");
		aereo2.set_tipologia("Boeing777");
		aereo2.set_numeroPosti(382);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo2);
	}

	//Ricerca aereo : riuscita							(scenario principale)
	@Test
	public void UC19test1() {
		aereo = sistema.ricercareAereo("id_aereo_1");
		assertNotNull(aereo);
		assertEquals("jet", aereo.get_tipologia());
		assertEquals(156, aereo.get_numeroPosti());
	}
	
	//Ricerca aereo : fallita per idAereo inesistente	(scenario alternativo 4a)
	@Test
	public void UC19test2() {
		aereo = sistema.ricercareAereo("id_aereo_5");
		assertNull(aereo);
	}

}
