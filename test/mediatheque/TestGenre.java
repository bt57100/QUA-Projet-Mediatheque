package mediatheque;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGenre {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenre() {
		Genre genre = new Genre("genre");
		assertTrue("NOK constructor", genre.getNom().equals("genre"));
		assertEquals("NOK constructor", 0, genre.getNbEmprunts());
	}

	@Test
	public void testHashCode() {
		Genre genre = new Genre("genre");
		int result = 31 + genre.getNbEmprunts();
		result = 31 * result + genre.getNom().hashCode();
		assertEquals("NOK constructor", result, genre.hashCode());
	}

	@Test
	public void testHashCodeNull() {
		Genre genre = new Genre(null);
		int result = 31 + genre.getNbEmprunts();
		result = 31 * result;
		assertEquals("NOK constructor", result, genre.hashCode());
	}

	@Test
	public void testEmprunter() {
		Genre genre = new Genre("genre");
		int nbEmprunt = genre.getNbEmprunts();
		genre.emprunter();
		nbEmprunt++;
		assertEquals("NOK emprunter", nbEmprunt, genre.getNbEmprunts());
	}

	@Test
	public void testModifier() {
		Genre genre = new Genre("genre");
		genre.modifier("new");
		assertTrue("NOK modifier", genre.getNom().equals("new"));
	}

	@Test
	public void testEqualsObject() {
		Genre genre1 = new Genre("genre");
		Genre genre2 = new Genre("genre");
		Genre genre3 = new Genre("genre");
		genre3.emprunter();
		Genre genre4 = new Genre("nom");
		Genre genreNull1 = new Genre(null);
		Genre genreNull2 = new Genre(null);
		assertTrue("NOK equals", genre1.equals(genre1));
		assertFalse("NOK equals", genre1.equals(null));
		assertFalse("NOK equals", genre1.equals(""));
		assertFalse("NOK equals", genre1.equals(genre3));
		assertFalse("NOK equals", genreNull1.equals(genre1));
		assertTrue("NOK equals", genreNull1.equals(genreNull2));
		assertFalse("NOK equals", genre1.equals(genre4));
		assertTrue("NOK equals", genre1.equals(genre2));
	}

}
