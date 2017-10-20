package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import util.InvariantBroken;

public class TestAudio {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetStat() throws OperationImpossible, InvariantBroken {
		assertEquals("NOK default nbEmpruntsTotal", 0, Audio.getStat());
	}
	
	@Test 
	public void testAudio() throws OperationImpossible, InvariantBroken {
		Audio audio = new Audio("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), "classification");
		assertTrue("NOK constructor code", audio.getCode().equals("code"));
		assertTrue("NOK constructor localisation", audio.getLocalisation().equals(new Localisation("salle", "rayon")));
		assertTrue("NOK constructor titre", audio.getTitre().equals("titre"));
		assertTrue("NOK constructor auteur", audio.getAuteur().equals("auteur"));
		assertTrue("NOK constructor annee", audio.getAnnee().equals("annee"));
		assertTrue("NOK constructor genre", audio.getGenre().equals(new Genre("genre")));
		assertTrue("NOK constructor classification", audio.getClassification().equals("classification"));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testAudioImpossible() throws OperationImpossible, InvariantBroken {
		@SuppressWarnings("unused")
		Audio audio = new Audio("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), null);
	}

	@Test
	public void testEmprunter() throws OperationImpossible, InvariantBroken {
		Audio audio = new Audio("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), "classification");
		assertEquals("NOK default nbEmpruntsTotal", 0, audio.getNbEmprunts());
		audio.metEmpruntable();
		audio.emprunter();
	}

	@Test
	public void testDureeEmprunt() throws OperationImpossible, InvariantBroken {
		Audio audio = new Audio("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), "classification");
		assertEquals("NOK dureeEmprunt", Audio.DUREE, audio.dureeEmprunt());
	}

	@Test
	public void testTarifEmprunt() throws OperationImpossible, InvariantBroken {
		Audio audio = new Audio("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), "classification");
		assertEquals("NOK tarifEmprunt", Audio.TARIF, audio.tarifEmprunt(), 0.001);
	}

}
