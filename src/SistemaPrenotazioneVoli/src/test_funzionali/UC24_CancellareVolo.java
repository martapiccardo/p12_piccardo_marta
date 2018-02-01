package test_funzionali;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;
import p12_sistema_prenotazione_voli_ManagerApp.Volo;

public class UC24_CancellareVolo {

	Sistema sistema;
	Calendar dataOra1;
	Calendar dataOra2;
	boolean annulla;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : gestore che vuole cancellare un volo deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
		Aereo aereo1 = new Aereo("id_aereo_1", "jet", 156);
		Aereo aereo2 = new Aereo("id_aereo_2", "Boeing777", 382);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo2);
		dataOra1 = Calendar.getInstance();
		dataOra1.set(2018, 4, 1, 7, 30 );
		Volo volo1 = new Volo("id_volo_1", aereo1, "Milano", "Roma", dataOra1, 1.1f, 80.0f, 380);
		dataOra2 = Calendar.getInstance();
		dataOra2.set(2018, 6, 21, 12, 45 );
		Volo volo2 = new Volo("id_volo_2", aereo2, "Roma", "Londra", dataOra2, 3.15f, 65.0f, 700);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaVoli().add(volo1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaVoli().add(volo2);
	}

	//NB : Non essendoci interfaccia grafica il volere del gestore di annullare la cancellazione del volo
	// viene simulato attraverso un boolean "annulla"(true se ha confermato di voler annullare la
	// cancellazione, false altrimenti)
	
	//Cancellazione volo : riuscita									(scenario principale)
	@Test
	public void UC24test1() {
		annulla = false;
		if(!annulla)
			assertTrue(sistema.cancellareVolo("id_volo_1"));
	}

	//Cancellazione volo : fallita per idVolo inesistente			(scenario alternativo 4a)
	@Test
	public void UC24test2() {
		assertFalse(sistema.cancellareVolo("id_volo_87"));
	}
	
	//Cancellazione volo : annullata per volere del gestore			(scenario alternativo 6a)
	@Test
	public void UC24test3(){
		annulla = true;
		if(annulla)
			assertNotNull(sistema.ricercareVolo("id_volo_1"));
	}
}
