package test_funzionali;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import p12_sistema_prenotazione_voli_ManagerApp.Aereo;
import p12_sistema_prenotazione_voli_ManagerApp.Sistema;
import p12_sistema_prenotazione_voli_ManagerApp.Volo;

public class UC21_InserireNuovoVolo {

	Sistema sistema;;
	Calendar dataOra1;
	Calendar dataOra2;
	
	@Before
	public void setUp() throws Exception {
		//Precondizioni : utente che vuole inserire un nuovo volo deve essere loggato nel sistema e
		// l’aereo (idAereo) che verrà selezionato per il volo deve essere stato già inserito nel sistema
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
		sistema.get_mappa().get("cverdi@gmail.com").get_listaVoli().add(volo1);
	}

	//Inserimento nuovo volo : riuscito								(scenario principale)
	@Test
	public void UC21test1() {
		dataOra2 = Calendar.getInstance();
		dataOra2.set(2018, 6, 21, 12, 45 );
		assertTrue(sistema.inserireNuovoVolo("id_volo_2","id_aereo_2", "Roma", "Londra", dataOra2,
				3.15f, 65.0f, 700));
	}

	//Inserimento nuovo volo : fallito per idVolo già presente		(scenario alternativo 4a)
	@Test
	public void UC21test2() {
		dataOra2 = Calendar.getInstance();
		dataOra2.set(2018, 6, 21, 12, 45 );
		assertFalse(sistema.inserireNuovoVolo("id_volo_1","id_aereo_2", "Roma", "Londra", dataOra2,
				3.15f, 65.0f, 700));
	}
	
	//Inserimento nuovo volo : fallito per idAereo inesistente		(scenario alternativo 4b)
	@Test
	public void UC21test3() {
		dataOra2 = Calendar.getInstance();
		dataOra2.set(2018, 6, 21, 12, 45 );
		assertFalse(sistema.inserireNuovoVolo("id_volo_1","id_aereo_8", "Roma", "Londra", dataOra2,
				3.15f, 65.0f, 700));
	}

	//Inserimento nuovo volo : fallito per campi nulli/0.0f/0		(scenario alternativo 4c)
	@Test
	public void UC21test4() {
		dataOra2 = Calendar.getInstance();
		dataOra2.set(2018, 6, 21, 12, 45 );
		assertFalse(sistema.inserireNuovoVolo(null,"id_aereo_2", "Roma", "Londra", dataOra2,
				3.15f, 65.0f, 700));
		assertFalse(sistema.inserireNuovoVolo("id_volo_1", null, "Roma", "Londra", dataOra2,
				3.15f, 65.0f, 700));
		assertFalse(sistema.inserireNuovoVolo("id_volo_1","id_aereo_2", null, "Londra", dataOra2,
				3.15f, 65.0f, 700));
		assertFalse(sistema.inserireNuovoVolo("id_volo_1","id_aereo_2", "Roma", null, dataOra2,
				3.15f, 65.0f, 700));
		assertFalse(sistema.inserireNuovoVolo("id_volo_1","id_aereo_2", "Roma", "Londra", null,
				3.15f, 65.0f, 700));
		assertFalse(sistema.inserireNuovoVolo("id_volo_1","id_aereo_2", "Roma", "Londra", dataOra2,
				0.0f, 65.0f, 700));
		assertFalse(sistema.inserireNuovoVolo("id_volo_1","id_aereo_2", "Roma", "Londra", dataOra2,
				3.15f, 0.0f, 700));
		assertFalse(sistema.inserireNuovoVolo("id_volo_1","id_aereo_2", "Roma", "Londra", dataOra2,
				3.15f, 65.0f, 0));
		assertFalse(sistema.inserireNuovoVolo(null, null, null, null, null, 0.0f, 0.0f, 0));
	}
}
