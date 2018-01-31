package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Abbonamento;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;

public class UC14_GestireListaAbbonamenti {

	Sistema sistema;
	int scelta;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : gestore deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "mr125", "Alitalia");
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
		Abbonamento abbonamento1 = new Abbonamento("id_abbonamento_1", 350.5f, 12000);
		Abbonamento abbonamento2 = new Abbonamento("id_abbonamento_2", 500.0f, 27000);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAbbonamenti().add(abbonamento1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAbbonamenti().add(abbonamento2);
	}

	//NB : Non essendoci interfaccia grafica l'eventuale scelta del gestore circa quale operazione
	// eseguire viene simulata attraverso una variabile "scelta"(1 per inserire un nuovo abbonamento, 2 per 
	// ricercare un abbonamento, 3 per cancellare un abbonamento)

	//Visualizzazione lista abbonamenti					(scenario principale)
	@Test
	public void UC14test1() {
		sistema.get_mappa().get("cverdi@gmail.com").print_listaAbbonamenti();
	}

	//Scelta : inserire un nuovo abbonamento			(scenario alternativo 3a)
	@Test
	public void UC14test2() {
		scelta = 1;
		if(scelta == 1)
			assertTrue(sistema.inserireNuovoAbbonamento("id_abbonamentoo_3", 750.0f, 50000));
	}
	
	//Scelta : ricercare un abbonamento					(scenario alternativo 3b)
	@Test
	public void UC14test3() {
		scelta = 2;
		if(scelta == 2)
			assertNotNull(sistema.ricercareAbbonamento("id_abbonamento_1"));
	}
	
	//Scelta : cancellare un abbonamento					(scenario alternativo 3c)
	@Test
	public void UC14test4() {
		scelta = 3;
		if(scelta == 3)
			assertTrue(sistema.cancellareAbbonamento("id_abbonamento_2"));
	}
}
