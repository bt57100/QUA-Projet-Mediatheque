package mediatheque;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Document;
import mediatheque.document.Livre;
import util.Datutil;
import util.InvariantBroken;

public class TestFicheEmprunt {
	
	private SimpleDateFormat format;
	private String today;
	private Mediatheque mediatheque;
	private CategorieClient catClient;
	private Client client;
	private Document doc;
	
	@Before
	public void setUp() throws Exception {
		format = new SimpleDateFormat("yyyy-MM-dd");
		today = format.format(Datutil.dateDuJour());
		
		catClient = new CategorieClient("catClient", 10, 1.0, 1.0, 1.0, false);
		client = new Client("nom", "prenom", "adresse", catClient);
		doc = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);	
		mediatheque = new Mediatheque("nom");mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(doc);
		mediatheque.metEmpruntable("code");
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFicheEmprunt() throws OperationImpossible, InvariantBroken {
		FicheEmprunt fiche = new FicheEmprunt(mediatheque, client, doc);
		assertEquals("NOK constructor", client, fiche.getClient());
		assertTrue("NOK constructor", today.equals(format.format(fiche.getDateEmprunt())));
		assertEquals("NOK constructor", false, fiche.getDepasse());
		assertEquals("NOK constructor", doc, fiche.getDocument());
		assertEquals("NOK constructor", doc.dureeEmprunt(), fiche.getDureeEmprunt());
		assertEquals("NOK constructor", client.sommeDue(doc.tarifEmprunt()), fiche.getTarifEmprunt(), 0.001);

		Date limit = Datutil.addDate(fiche.getDateEmprunt(), doc.dureeEmprunt());
		assertTrue("NOK constructor", format.format(limit).equals(format.format(fiche.getDateLimite())));
	}
	
	@Test
	public void testVerifier() throws OperationImpossible, InvariantBroken {
		FicheEmprunt fiche = new FicheEmprunt(mediatheque, client, doc);
		fiche.verifier();
		Datutil.addAuJour(-1000);
		fiche.setDateEmprunt(Datutil.dateDuJour());
		Datutil.addAuJour(1000);
		fiche.verifier();
		fiche.verifier();
	}
	
	@Test
	public void testModifierClient() throws OperationImpossible, InvariantBroken {
		FicheEmprunt fiche = new FicheEmprunt(mediatheque, client, doc);
		Client client2 = new Client("name", "surname", "address", catClient);
		assertNotEquals("NOK modifierClient", client2, fiche.getClient());
		fiche.modifierClient(client2);
		assertEquals("NOK modifierClient", client2, fiche.getClient());
	}

	@Test
	public void testCorrespond() throws OperationImpossible, InvariantBroken {
		Client client2 = new Client("name", "surname", "address", catClient);
		Document doc2 = new Livre("code2", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);	
		FicheEmprunt fiche = new FicheEmprunt(mediatheque, client, doc);
		assertFalse("NOK correspond", fiche.correspond(client2, doc));
		assertFalse("NOK correspond", fiche.correspond(client, doc2));
		assertTrue("NOK correspond", fiche.correspond(client, doc));
	}

	@Test
	public void testRestituer() throws InvariantBroken, OperationImpossible {
		FicheEmprunt fiche = new FicheEmprunt(mediatheque, client, doc);
		assertTrue("NOK restituer", doc.estEmprunte());
		fiche.restituer();
		assertFalse("NOK restituer", doc.estEmprunte());
	}

	/**
	 * should call client.metAJourEmprunts()
	 * @throws OperationImpossible
	 * @throws InvariantBroken
	 */
	@Test
	public void testChangementCategorie() throws OperationImpossible, InvariantBroken {
		FicheEmprunt fiche = new FicheEmprunt(mediatheque, client, doc);
		fiche.changementCategorie();
		assertFalse("NOK changementCategorie", fiche.getDepasse());
		Datutil.addAuJour(-1000);
		fiche.setDateEmprunt(Datutil.dateDuJour());
		Datutil.addAuJour(1000);
		fiche.changementCategorie();
		assertTrue("NOK changementCategorie", fiche.getDepasse());
	}

	/**
	 * should call client.metAJourEmprunts()
	 * @throws OperationImpossible
	 * @throws InvariantBroken
	 */
	@Test(expected=OperationImpossible.class)
	public void testChangementCategorieImpossible() throws OperationImpossible, InvariantBroken {
		FicheEmprunt fiche = new FicheEmprunt(mediatheque, client, doc);
		fiche.changementCategorie();
		assertFalse("NOK changementCategorie", fiche.getDepasse());
		Datutil.addAuJour(-1000);
		fiche.setDateEmprunt(Datutil.dateDuJour());
		Datutil.addAuJour(1000);
		fiche.changementCategorie();
		assertTrue("NOK changementCategorie", fiche.getDepasse());
		fiche.changementCategorie();
	}

}
