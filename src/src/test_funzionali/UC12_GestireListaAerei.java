package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;

public class UC12_GestireListaAerei {

	Sistema sistema;
	int scelta;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : gestore deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
		Aereo aereo1 = new Aereo("id_aereo_1", "jet", 156);
		Aereo aereo2 = new Aereo("id_aereo_2","Boeing777", 382);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo2);
	}
	
	//NB : Non essendoci interfaccia grafica l'eventuale scelta del gestore circa quale operazione
	// eseguire viene simulata attraverso una variabile "scelta"(1 per inserire un nuovo aereo, 2 per 
	// ricercare un aereo, 3 per cancellare un aereo)

	//Visualizzazione lista aerei					(scenario principale)
	@Test
	public void UC12test1() {
		sistema.get_mappa().get("cverdi@gmail.com").print_listaAerei();
	}

	//Scelta : inserire un nuovo aereo				(scenario alternativo 3a)
	@Test
	public void UC12test2() {
		scelta = 1;
		if(scelta == 1)
			assertTrue(sistema.inserireNuovoAereo("id_aereo_3", "Airbus A300", 220));
	}
	
	//Scelta : ricercare un aereo					(scenario alternativo 3b)
	@Test
	public void UC12test3() {
		scelta = 2;
		if(scelta == 2)
			assertNotNull(sistema.ricercareAereo("id_aereo_1"));
	}
	
	//Scelta : cancellare un aereo					(scenario alternativo 3c)
	@Test
	public void UC12test4() {
		scelta = 3;
		if(scelta == 3)
			assertTrue(sistema.cancellareAereo("id_aereo_2"));
	}
}
