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

public class TestLettreRappel {
	
	private SimpleDateFormat format;
	private Date today;
	private Mediatheque mediatheque;
	private CategorieClient catClient;
	private Client client;
	private Document doc;
	private FicheEmprunt fiche;
	
	@Before
	public void setUp() throws Exception {
		format = new SimpleDateFormat("yyyy-MM-dd");
		today = Datutil.dateDuJour();
		
		catClient = new CategorieClient("catClient", 3, 1.0, 1.0, 1.0, false);
		client = new Client("nom", "prenom", "adresse", catClient);
		doc = new Livre("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"), 1);	
		mediatheque = new Mediatheque("nom");mediatheque.ajouterGenre("genre");
		mediatheque.ajouterLocalisation("salle", "rayon");
		mediatheque.ajouterDocument(doc);
		mediatheque.metEmpruntable("code");
		mediatheque.ajouterCatClient("catClient", 1, 1.0, 1.0, 1.0, false);
		mediatheque.inscrire("nom", "prenom", "adresse", "catClient", 0);
		fiche = new FicheEmprunt(mediatheque, client, doc);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLettreRappel() {
		LettreRappel lettre = new LettreRappel(doc.getTitre(), fiche);
		assertTrue("NOK constructor", format.format(fiche.getDateLimite()).equals(format.format(lettre.getDateRappel())));
	}

	@Test
	public void testRelancer() {
		LettreRappel lettre = new LettreRappel(doc.getTitre(), fiche);
		Datutil.addAuJour(10);
		today = Datutil.dateDuJour();
		lettre.relancer();
		assertTrue("NOK relancer", format.format(today).equals(format.format(lettre.getDateRappel())));
	}

}
