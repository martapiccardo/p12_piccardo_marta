package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Aereo;

public class AereoTest {

	Aereo aereo1;
	Aereo aereo2;
	
	@Before
	public void setUp() throws Exception {
		aereo1 = new Aereo();
		aereo2 = new Aereo("id_aereo_2", "jet", 156);
	}

	//Costruttore senza parametri
	@Test
	public void testAereo() {
		assertNull(aereo1.get_idAereo());
		assertNull(aereo1.get_tipologia());
		assertEquals(0, aereo1.get_numeroPosti());
	}

	//Costruttore con parametri idAereo, tipologia, numeroPosti
	@Test
	public void testAereoStringStringInt() {
		assertNotNull(aereo2.get_idAereo());
		assertEquals("id_aereo_2", aereo2.get_idAereo());
		assertNotNull(aereo2.get_tipologia());
		assertEquals("jet", aereo2.get_tipologia());
		assertEquals(156, aereo2.get_numeroPosti());
	}

	@Test
	public void testSetGet_idAereo() {
		aereo1.set_idAereo("id_aereo_1");
		assertEquals("id_aereo_1", aereo1.get_idAereo());
	}

	@Test
	public void testSetGet_tipologia() {
		aereo1.set_tipologia("Boeing777");
		assertEquals("Boeing777", aereo1.get_tipologia());
	}

	
	@Test
	public void testSetGet_numeroPosti() {
		aereo1.set_numeroPosti(300);
		assertEquals(300, aereo1.get_numeroPosti());
	}

}
