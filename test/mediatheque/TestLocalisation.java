package mediatheque;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLocalisation {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLocalisation() {
		Localisation loc = new Localisation("salle", "rayon");
		assertTrue("NOK constructor", loc.getSalle().equals("salle"));
		assertTrue("NOK constructor", loc.getRayon().equals("rayon"));
	}

	@Test
	public void testHashCode() {
		Localisation loc = new Localisation("salle", "rayon");
		int result = 31 + loc.getRayon().hashCode();
		result = 31 * result + loc.getSalle().hashCode();
		assertEquals("NOK hashCode", result, loc.hashCode());
	}

	@Test
	public void testHashCodeNull() {
		Localisation loc = new Localisation(null, null);
		int result = 31 * 31;
		assertEquals("NOK hashCode", result, loc.hashCode());
	}

	@Test
	public void testSetSalle() {
		Localisation loc = new Localisation("salle", "rayon");
		loc.setSalle("room");
		assertTrue("NOK constructor", loc.getSalle().equals("room"));
	}

	@Test
	public void testSetRayon() {
		Localisation loc = new Localisation("salle", "rayon");
		loc.setRayon("shelf");
		assertTrue("NOK constructor", loc.getRayon().equals("shelf"));
	}

	@Test
	public void testEqualsObject() {
		Localisation loc1 = new Localisation("salle", "rayon");
		Localisation loc2 = new Localisation("salle", "rayon");
		Localisation loc3 = new Localisation("room", "rayon");
		Localisation loc4 = new Localisation("salle", "shelf");
		Localisation locNull1 = new Localisation(null, null);
		Localisation locNull2 = new Localisation(null, null);
		Localisation locNull3 = new Localisation("salle", null);
		assertTrue("NOK equals", loc1.equals(loc1));
		assertFalse("NOK equals", loc1.equals(null));
		assertFalse("NOK equals", loc1.equals(""));
		assertTrue("NOK equals", locNull1.equals(locNull2));
		assertFalse("NOK equals", locNull1.equals(loc1));
		assertFalse("NOK equals", loc1.equals(loc3));
		assertFalse("NOK equals", loc1.equals(loc4));
		assertFalse("NOK equals", locNull1.equals(locNull3));
		assertTrue("NOK equals", loc1.equals(loc2));
	}

}
