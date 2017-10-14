package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import util.InvariantBroken;

public class TestVideo {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVideo() throws OperationImpossible, InvariantBroken {
		Video video = new Video("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1, "mentionLegale");
		assertTrue("NOK constructor code", video.getCode().equals("code"));
		assertTrue("NOK constructor localisation", video.getLocalisation().equals(new Localisation("salle", "rayon")));
		assertTrue("NOK constructor titre", video.getTitre().equals("titre"));
		assertTrue("NOK constructor auteur", video.getAuteur().equals("auteur"));
		assertTrue("NOK constructor annee", video.getAnnee().equals("annee"));
		assertTrue("NOK constructor genre", video.getGenre().equals(new Genre("genre")));
		assertEquals("NOK constructor dureeFilm", video.getDureeFilm(), 1);
		assertTrue("NOK constructor mentionLegale", video.getMentionLegale().equals("mentionLegale"));
	}

	@Test(expected=OperationImpossible.class)
	public void testVideoImpossible1() throws OperationImpossible, InvariantBroken {
		@SuppressWarnings("unused")
		Video video = new Video("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 0, "mentionLegale");
	}

	@Test(expected=OperationImpossible.class)
	public void testVideoImpossible2() throws OperationImpossible, InvariantBroken {
		@SuppressWarnings("unused")
		Video video = new Video("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1, null);
	}

	@Test(expected=InvariantBroken.class)
	public void testVideoBroken() throws OperationImpossible, InvariantBroken {
		@SuppressWarnings("unused")
		Video video = new Video("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), -1, "mentionLegale");
	}

	@Test
	public void testEmprunter() throws OperationImpossible, InvariantBroken {
		Video video = new Video("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1, "mentionLegale");
		video.metEmpruntable();
		assertEquals("NOK emprunter", 0, Video.getStat());
		video.emprunter();
		assertEquals("NOK emprunter", 1, Video.getStat());
	}

	@Test
	public void testDureeEmprunt() throws OperationImpossible, InvariantBroken {
		Video video = new Video("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1, "mentionLegale");
		assertEquals("NOK dureeEmprunt", Video.DUREE, video.dureeEmprunt());
	}

	@Test
	public void testTarifEmprunt() throws OperationImpossible, InvariantBroken {
		Video video = new Video("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1, "mentionLegale");
		assertEquals("NOK tarifEmprunt", Video.TARIF, video.tarifEmprunt(), 0.001);
	}

	@Test
	public void testInvariantVideo() throws OperationImpossible, InvariantBroken {
		Video video = new Video("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1, "mentionLegale");
		assertTrue("NOK invariant", video.invariant());
	}

}
