package mediatheque;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Livre;
import util.InvariantBroken;

public class TestMediatheque {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMediatheque() {
		@SuppressWarnings("unused")
		Mediatheque mediatheque = new Mediatheque("data");
	}

	@Test(expected=OperationImpossible.class)
	public void testSupprimerGenreImpossible() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.supprimerGenre("notExist");
	}
	
	@Test
	public void testCRUDGenre() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		Genre genre = new Genre("genre"); 
		mediatheque.ajouterGenre("genre");
		assertEquals("NOK chercherGenre", genre, mediatheque.chercherGenre("genre"));
		mediatheque.supprimerGenre("genre");
		assertNull("NOK supprimerGenre", mediatheque.chercherGenre("genre"));
	}

	@Test(expected=OperationImpossible.class)
	public void testAjouterGenreImpossible() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterGenre("genre");
	}

	/**
	 * should throw exception not exist when null
	 * if (g == null)
	 * @throws OperationImpossible
	 */
	@Test
	public void testModifierGenre() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		Genre genre = new Genre("newGenre");
		mediatheque.ajouterGenre("genre");
		mediatheque.modifierGenre("genre", "newGenre");
		assertEquals("NOK modifierGenre", genre, mediatheque.chercherGenre("newGenre"));
	}

	@Test(expected=OperationImpossible.class)
	public void testModifierGenreException() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.modifierGenre("genre", "newGenre");
	}

	@Test
	public void testGetGenreAt() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		Genre genre = new Genre("genre");
		mediatheque.ajouterGenre("genre");
		assertEquals("NOK getGenreAt", genre, mediatheque.getGenreAt(0));
	}

	@Test
	public void testGetGenresSize() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		assertEquals("NOK getGenreAt", 0, mediatheque.getGenresSize());
		mediatheque.ajouterGenre("genre");
		assertEquals("NOK getGenreAt", 1, mediatheque.getGenresSize());
	}

	@Test(expected=OperationImpossible.class)
	public void testSupprimerLocalisation() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.supprimerLocalisation("salle", "rayon");
	}

	@Test
	public void testCRUDLocalisation() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		Localisation loc = new Localisation("salle", "rayon");
		assertNull("NOK chercherLocalisation", mediatheque.chercherLocalisation("salle", "rayon"));
		mediatheque.ajouterLocalisation("salle", "rayon");
		assertEquals("NOK ajouterLocalisation", loc, mediatheque.chercherLocalisation("salle", "rayon"));
		mediatheque.supprimerLocalisation("salle", "rayon");
		assertNull("NOK supprimerLocalisation", mediatheque.chercherLocalisation("salle", "rayon"));
	}

	@Test(expected=OperationImpossible.class)
	public void testAjouterLocalisationImpossible() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterLocalisation("salle", "rayon");
	}

	/**
	 * should be (salle,rayon) not (rayon,rayon)
	 * Localisation inVector = chercherLocalisation(loc.getSalle(), loc.getRayon());
	 * @throws OperationImpossible
	 */
	@Test
	public void testModifierLocalisation() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		Localisation loc = new Localisation("salle", "rayon");
		Localisation newLoc = new Localisation("s", "r");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.modifierLocalisation(loc, "salle", "rayon");
		assertEquals("NOK modifierLocalisation", loc, mediatheque.chercherLocalisation("salle", "rayon"));
		mediatheque.modifierLocalisation(loc, "s", "r");
		assertEquals("NOK modifierLocalisation", newLoc, mediatheque.chercherLocalisation("s", "r"));
	}

	/**
	 * should be (salle,rayon) not (rayon,rayon)
	 * Localisation inVector = chercherLocalisation(loc.getSalle(), loc.getRayon());
	 * @throws OperationImpossible
	 */
	@Test(expected=OperationImpossible.class)
	public void testModifierLocalisationImpossible() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		Localisation loc = new Localisation("salle", "rayon");
		mediatheque.modifierLocalisation(loc, "s", "r");
	}
	
	@Test
	public void testGetLocalisationAt() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		Localisation loc = new Localisation("salle", "rayon");
		mediatheque.ajouterLocalisation("salle", "rayon");
		assertEquals("NOK get", loc, mediatheque.getLocalisationAt(0));
	}

	@Test
	public void testGetLocalisationsSize() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		assertEquals("NOK get", 0, mediatheque.getLocalisationsSize());
		mediatheque.ajouterLocalisation("salle", "rayon");
		assertEquals("NOK get", 1, mediatheque.getLocalisationsSize());
	}

	@Test
	public void testCRUDCatClient() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		CategorieClient catClient = new CategorieClient("catClient", 1, 1.0, 1.0, 1.0, false);
		assertNull("NOK chercherLocalisation", mediatheque.chercherCatClient("catClient"));
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		assertEquals("NOK ajouterLocalisation", catClient, mediatheque.chercherCatClient("catClient"));
		mediatheque.supprimerCatClient("catClient");
		assertNull("NOK supprimerLocalisation", mediatheque.chercherCatClient("catClient"));
	}

	@Test(expected=OperationImpossible.class)
	public void testSupprimerCatClientImpossible1() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.supprimerCatClient("catClient");
	}

	@Test(expected=OperationImpossible.class)
	public void testSupprimerCatClientImpossible2() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient");
		mediatheque.supprimerCatClient("catClient");
	}

	@Test(expected=OperationImpossible.class)
	public void testAjouterCatClientImpossible() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
	}

	/**
	 * should modify c not co 
	 * OR remove c from list, add co and return co not c
	 * @throws OperationImpossible
	 */
	@Test
	public void testModifierCatClient() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		CategorieClient catClient = new CategorieClient("catClient", 1, 1.0, 1.0, 1.0, false);
		CategorieClient catClient2 = new CategorieClient("catClient2", 2, 2.0, 2.0, 2.0, true);
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.modifierCatClient(catClient, "catClient", 1, 1.0, 1.0, 1.0, false);
		assertEquals("NOK modifierCatClient", catClient, mediatheque.chercherCatClient("catClient"));
		mediatheque.modifierCatClient(catClient, "catClient2", 2, 2.0, 2.0, 2.0, true);
		assertEquals("NOK modifierCatClient", catClient2, mediatheque.chercherCatClient("catClient2"));
	}

	@Test(expected=OperationImpossible.class)
	public void testModifierCatClientImpossible() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		CategorieClient catClient = new CategorieClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.modifierCatClient(catClient, "catClient", 1, 1.0, 1.0, 1.0, false);
	}

	@Test
	public void testGetCategorieAt() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		CategorieClient catClient = new CategorieClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		assertEquals("NOK getCategorieAt", catClient, mediatheque.getCategorieAt(0));
		assertNull("NOK getCategorieAt", mediatheque.getCategorieAt(1));
	}

	@Test
	public void testGetCategoriesSize() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		assertEquals("NOK getCategoriesSize", 0, mediatheque.getCategoriesSize());
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		assertEquals("NOK getCategoriesSize", 1, mediatheque.getCategoriesSize());
	}
	
	@Test
	public void testChercherDocument() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		assertNull("NOK chercherLocalisation", mediatheque.chercherDocument("code"));
		mediatheque.ajouterDocument(livre);
		assertEquals("NOK ajouterLocalisation", livre, mediatheque.chercherDocument("code"));
		mediatheque.retirerDocument("code");
		assertNull("NOK supprimerLocalisation", mediatheque.chercherDocument("code"));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testAjouterDocumentImpossible2() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("name");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre1"), 1);mediatheque.ajouterGenre("genre");
		mediatheque.ajouterDocument(livre);
	}
	
	@Test(expected=OperationImpossible.class)
	public void testAjouterDocumentImpossible3() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre2"), 1);mediatheque.ajouterGenre("genre");
		mediatheque.ajouterGenre("genre2");
		mediatheque.ajouterDocument(livre);
	}
	
	@Test(expected=OperationImpossible.class)
	public void testAjouterDocumentImpossible1() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre2"), 1);mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterGenre("genre2");
		mediatheque.ajouterDocument(livre);
		mediatheque.ajouterDocument(livre);
	}

	@Test
	public void testMetEmpruntable() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		assertFalse("NOK metEmpruntable", mediatheque.getDocumentAt(0).estEmpruntable());
		mediatheque.metEmpruntable("code");
		assertTrue("NOK metEmpruntable", mediatheque.getDocumentAt(0).estEmpruntable());
	}

	@Test(expected=OperationImpossible.class)
	public void testMetEmpruntableImpossible() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.metEmpruntable("code");
	}
	
	@Test
	public void testMetConsultable() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		mediatheque.metEmpruntable("code");
		assertTrue("NOK metEmpruntable", mediatheque.getDocumentAt(0).estEmpruntable());
		mediatheque.metConsultable("code");
		assertFalse("NOK metEmpruntable", mediatheque.getDocumentAt(0).estEmpruntable());
	}

	@Test(expected=OperationImpossible.class)
	public void testMetConsultableImpossible() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.metConsultable("code");
	}
	
	@Test
	public void testGetDocumentAt() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		livre.metEmpruntable();
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		assertEquals("NOK getDocumentAt", livre, mediatheque.getDocumentAt(0));
		assertNull("NOK getDocumentAt", mediatheque.getDocumentAt(1));
	}

	@Test
	public void testGetDocumentsSize() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		livre.metEmpruntable();
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		assertEquals("NOK getDocumentAt", 0, mediatheque.getDocumentsSize());
		mediatheque.ajouterDocument(livre);
		assertEquals("NOK getDocumentAt", 1, mediatheque.getDocumentsSize());
	}

	@Test
	public void testChercherClient() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		assertNull("NOK chercherClient", mediatheque.chercherClient("nom", "prenom"));
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		assertNotNull("NOK chercherClient", mediatheque.chercherClient("nom", "prenom"));
	}

	@Test
	public void testExisteClient() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		CategorieClient catClient = new CategorieClient("catClient", 3, 1.0, 1.0, 1.0, true);
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, true);
		mediatheque.ajouterCatClient("catClient2", 3, 1.0, 1.0, 1.0, true);
		mediatheque.inscrire("name", "surname", "address", "catClient2", 0);
		assertFalse("NOK existeClient", mediatheque.existeClient(catClient));
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		assertTrue("NOK existeClient", mediatheque.existeClient(catClient));
	}

	/**
	 * should return null if the vector is shorter than the given index
	 * 
	 * @throws OperationImpossible
	 */
	@Test
	public void testGetClientAt() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		CategorieClient catClient = new CategorieClient("catClient", 3, 1.0, 1.0, 1.0, false);
		Client client = new Client("nom", "prenom", "adresse", catClient);
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		assertEquals("NOK getClientAt", client, mediatheque.getClientAt(0));
		assertNull("NOK getClientAt", mediatheque.getClientAt(1));
		
	}

	@Test
	public void testGetClientsSize() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		assertEquals("NOK getClientsSize", 0, mediatheque.getClientsSize());
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, true);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		assertEquals("NOK getClientsSize", 1, mediatheque.getClientsSize());
	}

	@Test
	public void testFindClient() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		assertNull("NOK chercherClient", mediatheque.findClient("nom", "prenom"));
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		assertNotNull("NOK chercherClient", mediatheque.findClient("nom", "prenom"));
	}

	@Test
	public void testGetNom() {
		Mediatheque mediatheque = new Mediatheque("nom");
		assertTrue("NOK getNom", "nom".equals(mediatheque.getNom()));
	}

	@Test
	public void testInscrireStringStringStringString() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		CategorieClient catClient = new CategorieClient("catClient", 1, 1.0, 1.0, 1.0, false);
		Client client = new Client("nom", "prenom", "adresse", catClient);
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient");
		assertEquals("NOK inscrireStringStringStringString", client, mediatheque.getClientAt(0));
	}

	@Test(expected=OperationImpossible.class)
	public void testInscrireStringStringStringStringImpossible() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient");
	}

	@Test
	public void testInscrireStringStringStringStringInt() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		CategorieClient catClient = new CategorieClient("catClient", 1, 1.0, 1.0, 1.0, false);
		Client client = new Client("nom", "prenom", "adresse", catClient);
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		assertEquals("NOK inscrireStringStringStringString", client, mediatheque.getClientAt(0));
	}

	@Test(expected=OperationImpossible.class)
	public void testInscrireStringStringStringStringIntImpossible() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
	}

	@Test
	public void testChangerCategorie() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		CategorieClient catClient = new CategorieClient("catClient", 3, 1.0, 1.0, 1.0, false);
		CategorieClient catClient2 = new CategorieClient("catClient2", 3, 1.0, 1.0, 1.0, true);
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.ajouterCatClient("catClient2", 3, 1.0, 1.0, 1.0, true);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);

		mediatheque.changerCategorie("nom", "prenom", "catClient2", 1);
		assertEquals("NOK changerCategorie", catClient2, mediatheque.getClientAt(0).getCategorie());
		mediatheque.changerCategorie("nom", "prenom", "catClient", 0);
		assertEquals("NOK changerCategorie", catClient, mediatheque.getClientAt(0).getCategorie());
	}

	@Test(expected=OperationImpossible.class)
	public void testChangerCategorieImpossible1() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.changerCategorie("nom", "prenom", "catClient2", 1);
	}

	@Test(expected=OperationImpossible.class)
	public void testChangerCategorieImpossible2() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		mediatheque.changerCategorie("nom", "prenom", "catClient2", 1);
	}

	@Test
	public void testChangerCodeReduction() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, true);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		mediatheque.changerCodeReduction("nom", "prenom", 1);
	}

	@Test(expected=OperationImpossible.class)
	public void testChangerCodeReductionImpossible1() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.changerCodeReduction("nom", "prenom", 1);
	}

	@Test(expected=OperationImpossible.class)
	public void testChangerCodeReductionImpossible2() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		mediatheque.changerCodeReduction("nom", "prenom", 1);
	}
	
	@Test
	public void testEmprunter() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		mediatheque.metEmpruntable("code");
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);

		assertFalse("NOK emprunter", mediatheque.getDocumentAt(0).estEmprunte());
		mediatheque.emprunter("nom", "prenom", "code");
		assertTrue("NOK emprunter", mediatheque.getDocumentAt(0).estEmprunte());
	}

	@Test(expected=OperationImpossible.class)
	public void testEmprunterImpossible1() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		
		mediatheque.emprunter("nom", "prenom", "code");
	}

	@Test(expected=OperationImpossible.class)
	public void testEmprunterImpossible2() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterCatClient("catClient", 0, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		
		mediatheque.emprunter("nom", "prenom", "code");
	}

	@Test(expected=OperationImpossible.class)
	public void testEmprunterImpossible3() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		
		mediatheque.emprunter("nom", "prenom", "code");
	}

	@Test(expected=OperationImpossible.class)
	public void testEmprunterImpossible4() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		
		mediatheque.emprunter("nom", "prenom", "code");
	}

	@Test(expected=OperationImpossible.class)
	public void testEmprunterImpossible5() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		mediatheque.metEmpruntable("code");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);

		mediatheque.emprunter("nom", "prenom", "code");
		mediatheque.emprunter("nom", "prenom", "code");
	}

	@Test
	public void testRestituer() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		mediatheque.metEmpruntable("code");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		mediatheque.emprunter("nom", "prenom", "code");

		assertTrue("NOK emprunter", mediatheque.getDocumentAt(0).estEmprunte());
		mediatheque.restituer("nom", "prenom", "code");
		assertFalse("NOK emprunter", mediatheque.getDocumentAt(0).estEmprunte());
	}

	@Test(expected=OperationImpossible.class)
	public void testRestituerImpossible1() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		
		mediatheque.restituer("nom", "prenom", "code");
	}

	@Test(expected=OperationImpossible.class)
	public void testRestituerImpossible2() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);

		mediatheque.restituer("nom", "prenom", "code");
	}

	@Test(expected=OperationImpossible.class)
	public void testRestituerImpossible3() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		mediatheque.metEmpruntable("code");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		
		mediatheque.restituer("nom", "prenom", "code");
	}

	@Test
	public void testVerifier() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");		
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		mediatheque.metEmpruntable("code");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		
		mediatheque.verifier();
		mediatheque.emprunter("nom", "prenom", "code");
		mediatheque.verifier();
	}


	@Test
	public void testGetFicheEmpruntAt() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");		
		CategorieClient catClient = new CategorieClient("catClient");
		/*Client client = new Client("nom", "prenom", "adresse", catClient, 1);
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		FicheEmprunt fiche = new FicheEmprunt(mediatheque, client, livre);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		mediatheque.metEmpruntable("code");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		mediatheque.emprunter("nom", "prenom", "code");
		
		assertEquals("NOK getFicheEmpruntAt", fiche, mediatheque.getFicheEmpruntAt(0));*/
	}

	@Test
	public void testGetFicheEmpruntsSize() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");		
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		mediatheque.metEmpruntable("code");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		
		assertEquals("NOK getFicheEmpruntAt", 0, mediatheque.getFicheEmpruntsSize());
		mediatheque.emprunter("nom", "prenom", "code");
		assertEquals("NOK getFicheEmpruntAt", 1, mediatheque.getFicheEmpruntsSize());
	}

	@Test
	public void testResilier() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		
		assertNotNull("NOK resilier", mediatheque.chercherClient("nom", "prenom"));
		mediatheque.resilier("nom", "prenom");
		assertNull("NOK resilier", mediatheque.chercherClient("nom", "prenom"));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testResilierImpossible1() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");		
		mediatheque.resilier("nom", "prenom");
	}

	@Test(expected=OperationImpossible.class)
	public void testResilierImpossible2() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		mediatheque.metEmpruntable("code");
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		mediatheque.emprunter("nom", "prenom", "code");
		
		mediatheque.resilier("nom", "prenom");
	}

	@Test
	public void testModifierClient() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		CategorieClient catClient = new CategorieClient("catClient", 3, 1.0, 1.0, 1.0, false);
		CategorieClient catClient2 = new CategorieClient("catClient2", 3, 1.0, 1.0, 1.0, false);
		Client client = new Client("nom", "prenom", "adresse", catClient);
		Client client2 = new Client( "name", "surname", "address", catClient2);
		Client client3 = new Client( "name", "surname", "address", catClient);
		mediatheque.ajouterCatClient("catClient", 3, 1.0, 1.0, 1.0, false);
		mediatheque.ajouterCatClient("catClient2", 3, 1.0, 1.0, 1.0, true);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);

		mediatheque.modifierClient(client, "nom", "prenom", "adresse", "catClient", 0);
		assertEquals("NOK modifierClient", client, mediatheque.chercherClient("nom", "prenom"));
		mediatheque.modifierClient(client, "name", "surname", "address", "catClient2", 1);
		assertEquals("NOK modifierClient", client2, mediatheque.chercherClient("name", "surname"));
		mediatheque.modifierClient(client, "name", "surname", "address", "catClient", 1);
		assertEquals("NOK modifierClient", client3, mediatheque.chercherClient("name", "surname"));
	}

	@Test(expected=OperationImpossible.class)
	public void testModifierClientImpossible() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		CategorieClient catClient = new CategorieClient("catClient", 3, 1.0, 1.0, 1.0, false);
		Client client = new Client("nom", "prenom", "adresse", catClient);
		mediatheque.modifierClient(client, "nom", "prenom", "adresse", "catClient", 0);
	}

	@Test(expected=OperationImpossible.class)
	public void testRetirerDocumentImpossible1() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		mediatheque.retirerDocument("code");
	}

	@Test(expected=OperationImpossible.class)
	public void testRetirerDocumentImpossible2() throws OperationImpossible, InvariantBroken {
		Mediatheque mediatheque = new Mediatheque("nom");
		Livre livre = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);
		mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(livre);
		mediatheque.metEmpruntable("code");
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		mediatheque.emprunter("nom", "prenom", "code");
		mediatheque.retirerDocument("code");
	}
	
	@Test
	public void testInitFromFile() {
		Mediatheque mediatheque = new Mediatheque("nom");
		assertTrue("NOK initFromFile", mediatheque.initFromFile());
	}

	@Test
	public void testInitFromFileNotFound() {
		Mediatheque mediatheque = new Mediatheque("nomInconnu");
		assertFalse("NOK initFromFile", mediatheque.initFromFile());
	}

	@Test
	public void testSaveToFile() throws OperationImpossible {
		Mediatheque mediatheque = new Mediatheque("nom");
		assertTrue("NOK saveToFile", mediatheque.saveToFile());
	}
}
