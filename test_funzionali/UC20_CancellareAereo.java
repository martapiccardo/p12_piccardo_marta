package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;

public class UC20_CancellareAereo {

	Sistema sistema;
	boolean annulla;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : gestore che vuole cancellare un aereo deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
		Aereo aereo1 = new Aereo("id_aereo_1", "jet", 156);
		Aereo aereo2 = new Aereo("id_aereo_2", "Boeing777", 382);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo2);
	}
	
	//NB : Non essendoci interfaccia grafica il volere del gestore di annullare la cancellazione dell'aereo
	// viene simulato attraverso un boolean "annulla"(true se ha confermato di voler annullare la
	// cancellazione, false altrimenti)

	//Cancellazione aereo : riuscita							(scenario principale)
	@Test
	public void UC20test1() {
		annulla = false;
		if(!annulla)
			assertTrue(sistema.cancellareAereo("id_aereo_1"));
	}

	//Cancellazione aereo : fallita per idAereo inesistente		(scenario alternativo 4a)
	@Test
	public void UC20test2(){
		assertFalse(sistema.cancellareAereo("id_aereo_45"));
	}
	
	//Cancellazione aereo : annullata per volere del gestore	(scenario alternativo 6a)
	@Test
	public void UC20test3(){
		annulla = true;
		if(annulla){
			assertNotNull(sistema.ricercareAereo("id_aereo_1"));
		}
	}


}
