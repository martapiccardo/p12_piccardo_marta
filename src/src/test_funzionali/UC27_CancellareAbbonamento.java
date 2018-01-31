package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Abbonamento;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;

public class UC27_CancellareAbbonamento {

	Sistema sistema;
	Abbonamento abbonamento;
	boolean annulla;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : gestore che vuole cancellare un abbonamento deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Mario", "Rossi", "mario.rossi@gmail.com", "mr125", "Alitalia");
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
		Abbonamento abbonamento1 = new Abbonamento("id_abbonamento_1", 350.5f, 12000);
		Abbonamento abbonamento2 = new Abbonamento("id_abbonamento_2", 500.0f, 27000);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAbbonamenti().add(abbonamento1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAbbonamenti().add(abbonamento2);
	}

	//NB : Non essendoci interfaccia grafica il volere del gestore di annullare la cancellazione dell'abbonamento
	// viene simulato attraverso un boolean "annulla"(true se ha confermato di voler annullare la
	// cancellazione, false altrimenti)
	
	//Cancellazione abbonamento : riuscita									(scenario principale)
	@Test
	public void UC27test1() {
		annulla = false;
		if(!annulla)
			assertTrue(sistema.cancellareAbbonamento("id_abbonamento_2"));
	}

	//Cancellazione abbonamento : fallita per idAbbonamento non esistente	(scenario alternativo 4a)
	@Test
	public void UC27test2() {
		assertFalse(sistema.cancellareAbbonamento("id_abbonamento_24"));
	}
	
	//Cancellazione abbonamento : annullata per volere del gestore			(scenario alternativo 6a)
	@Test
	public void UC27test3(){
		annulla = true;
		if(annulla)
			assertTrue(sistema.cancellareAbbonamento("id_abbonamento_2"));
	}
}
