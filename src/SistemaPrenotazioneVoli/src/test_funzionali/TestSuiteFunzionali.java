package test_funzionali;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	UC3_Autenticarsi.class,
	UC11_Deautenticarsi.class,
	UC12_GestireListaAerei.class,
	UC13_GestireListaVoli.class,
	UC14_GestireListaAbbonamenti.class,
	UC18_InserireNuovoAereo.class,
	UC19_RicercareAereo.class,
	UC20_CancellareAereo.class,
	UC21_InserireNuovoVolo.class,
	UC22_RicercareVolo.class,
	UC23_ModificareVolo.class,
	UC24_CancellareVolo.class,
	UC25_InserireNuovoAbbonamento.class,
	UC26_RicercareAbbonamento.class,
	UC27_CancellareAbbonamento.class,
	})

public class TestSuiteFunzionali {
}
