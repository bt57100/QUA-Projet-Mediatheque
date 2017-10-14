package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import util.InvariantBroken;

public class TestLivre {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLivre() throws OperationImpossible, InvariantBroken {
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		assertTrue("NOK constructor code", livre.getCode().equals("code"));
		assertTrue("NOK constructor localisation", livre.getLocalisation().equals(new Localisation("salle", "rayon")));
		assertTrue("NOK constructor titre", livre.getTitre().equals("titre"));
		assertTrue("NOK constructor auteur", livre.getAuteur().equals("auteur"));
		assertTrue("NOK constructor annee", livre.getAnnee().equals("annee"));
		assertTrue("NOK constructor genre", livre.getGenre().equals(new Genre("genre")));
	}

	@Test(expected=OperationImpossible.class)
	public void testLivreImpossible1() throws OperationImpossible, InvariantBroken {
		@SuppressWarnings("unused")
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), -1);
	}

	@Test(expected=InvariantBroken.class)
	public void testLivreImpossible2() throws OperationImpossible, InvariantBroken {
		@SuppressWarnings("unused")
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 0);
	}

	/**
	 * False because
	 * nbEmpruntsTotal is not increment
	 * @throws OperationImpossible
	 * @throws InvariantBroken
	 */
	@Test
	public void testEmprunter() throws OperationImpossible, InvariantBroken {
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		livre.metEmpruntable();
		assertEquals("NOK emprunter", 0, Livre.getStat());
		assertTrue("NOK emprunter", livre.emprunter());
		assertEquals("NOK emprunter", 1, Livre.getStat());
	}

	@Test
	public void testDureeEmprunt() throws OperationImpossible, InvariantBroken {
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		assertEquals("NOK dureeEmprunt", Livre.DUREE, livre.dureeEmprunt());
	}

	@Test
	public void testTarifEmprunt() throws OperationImpossible, InvariantBroken {
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		assertEquals("NOK tarifEmprunt", Livre.TARIF, livre.tarifEmprunt(), 0.001);
	}

	@Test
	public void testInvariantLivre() throws OperationImpossible, InvariantBroken {
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		assertTrue("NOK invariant", livre.invariant());
	}

}
