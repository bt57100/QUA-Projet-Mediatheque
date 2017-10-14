package mediatheque.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestHashClient {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHashClient() {
		HashClient hashClient = new HashClient("nom", "prenom");
		assertTrue("NOK constructor", hashClient.getNom().equals("nom"));
		assertTrue("NOK constructor", hashClient.getPrenom().equals("prenom"));
	}

	@Test
	public void testHashCode() {
		HashClient hashClient = new HashClient("nom", "prenom");
		int result = 37 * 17 + hashClient.getNom().hashCode();
        result = 37*result + hashClient.getPrenom().hashCode();
        assertEquals("NOK hashCode", result, hashClient.hashCode());
	}

	@Test
	public void testEqualsObject() {
		HashClient hashClient1 = new HashClient("nom", "prenom");
		HashClient hashClient2 = new HashClient("nom", "prenom");
		HashClient hashClient3 = new HashClient("autre", "prenom");
		HashClient hashClient4 = new HashClient("nom", "autre");
		assertFalse("NOK constructor", hashClient1.equals(null));
		assertTrue("NOK constructor", hashClient1.equals(hashClient1));
		assertFalse("NOK constructor", hashClient1.equals(""));
		assertFalse("NOK constructor", hashClient1.equals(hashClient3));
		assertFalse("NOK constructor", hashClient1.equals(hashClient4));
		assertTrue("NOK constructor", hashClient1.equals(hashClient2));
	}
	
}
