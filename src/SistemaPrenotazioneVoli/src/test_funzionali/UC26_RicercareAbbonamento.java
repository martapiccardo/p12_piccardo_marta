package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Abbonamento;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;

public class UC26_RicercareAbbonamento {

	Sistema sistema;
	Abbonamento abbonamento;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : gestore che vuole ricercare un abbonamento deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "mr125", "Alitalia");
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
		Abbonamento abbonamento1 = new Abbonamento("id_abbonamento_1", 350.5f, 12000);
		Abbonamento abbonamento2 = new Abbonamento();
		abbonamento2.set_idAbbonamento("id_abbonamento_2");
		abbonamento2.set_costo(500.0f);
		abbonamento2.set_miglia(27000);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAbbonamenti().add(abbonamento1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAbbonamenti().add(abbonamento2);
	}

	//Ricerca abbonamento : riuscita								(scenario principale)
	@Test
	public void UC26test1() {
		abbonamento = sistema.ricercareAbbonamento("id_abbonamento_1");
		assertNotNull(abbonamento);
		assertEquals(350.5f, abbonamento.get_costo(), 0.01f);
		assertEquals(12000, abbonamento.get_miglia());
	}
	
	//Ricerca abbonamento : fallita per idAbbonamento inesistente	(scenario alternativo 4a)
	@Test
	public void UC26test2() {
		abbonamento = sistema.ricercareAbbonamento("id_abbonamento_17");
		assertNull(abbonamento);
	}

}
