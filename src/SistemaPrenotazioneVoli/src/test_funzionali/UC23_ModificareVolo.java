package test_funzionali;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;
import p12_sistema_prenotazione_voli_ManagerApp.Volo;

public class UC23_ModificareVolo {
	
	Sistema sistema;
	Calendar dataOra;

	@Before
	public void setUp() throws Exception {
		//Precondizioni : utente che vuole modificare un volo deve essere loggato nel sistema
		sistema = new Sistema();
		sistema.aggiungi_gestore("Carlo", "Verdi", "cverdi@gmail.com", "psw", "Airfrance");
		sistema.login("cverdi@gmail.com", "psw");
		Aereo aereo1 = new Aereo("id_aereo_1", "jet", 156);
		Aereo aereo2 = new Aereo("id_aereo_2", "Boeing777", 382);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo1);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaAerei().add(aereo2);
		dataOra = Calendar.getInstance();
		dataOra.set(2018, 4, 1, 7, 30 );
		Volo volo1 = new Volo("id_volo_1", aereo1, "Milano", "Roma", dataOra, 1.1f, 80.0f, 380);
		sistema.get_mappa().get("cverdi@gmail.com").get_listaVoli().add(volo1);
	}

	//Modifica volo : riuscita									(scenario principale)
	@Test
	public void UC23test1() {
		dataOra.set(2018, 9, 11, 10, 30);
		assertTrue(sistema.modificareVolo("id_volo_1", "id_aereo_2", "Roma", "Londra", dataOra,
				3.15f, 65.0f, 700));
		Volo volo = sistema.ricercareVolo("id_volo_1");
		assertEquals("id_aereo_2", volo.get_aereo().get_idAereo());
		assertEquals("Roma", volo.get_luogoPartenza());
		assertEquals("Londra", volo.get_luogoDestinazione());
		assertEquals(0, volo.get_dataOraPartenza().compareTo(dataOra));
		assertEquals(3.15f, volo.get_durata(), 0.01f);
		assertEquals(65.0f, volo.get_prezzoBiglietto(), 0.01f);
		assertEquals(700, volo.get_miglia());
	}

	//Modifica volo : fallita per idVolo inesistente			(scenario alternativo 4a)
	@Test
	public void UC23test2() {
		assertFalse(sistema.modificareVolo("id_volo_19", "id_aereo_2", "Roma", "Londra", dataOra,
				3.15f, 65.0f, 700));
	}
	
	//Modifica volo : fallita per idAereo inesistente			(scenario alternativo 4b)
	@Test
	public void UC23test3() {
		assertFalse(sistema.modificareVolo("id_volo_1", "id_aereo_85", "Roma", "Londra", dataOra,
				3.15f, 65.0f, 700));
	}
}
