package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import util.InvariantBroken;

public class TestDocument {
	
	/**
	 * Class to test Document abstract class
	 * @author Kevin
	 *
	 */
	public class DocumentImpl extends Document {
		
		private static final long serialVersionUID = 1L;

		public DocumentImpl(String code, Localisation localisation, String titre, String auteur, String annee,
				Genre genre) throws OperationImpossible {
			super(code, localisation, titre, auteur, annee, genre);
		}

		@Override
		public int dureeEmprunt() {
			return 0;
		}

		@Override
		public double tarifEmprunt() {
			return 0;
		}
		
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDocument() throws OperationImpossible {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		assertTrue("NOK constructor code", doc.getCode().equals("code"));
		assertTrue("NOK constructor localisation", doc.getLocalisation().equals(new Localisation("salle", "rayon")));
		assertTrue("NOK constructor titre", doc.getTitre().equals("titre"));
		assertTrue("NOK constructor auteur", doc.getAuteur().equals("auteur"));
		assertTrue("NOK constructor annee", doc.getAnnee().equals("annee"));
		assertTrue("NOK constructor genre", doc.getGenre().equals(new Genre("genre")));
		assertEquals("NOK constructor emprunte", false, doc.estEmprunte());
		assertEquals("NOK constructor empruntable", false, doc.estEmpruntable());
		assertEquals("NOK default nbEmpruntsTotal", 0, doc.getNbEmprunts());
	}

	@Test(expected=OperationImpossible.class)
	public void testDocumentImpossible() throws OperationImpossible {
		@SuppressWarnings("unused")
		DocumentImpl doc = new DocumentImpl(null, null, null, null, null, null);
	}

	@Test(expected=OperationImpossible.class)
	public void testDocumentImpossible1() throws OperationImpossible {
		@SuppressWarnings("unused")
		DocumentImpl doc = new DocumentImpl("code", null, null, null, null, null);
	}

	@Test(expected=OperationImpossible.class)
	public void testDocumentImpossible2() throws OperationImpossible {
		@SuppressWarnings("unused")
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), null, null, null, null);
	}

	@Test(expected=OperationImpossible.class)
	public void testDocumentImpossible3() throws OperationImpossible {
		@SuppressWarnings("unused")
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", null, null, null);
	}

	@Test(expected=OperationImpossible.class)
	public void testDocumentImpossible4() throws OperationImpossible {
		@SuppressWarnings("unused")
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", null, null);
	}

	@Test(expected=OperationImpossible.class)
	public void testDocumentImpossible5() throws OperationImpossible {
		@SuppressWarnings("unused")
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", null);
	}

	@Test
	public void testHashCode() throws OperationImpossible {
        int result = 31 + "code".hashCode();
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
        assertEquals("NOK hashcode", result, doc.hashCode());
	}

	@Test
	public void testEquals() throws OperationImpossible {
		DocumentImpl doc1 = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		DocumentImpl doc2 = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		assertTrue("NOK equals", doc1.equals(doc1));
		assertTrue("NOK equals", doc1.equals(doc2));
		assertFalse("NOK equals", doc1.equals(null));
		assertFalse("NOK equals", doc1.equals(""));
	}
	
	@Test
	public void testMetEmpruntable() throws OperationImpossible, InvariantBroken {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		assertEquals("NOK metEmpruntable", false, doc.estEmpruntable());
		doc.metEmpruntable();
		assertEquals("NOK metEmpruntable", true, doc.estEmpruntable());
	}
	
	@Test(expected=OperationImpossible.class)
	public void testMetEmpruntableImpossible() throws OperationImpossible, InvariantBroken {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		doc.metEmpruntable();
		doc.metEmpruntable();
	}

	@Test
	public void testMetConsultable() throws OperationImpossible, InvariantBroken {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		doc.metEmpruntable();
		doc.metConsultable();
		assertEquals("NOK metConsultable", false, doc.estEmpruntable());
	}

	@Test(expected=OperationImpossible.class)
	public void testMetConsultableImpossible1() throws OperationImpossible, InvariantBroken {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		doc.metConsultable();
	}

	@Test(expected=OperationImpossible.class)
	public void testMetConsultableImpossible2() throws OperationImpossible, InvariantBroken {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		doc.metEmpruntable();
		doc.emprunter();
		doc.metConsultable();
	}

	@Test
	public void testEmprunter() throws OperationImpossible, InvariantBroken {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		doc.metEmpruntable();
		assertEquals("NOK emprunter", 0, doc.getNbEmprunts());
		assertTrue("NOK emprunter", doc.emprunter());
		assertEquals("NOK emprunter", 1, doc.getNbEmprunts());
	}

	@Test(expected=OperationImpossible.class)
	public void testEmprunterImpossible1() throws OperationImpossible, InvariantBroken {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		assertTrue("NOK emprunter", doc.emprunter());
	}

	@Test(expected=OperationImpossible.class)
	public void testEmprunterImpossible2() throws OperationImpossible, InvariantBroken {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		doc.metEmpruntable();
		assertTrue("NOK emprunter", doc.emprunter());
		assertFalse("NOK emprunter", doc.emprunter());
	}

	@Test
	public void testRestituer() throws OperationImpossible, InvariantBroken {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		doc.metEmpruntable();
		doc.emprunter();
		doc.restituer();
	}

	@Test(expected=OperationImpossible.class)
	public void testRestituerImpossible1() throws OperationImpossible, InvariantBroken {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		doc.restituer();
	}

	@Test(expected=OperationImpossible.class)
	public void testRestituerImpossible2() throws OperationImpossible, InvariantBroken {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		doc.metEmpruntable();
		doc.restituer();
	}

	@Test
	public void testInvariant() throws OperationImpossible {
		DocumentImpl doc = new DocumentImpl("code", new Localisation("salle", "rayon"), "titre", "auteur", "annee", new Genre("genre"));
		assertTrue("NOK invariant", doc.invariant());
	}

}
