package test_strutturali;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.Volo;

public class VoloTest {

	Volo volo1;
	Volo volo2;
	Calendar dataOra;
	Calendar dataOra2;
	Aereo aereo;
	Aereo aereo2;
	
	@Before
	public void setUp() throws Exception {
		volo1 = new Volo();
		//set(int year, int month(0-11), int date(1-..), int hourOfDay(0-23), int minute(0-59))
		dataOra = Calendar.getInstance();
		dataOra.set(2018, 4, 1, 7, 30 );
		aereo = new Aereo("id_aereo", "jet", 156);
		volo2 = new Volo("id_volo_2", aereo, "Milano", "Roma", dataOra, 1.1f, 80.0f, 380);
	}

	@Test
	public void testVolo() {
		assertNotNull(volo1);
		assertNull(volo1.get_idVolo());
		assertNull(volo1.get_aereo());
		assertNull(volo1.get_luogoPartenza());
		assertNull(volo1.get_luogoDestinazione());
		assertNull(volo1.get_dataOraPartenza());
		assertEquals(0.0f, volo1.get_durata(), 0.01f); 
		assertEquals(0.0f, volo1.get_prezzoBiglietto(), 0.01f); 
		assertEquals(0, volo1.get_miglia());
	}

	//Costruttore con parametri idVolo, aereo, luogoPartenza, luogoDestinazione, dataOraPartenza,
	// durata, prezzoBiglietto, miglia
	@Test
	public void testVoloStringAereoStringStringCalendarFloatFloatInt() {
		assertNotNull(volo2);
		assertEquals("id_volo_2", volo2.get_idVolo());
		assertEquals(aereo, volo2.get_aereo());
		assertEquals("Milano", volo2.get_luogoPartenza());
		assertEquals("Roma", volo2.get_luogoDestinazione());
		assertEquals(dataOra, volo2.get_dataOraPartenza());
		assertEquals(1.1f, volo2.get_durata(), 0.01f); 
		assertEquals(80.0f, volo2.get_prezzoBiglietto(), 0.01f); 
		assertEquals(380, volo2.get_miglia());
	}

	@Test
	public void testSetGet_idVolo() {
		volo1.set_idVolo("id_volo_1");
		assertEquals("id_volo_1", volo1.get_idVolo());
	}

	@Test
	public void testSetGet_aereo() {
		aereo2 = aereo = new Aereo("id_aereo_2", "Boing777", 382);
		volo1.set_aereo(aereo2);
		assertEquals(aereo2, volo1.get_aereo());
	}

	@Test
	public void testSetGet_luogoPartenza() {
		volo1.set_luogoPartenza("Roma");
		assertEquals("Roma", volo1.get_luogoPartenza());
	}

	@Test
	public void testSetGet_luogoDestinazione() {
		volo1.set_luogoDestinazione("Buenos Aires");
		assertEquals("Buenos Aires", volo1.get_luogoDestinazione());
	}

	@Test
	public void testSetGet_dataOraPartenza() {
		dataOra2 = Calendar.getInstance();
		dataOra2.set(2018, 3, 7, 21, 45 );
		volo1.set_dataOraPartenza(dataOra2);
		assertEquals(dataOra2, volo1.get_dataOraPartenza());
	}

	@Test
	public void testSetGet_durata() {
		volo1.set_durata(14.15f);
		assertEquals(14.15f, volo1.get_durata(), 0.01f);
	}

	@Test
	public void testSetGet_prezzoBiglietto() {
		volo1.set_prezzoBiglietto(1000f);
		assertEquals(1000f, volo1.get_prezzoBiglietto(), 0.01f);
	}

	@Test
	public void testSetGet_miglia() {
		volo1.set_miglia(6930);
		assertEquals(6930, volo1.get_miglia());
	}

}
