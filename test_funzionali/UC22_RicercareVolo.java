package test_funzionali;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;
import p12_sistema_prenotazione_voli_ManagerApp.Volo;

public class UC22_RicercareVolo {
	
	Sistema sistema;
	Calendar dataOra1;
	Calendar dataOra2;

	@Before
	public void setUp() throws Exception {
		//Precondizioni : utente che vuole ricercare un volo deve essere loggato nel sistema
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
		Volo volo2 = new Volo();
		volo2.set_idVolo("id_volo_2");
		volo2.set_aereo(aereo2);
		volo2.set_luogoPartenza("Roma");
		volo2.set_luogoDestinazione("Londra");
		volo2.set_dataOraPartenza(dataOra2);
		volo2.set_durata(3.15f);
		volo2.set_prezzoBiglietto(65.0f);
		volo2.set_miglia(700);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaVoli().add(volo1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaVoli().add(volo2);
	}

	//Ricerca volo : riuscita								(scenario principale)
	@Test
	public void UC22test1() {
		Volo volo = sistema.ricercareVolo("id_volo_2");
		assertNotNull(volo);
		assertEquals("id_aereo_2", volo.get_aereo().get_idAereo());
		assertEquals("Roma", volo.get_luogoPartenza());
		assertEquals("Londra", volo.get_luogoDestinazione());
		assertEquals(0, volo.get_dataOraPartenza().compareTo(dataOra2));
		assertEquals(3.15f, volo.get_durata(), 0.01f);
		assertEquals(65.0f, volo.get_prezzoBiglietto(), 0.01f);
		assertEquals(700, volo.get_miglia());
		
	}
	
	//Ricerca volo : fallita per idVolo inesistente			(scenario alternativo 4a)
	@Test
	public void UC22test2() {
		Volo volo = sistema.ricercareVolo("id_volo_99");
		assertNull(volo);
		
	}	

}
