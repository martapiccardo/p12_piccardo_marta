package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;

public class UC18_InserireNuovoAereo {

	Sistema sistema;
	Aereo aereo;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : utente che vuole inserire un nuovo aereo deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "mr125", "Alitalia");
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
		aereo = new Aereo("id_aereo_1", "jet", 156);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo);
	}

	//Inserimento nuovo aereo : riuscito							(scenario principale)
	@Test
	public void UC18test1() {
		assertTrue(sistema.inserireNuovoAereo("id_aereo_2", "Boeing777", 382));
	}
	
	//Inserimento nuovo aereo : fallito per idAereo già esistente	(scenario alternativo 4a)
	@Test
	public void UC18test2() {
		assertFalse(sistema.inserireNuovoAereo("id_aereo_1", "Boeing777", 382));
	}
	
	//Inserimento nuovo aereo : fallito per campi nulli/0			(scenario alternativo 4b)
	@Test
	public void UC18test3() {
		assertFalse(sistema.inserireNuovoAereo(null, "Boeing777", 382));
		assertFalse(sistema.inserireNuovoAereo("id_aereo_3", null, 382));
		assertFalse(sistema.inserireNuovoAereo("id_aereo_3", "Boeing777", 0));
		assertFalse(sistema.inserireNuovoAereo(null, null, 0));
	}
}
