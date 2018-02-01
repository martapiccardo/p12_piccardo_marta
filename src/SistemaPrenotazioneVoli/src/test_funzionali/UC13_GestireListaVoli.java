package test_funzionali;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;
import p12_sistema_prenotazione_voli_ManagerApp.Volo;

public class UC13_GestireListaVoli {

	Sistema sistema;
	Calendar dataOra1;
	Calendar dataOra2;
	Calendar dataOra3;
	int scelta;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : gestore deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
		Aereo aereo1 = new Aereo("id_aereo_1", "jet", 156);
		Aereo aereo2 = new Aereo("id_aereo_2", "Boeing777", 382);
		Aereo aereo3 = new Aereo("id_aereo_3", "Airbus A300", 220);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo2);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo3);
		dataOra1 = Calendar.getInstance();
		dataOra1.set(2018, 4, 1, 7, 30 );
		Volo volo1 = new Volo("id_volo_1", aereo1, "Milano", "Roma", dataOra1, 1.1f, 80.0f, 380);
		dataOra2 = Calendar.getInstance();
		dataOra2.set(2018, 6, 21, 12, 45 );
		Volo volo2 = new Volo("id_volo_2", aereo2, "Roma", "Londra", dataOra2, 3.15f, 65.0f, 700);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaVoli().add(volo1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaVoli().add(volo2);
		
	}

	//NB : Non essendoci interfaccia grafica l'eventuale scelta del gestore circa quale operazione
	// eseguire viene simulata attraverso una variabile "scelta"(1 per inserire un nuovo volo, 2 per 
	// ricercare un volo, 3 per cancellare un volo)

	//Visualizzazione lista voli					(scenario principale)
	@Test
	public void UC13test1() {
		sistema.get_mappa().get("cverdi@gmail.com").print_listaVoli();
	}

	//Scelta : inserire un nuovo volo				(scenario alternativo 3a)
	@Test
	public void UC13test2() {
		scelta = 1;
		if(scelta == 1){
			dataOra3 = Calendar.getInstance();
			dataOra3.set(2018, 5, 12, 13, 40);
			assertTrue(sistema.inserireNuovoVolo("id_volo_3", "id_aereo_3", "Francoforte",
					"Johannesburg ", dataOra3, 1.3f, 120.0f, 1200));
		}		
	}
	
	//Scelta : ricercare un volo					(scenario alternativo 3b)
	@Test
	public void UC13test3() {
		scelta = 2;
		if(scelta == 2)
			assertNotNull(sistema.ricercareVolo("id_volo_1"));
	}
	
	//Scelta : cancellare un volo					(scenario alternativo 3c)
	@Test
	public void UC13test4() {
		scelta = 3;
		if(scelta == 3)
			assertTrue(sistema.cancellareVolo("id_volo_2"));
	}
}
