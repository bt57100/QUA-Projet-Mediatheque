package global;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import mediatheque.TestFicheEmprunt;
import mediatheque.TestGenre;
import mediatheque.TestLettreRappel;
import mediatheque.TestLocalisation;
import mediatheque.TestMediatheque;
import mediatheque.TestOperationImpossible;
import mediatheque.client.TestCategorieClient;
import mediatheque.client.TestClient;
import mediatheque.client.TestHashClient;
import mediatheque.document.TestAudio;
import mediatheque.document.TestDocument;
import mediatheque.document.TestLivre;
import mediatheque.document.TestVideo;
import util.TestConsole;
import util.TestDatutil;
import util.TestInvariantBroken;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestFicheEmprunt.class, 
	TestGenre.class, 
	TestLettreRappel.class, 
	TestLocalisation.class,
	TestMediatheque.class, 
	TestOperationImpossible.class,
	TestCategorieClient.class,
	TestClient.class,
	TestHashClient.class,
	TestAudio.class,
	TestDocument.class,
	TestLivre.class,
	TestVideo.class,
	TestConsole.class,
	TestDatutil.class,
	TestInvariantBroken.class})
public class AllTests {

}
