package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Abbonamento;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;

public class UC25_InserireNuovoAbbonamento {

	Sistema sistema;
	Abbonamento abbonamento;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : gestore che vuole inserire un nuovo abbonamento deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "mr125", "Alitalia");
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
		abbonamento = new Abbonamento("id_abbonamento_1", 350.5f, 12000);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAbbonamenti().add(abbonamento);
		
	}

	//Inserimento nuovo abbonamento : riuscito									(scenario principale)
	@Test
	public void UC25test1() {
		assertTrue(sistema.inserireNuovoAbbonamento("id_abbonamento_2", 500.0f, 27000));
	}

	//Inserimento nuovo abbonamento : fallito per idAbbonamento già esistente	(scenario alternativo 4a)
	@Test
	public void UC25test2() {
		assertFalse(sistema.inserireNuovoAbbonamento("id_abbonamento_1", 500.0f, 27000));
	}
	
	//Inserimento nuovo abbonamento : fallito per campi nulli/0.0f/0			(scenario alternativo 4b)
	@Test
	public void UC25test3() {
		assertFalse(sistema.inserireNuovoAbbonamento(null, 500.0f, 27000));
		assertFalse(sistema.inserireNuovoAbbonamento("id_abbonamento_1", 0.0f, 27000));
		assertFalse(sistema.inserireNuovoAbbonamento("id_abbonamento_1", 500.0f, 0));
		assertFalse(sistema.inserireNuovoAbbonamento(null, 0.0f, 0));
	}
}
