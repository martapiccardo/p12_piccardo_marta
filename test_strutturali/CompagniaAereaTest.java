package test_strutturali;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Abbonamento;
import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.CompagniaAerea;
import p12_sistema_prenotazione_voli_ManagerApp.Volo;

public class CompagniaAereaTest {

	CompagniaAerea compagnia;
	
	@Before
	public void setUp() throws Exception {
		compagnia = new CompagniaAerea();
	}

	//Costruttore
	@Test
	public void testCompagniaAerea() {
		assertNotNull(compagnia);
		assertNull(compagnia.get_nomeCompagniaAerea());
		assertTrue(compagnia.get_listaAerei().isEmpty());
		assertTrue(compagnia.get_listaAbbonamenti().isEmpty());
		assertTrue(compagnia.get_listaVoli().isEmpty());
	}

	@Test
	public void testSetGet_nomeCompagniaAerea() {
		compagnia.set_nomeCompagniaAerea("Alitalia");
		assertEquals("Alitalia", compagnia.get_nomeCompagniaAerea());
	}

	@Test
	public void testSetGet_listaAerei() {
		Aereo aereo1 = new Aereo("id_aereo_1", "Boeing777", 382);
		Aereo aereo2 = new Aereo("id_aereo_2", "jet", 156);
		ArrayList<Aereo> lista_aerei = new ArrayList<Aereo>();
		lista_aerei.add(aereo1);
		lista_aerei.add(aereo2);
		compagnia.set_listaAerei(lista_aerei);
		assertEquals(aereo1, compagnia.get_listaAerei().get(0));
		assertEquals(aereo2, compagnia.get_listaAerei().get(1));
		assertEquals(2, compagnia.get_listaAerei().size());
		assertEquals(lista_aerei, compagnia.get_listaAerei());
	}

	@Test
	public void testSetGet_listaAbbonamenti() {
		Abbonamento abbonamento1 = new Abbonamento("id_1", 350.5f, 12000);
		Abbonamento abbonamento2 = new Abbonamento("id_2", 500.0f, 20000);
		ArrayList<Abbonamento> lista_abbonamenti = new ArrayList<Abbonamento>();
		lista_abbonamenti.add(abbonamento1);
		lista_abbonamenti.add(abbonamento2);
		compagnia.set_listaAbbonamenti(lista_abbonamenti);
		assertEquals(abbonamento1, compagnia.get_listaAbbonamenti().get(0));
		assertEquals(abbonamento2, compagnia.get_listaAbbonamenti().get(1));
		assertEquals(2, compagnia.get_listaAbbonamenti().size());
		assertEquals(lista_abbonamenti, compagnia.get_listaAbbonamenti());
	}

	@Test
	public void testSetGet_listaVoli() {
		Aereo aereo1 = new Aereo("id_aereo_1", "Boeing777", 382);
		Aereo aereo2 = new Aereo("id_aereo_2", "jet", 156);
		Calendar dataOra1 = Calendar.getInstance();
		dataOra1.set(2018, 4, 1, 7, 30 );
		Calendar dataOra2 = Calendar.getInstance();
		dataOra2.set(2018, 3, 7, 21, 45 );
		Volo volo1 = new Volo("id_volo_1", aereo1, "Milano", "Roma", dataOra1, 1.1f, 80.0f, 380);
		Volo volo2 = new Volo("id_volo_2", aereo2, "Roma", "Buenos Aires", dataOra2, 14.15f, 1000.0f, 6930);
		ArrayList<Volo> lista_voli = new ArrayList<Volo>();
		lista_voli.add(volo1);
		lista_voli.add(volo2);
		compagnia.set_listaVoli(lista_voli);
		assertEquals(volo1, compagnia.get_listaVoli().get(0));
		assertEquals(volo2, compagnia.get_listaVoli().get(1));
		assertEquals(2, compagnia.get_listaVoli().size());
		assertEquals(lista_voli, compagnia.get_listaVoli());
	}
	
	@Test
	public void testPrit_listaAerei() {
		compagnia.set_nomeCompagniaAerea("Alitalia");
		Aereo aereo1 = new Aereo("id_aereo_1", "Boeing777", 382);
		Aereo aereo2 = new Aereo("id_aereo_2", "jet", 156);
		ArrayList<Aereo> lista_aerei = new ArrayList<Aereo>();
		lista_aerei.add(aereo1);
		lista_aerei.add(aereo2);
		compagnia.set_listaAerei(lista_aerei);
		compagnia.print_listaAerei();
	}
	
	@Test
	public void testPrit_listaVoli() {
		compagnia.set_nomeCompagniaAerea("Alitalia");
		Aereo aereo1 = new Aereo("id_aereo_1", "Boeing777", 382);
		Aereo aereo2 = new Aereo("id_aereo_2", "jet", 156);
		ArrayList<Aereo> lista_aerei = new ArrayList<Aereo>();
		lista_aerei.add(aereo1);
		lista_aerei.add(aereo2);
		compagnia.set_listaAerei(lista_aerei);
		Calendar dataOra1 = Calendar.getInstance();
		dataOra1.set(2018, 4, 1, 7, 30 );
		Calendar dataOra2 = Calendar.getInstance();
		dataOra2.set(2018, 3, 7, 21, 45 );
		Volo volo1 = new Volo("id_volo_1", aereo1, "Milano", "Roma", dataOra1, 1.1f, 80.0f, 380);
		Volo volo2 = new Volo("id_volo_2", aereo2, "Roma", "Buenos Aires", dataOra2, 14.15f, 1000.0f, 6930);
		ArrayList<Volo> lista_voli = new ArrayList<Volo>();
		lista_voli.add(volo1);
		lista_voli.add(volo2);
		compagnia.set_listaVoli(lista_voli);
		compagnia.print_listaVoli();
	}
	
	@Test
	public void testPrit_listaAbbonamenti() {
		compagnia.set_nomeCompagniaAerea("Alitalia");
		Abbonamento abbonamento1 = new Abbonamento("id_1", 350.5f, 12000);
		Abbonamento abbonamento2 = new Abbonamento("id_2", 500.0f, 20000);
		ArrayList<Abbonamento> lista_abbonamenti = new ArrayList<Abbonamento>();
		lista_abbonamenti.add(abbonamento1);
		lista_abbonamenti.add(abbonamento2);
		compagnia.set_listaAbbonamenti(lista_abbonamenti);
		compagnia.print_listaAbbonamenti();
		
	}
}
