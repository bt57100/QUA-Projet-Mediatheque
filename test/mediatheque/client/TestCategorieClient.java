package mediatheque.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCategorieClient {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCategorieClientStringIntDoubleDoubleDoubleBoolean() {
		CategorieClient catClient = new CategorieClient("nom", 1, 1.0, 1.0, 1.0, false);
		assertTrue("NOK constructor", catClient.getNom().equals("nom"));
		assertEquals("NOK constructor", 1, catClient.getNbEmpruntMax());
		assertEquals("NOK constructor", 1.0, catClient.getCotisation(), 0.001);
		assertEquals("NOK constructor", 1.0, catClient.getCoefDuree(), 0.001);
		assertEquals("NOK constructor", 1.0, catClient.getCoefTarif(), 0.001);
		assertFalse("NOK constructor", catClient.getCodeReducUtilise());
	}

	@Test
	public void testCategorieClientString() {
		CategorieClient catClient = new CategorieClient("nom");
		assertTrue("NOK constructor", catClient.getNom().equals("nom"));
		assertEquals("NOK constructor", 0, catClient.getNbEmpruntMax());
		assertEquals("NOK constructor", 0, catClient.getCotisation(), 0.001);
		assertEquals("NOK constructor", 0, catClient.getCoefDuree(), 0.001);
		assertEquals("NOK constructor", 0, catClient.getCoefTarif(), 0.001);
		assertFalse("NOK constructor", catClient.getCodeReducUtilise());
	}

	@Test
	public void testHashCode() {
		CategorieClient catClient = new CategorieClient("nom");
		int result = 31 + "nom".hashCode();
		assertEquals("NOK hashCode", result, catClient.hashCode());
	}

	@Test
	public void testHashCodeNull() {
		CategorieClient catClient = new CategorieClient(null);
		int result = 31;
		assertEquals("NOK hashCode", result, catClient.hashCode());
	}

	@Test
	public void testModifierNom() {
		CategorieClient catClient = new CategorieClient("nom");
		catClient.modifierNom("new");
		assertTrue("NOK modifierNom", catClient.getNom().equals("new"));
	}

	@Test
	public void testModifierMax() {
		CategorieClient catClient = new CategorieClient("nom");
		catClient.modifierMax(1);
		assertEquals("NOK modifierMax", 1, catClient.getNbEmpruntMax());
	}

	@Test
	public void testModifierCotisation() {
		CategorieClient catClient = new CategorieClient("nom");
		catClient.modifierCotisation(1);
		assertEquals("NOK modifierCotisation", 1, catClient.getCotisation(), 0.001);
	}

	@Test
	public void testModifierCoefDuree() {
		CategorieClient catClient = new CategorieClient("nom");
		catClient.modifierCoefDuree(1);
		assertEquals("NOK modifierCoefDuree", 1, catClient.getCoefDuree(), 0.001);
	}

	@Test
	public void testModifierCoefTarif() {
		CategorieClient catClient = new CategorieClient("nom");
		catClient.modifierCoefTarif(1);
		assertEquals("NOK modifierCoefTarif", 1, catClient.getCoefTarif(), 0.001);
	}

	@Test
	public void testModifierCodeReducActif() {
		CategorieClient catClient = new CategorieClient("nom");
		catClient.modifierCodeReducActif(true);
		assertTrue("NOK modifierCodeReducActif", catClient.getCodeReducUtilise());
	}

	@Test
	public void testToString() {
		CategorieClient catClient = new CategorieClient("nom");
		assertTrue("NOK toString", catClient.toString().equals("nom"));
	}

	@Test
	public void testEqualsObject() {
		CategorieClient catClient1 = new CategorieClient("nom");
		CategorieClient catClient2 = new CategorieClient("nom");
		CategorieClient catClient3 = new CategorieClient("autre");
		CategorieClient catClientNull1 = new CategorieClient(null);
		CategorieClient catClientNull2 = new CategorieClient(null);
		assertTrue("NOK equals", catClient1.equals(catClient1));
		assertFalse("NOK equals", catClient1.equals(null));
		assertFalse("NOK equals", catClient1.equals(""));
		assertFalse("NOK equals", catClientNull1.equals(catClient1));
		assertFalse("NOK equals", catClient1.equals(catClient3));
		assertTrue("NOK equals", catClientNull1.equals(catClientNull2));
		assertTrue("NOK equals", catClient1.equals(catClient2));
	}

}
