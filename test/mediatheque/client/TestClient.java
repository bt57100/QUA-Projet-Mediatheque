package mediatheque.client;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.OperationImpossible;

public class TestClient {

	private SimpleDateFormat format;
	private String today;
	private String inOneYear;
	
	@Before
	public void setUp() throws Exception {
		Calendar calendar = Calendar.getInstance();
		format = new SimpleDateFormat("yyyy-MM-dd");
		today = format.format(calendar.getTime());
		calendar.add(Calendar.DATE, 365);
		inOneYear = format.format(calendar.getTime());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClientStringStringStringCategorieClient() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		assertTrue("NOK constructor", client.getNom().equals("nom"));
		assertTrue("NOK constructor", client.getPrenom().equals("prenom"));
		assertTrue("NOK constructor", client.getAdresse().equals("adresse"));
		assertTrue("NOK constructor", client.getCategorie().equals(catClient));
		assertTrue("NOK constructor", today.equals(format.format(client.getDateInscription())));
		assertEquals("NOK default", 0, client.getNbEmpruntsEnCours());
		assertEquals("NOK default", 0, client.getNbEmpruntsEffectues());
		assertEquals("NOK default", 0, client.getCoefTarif(), 0.001);
		assertEquals("NOK default", 0, client.getCoefDuree(), 0.001);
	}
	
	/**
	 * Error in getNbEmpruntsEnRetard always return 1 => failed
	 * @throws OperationImpossible 
	 */
	@Test
	public void testGetNbEmpruntsEnRetard() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		assertEquals("NOK default", 0, client.getNbEmpruntsEnRetard());
	}
	
	/**
	 * Error in Datutil when adding day () => failed
	 * @see util.TestDatutil for more info
	 * @throws OperationImpossible 
	 */
	@Test
	public void testGetDateCotisation() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		assertTrue("NOK constructor", inOneYear.equals(format.format(client.getDateCotisation())));
	}

	@Test(expected=OperationImpossible.class)
	public void testClientStringStringStringCategorieClientImpossible1() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		@SuppressWarnings("unused")
		Client client = new Client(null, "prenom", "adresse", catClient);
	}

	@Test(expected=OperationImpossible.class)
	public void testClientStringStringStringCategorieClientImpossible2() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		@SuppressWarnings("unused")
		Client client = new Client("nom", null, "adresse", catClient);
	}

	@Test(expected=OperationImpossible.class)
	public void testClientStringStringStringCategorieClientImpossible3() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		@SuppressWarnings("unused")
		Client client = new Client("nom", "prenom", null, catClient);
	}

	@Test(expected=OperationImpossible.class)
	public void testClientStringStringStringCategorieClientImpossible4() throws OperationImpossible {
		@SuppressWarnings("unused")
		Client client = new Client("nom", "prenom", "adresse", null);
	}

	@Test(expected=OperationImpossible.class)
	public void testClientStringStringStringCategorieClientImpossible5() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		catClient.modifierCodeReducActif(true);
		@SuppressWarnings("unused")
		Client client = new Client("nom", "prenom", "adresse", catClient);
	}

	@Test
	public void testClientStringStringStringCategorieClientInt() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient", 10, 1.0, 1.0, 1.0, true);
		Client client = new Client("nom", "prenom", "adresse", catClient, 1);
		assertTrue("NOK constructor", client.getNom().equals("nom"));
		assertTrue("NOK constructor", client.getPrenom().equals("prenom"));
		assertTrue("NOK constructor", client.getAdresse().equals("adresse"));
		assertTrue("NOK constructor", client.getCategorie().equals(catClient));
		assertEquals("NOK constructor", 1, client.getReduc());
	}

	@Test(expected=OperationImpossible.class)
	public void testClientStringStringStringCategorieClientIntImpossible() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient, 0);
		assertEquals("NOK constructor", 0, client.getReduc());
	}

	/**
	 * Some fields like CategorieClient are not initialized 
	 * and may cause NullPointerException
	 * 
	 */
	@Test
	public void testClientStringString() {
		Client client = new Client("nom", "prenom");
		assertTrue("NOK constructor", client.getNom().equals("nom"));
		assertTrue("NOK constructor", client.getPrenom().equals("prenom"));
	}
	
	@Test
	public void testHashCode() {
		Client client = new Client("nom", "prenom");
		int result = 37 * 17 + client.getNom().hashCode();
		result = 37 * result + client.getPrenom().hashCode();
		assertEquals("NOK hashCode", result, client.hashCode());
	}

	@Test
	public void testEqualsObject() {
		Client client1 = new Client("nom", "prenom");
		Client client2 = new Client("nom", "prenom");
		Client client3 = new Client("autre", "autre");
		Client client4 = new Client("nom", "autre");
		assertFalse("NOK equals", client1.equals(null));
		assertTrue("NOK equals", client1.equals(client1));
		assertFalse("NOK equals", client1.equals(""));
		assertFalse("NOK equals", client1.equals(client3));
		assertFalse("NOK equals", client1.equals(client4));
		assertTrue("NOK equals", client1.equals(client2));
	}

	@Test
	public void testADesEmpruntsEnCours() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		client.getCategorie().modifierMax(1);
		assertFalse("NOK aDesEmpruntsEnCours", client.aDesEmpruntsEnCours());
		client.emprunter();
		assertTrue("NOK aDesEmpruntsEnCours", client.aDesEmpruntsEnCours());
	}

	/**
	 * getnbEmpruntsTotal() and getStat():
	 * nbEmpruntsTotal is not increment => failed 
	 * 
	 * @throws OperationImpossible
	 */
	@Test
	public void testEmprunter() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		client.getCategorie().modifierMax(1);	
		assertEquals("NOK emprunter", 0, client.getNbEmpruntsEffectues());
		assertEquals("NOK emprunter", 0, client.getNbEmpruntsEnCours());
		assertEquals("NOK emprunter", 0, Client.getnbEmpruntsTotal());
		assertEquals("NOK emprunter", 0, Client.getStat());

		client.emprunter();
		assertEquals("NOK emprunter", 1, client.getNbEmpruntsEffectues());
		assertEquals("NOK emprunter", 1, client.getNbEmpruntsEnCours());
		assertEquals("NOK emprunter", 1, Client.getnbEmpruntsTotal());
		assertEquals("NOK emprunter", 1, Client.getStat());
	}

	/**
	 * nbEmpruntsDepasses should be " >= " to nbEmpruntsEnCours
	 * to manage more cases also the condition should exclude 
	 * nbEmpruntsDepasses == 0 OR nbEmpruntsEnCours == 0
	 * @throws OperationImpossible
	 */
	@Test
	public void testMarquer() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		client.getCategorie().modifierMax(1);	
		client.emprunter();
		client.marquer();
	}

	@Test(expected=OperationImpossible.class)
	public void testMarquerImpossible() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		client.marquer();
	}

	//TODO
	@Test
	public void testRestituerFicheEmprunt() throws OperationImpossible {
		fail("Not yet implemented");
	}

	//TODO
	@Test
	public void testRestituerFicheEmpruntImpossible() throws OperationImpossible {
		fail("Not yet implemented");
	}

	@Test
	public void testRestituerBooleanFalse() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		client.getCategorie().modifierMax(1);	
		client.emprunter();
		assertEquals("NOK restituer", 1, client.getNbEmpruntsEnCours());
		client.restituer(false);
		assertEquals("NOK restituer", 0, client.getNbEmpruntsEnCours());
	}

	@Test(expected=OperationImpossible.class)
	public void testRestituerBooleanTrueImpossible() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		client.getCategorie().modifierMax(1);	
		client.emprunter();
		client.restituer(true);
	}

	@Test(expected=OperationImpossible.class)
	public void testRestituerBooleanImpossible() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		client.restituer(false);
	}

	/**
	 * Error in Datutil when adding day () => failed
	 * @throws OperationImpossible 
	 * @see util.TestDatutil for more info
	 * 
	 */
	@Test
	public void testDateRetour() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		client.getCategorie().modifierCoefDuree(1);
		String dateRetour = format.format(client.dateRetour(new Date(), 365));
		assertTrue("NOK dateRetour", inOneYear.equals(dateRetour));
	}

	@Test
	public void testSommeDue() throws OperationImpossible {
		double tarif = 10;
		double coeff = 0.5; 
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		client.getCategorie().modifierCoefTarif(coeff);
		assertEquals("NOK sommeDue", tarif * coeff, client.sommeDue(tarif), 0.001);
	}

	@Test
	public void testSetCategorieCategorieClient() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		client.setCategorie(catClient);
	}

	@Test(expected=OperationImpossible.class)
	public void testSetCategorieCategorieClientImpossible() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		catClient.modifierCodeReducActif(true);
		client.setCategorie(catClient);
	}

	@Test
	public void testSetCategorieCategorieClientIntTrue() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		catClient.modifierCodeReducActif(true);
		client.setCategorie(catClient, 1);
	}

	@Test(expected=OperationImpossible.class)
	public void testSetCategorieCategorieClientIntFalse() throws OperationImpossible {
		CategorieClient catClient = new CategorieClient("catClient");
		Client client = new Client("nom", "prenom", "adresse", catClient);
		client.setCategorie(catClient, 1);
	}

	@Test
	public void testSetter() {
		Client client = new Client("nom", "prenom");
		client.setNom("nom");
		client.setPrenom("prenom");
		client.setAddresse("adresse");
		client.setReduc(1);
		assertTrue("NOK constructor", client.getNom().equals("nom"));
		assertTrue("NOK constructor", client.getPrenom().equals("prenom"));
		assertTrue("NOK constructor", client.getAdresse().equals("adresse"));
		assertEquals("NOK constructor", 1, client.getReduc());
	}
}
