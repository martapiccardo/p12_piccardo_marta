package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Abbonamento;

public class AbbonamentoTest {

	Abbonamento abbonamento1;
	Abbonamento abbonamento2;
	
	@Before
	public void setUp() throws Exception {
		abbonamento1 = new Abbonamento();
		abbonamento2 = new Abbonamento("id_2", 350.5f, 12000);
	}

	//Costruttore senza parametri
	@Test
	public void testAbbonamento() {
		assertNull(abbonamento1.get_idAbbonamento());
		assertEquals(0.0f,abbonamento1.get_costo(), 0.01f);
		assertEquals(0,abbonamento1.get_miglia());		
	}

	//Costruttore con parametri idAbbonamento, costo, miglia
	@Test
	public void testAbbonamentoStringFloatInt() {
		assertNotNull(abbonamento2.get_idAbbonamento());
		assertEquals("id_2", abbonamento2.get_idAbbonamento());
		assertEquals(350.5f, abbonamento2.get_costo(), 0.01f);
		assertEquals(12000,abbonamento2.get_miglia());
		
	}

	@Test
	public void testSetGet_idAbbonamento() {
		abbonamento1.set_idAbbonamento("id_1");
		assertEquals("id_1", abbonamento1.get_idAbbonamento());
	}

	@Test
	public void testSetGet_costo() {
		abbonamento1.set_costo(465.4f);
		assertEquals(465.4f, abbonamento1.get_costo(), 0.01f);
	}

	@Test
	public void testSetGet_miglia() {
		abbonamento1.set_miglia(35000);
		assertEquals(35000, abbonamento1.get_miglia());
	}

}
