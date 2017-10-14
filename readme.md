# SOFTWARE QUALITY: Project 2017

## Instructions

 - Several bugs are to be fixed.
 - You should test the library project (Media2017 zip file).
 - You should send a test report in PDF and the test files as an Eclipse exported project in zip format.
 - All other format will be rejected.
 - Filenames should be <yourName>.pdf and <yourName>.zip.

## TODO 
 - mediatheque.client.Client.java (2 methods left)
 - mediatheque.Mediatheque.java
 - mediatheque.FicheEmprunt.java
 - mediatheque.LettreRappel.java

## Bugs

### Package mediatheque

##### Genre.class
 - l. 20 default attribut: 
 > int nbEmprunts = 0; //default nbEmprunts should be 0 not 10
 - l. 28 constructor():	
 >	nbEmprunts=0; // uncomment remove the previous problem
 - l. 36 emprunter():
 > nbEmprunts = nbEmprunts+1; //should be increment 1 by 1 (not 2)

### Package mediatheque.client

##### CategorieClient.class
 - l. 72 modifierCotisation():
 >	cotisation = cot; //remove auto-set to 4

##### Client.class
 - l.103 constructor():
 > if(catClient.getCodeReducUtilise()) //remove ! new client have no code reduc
 - l.117 constructor(): catClient uninitialized may cause NullPointerException
 - l.188 getNbEmpruntsEnRetard():
 > return nbEmpruntsDepasses; // should return nbEmpruntsDepasses instead of 1
 - l.290 emprunter():
 > nbEmpruntsTotal++; //should increment static nbEmpruntsTotal
 - l.300 marquer():
 > if(nbEmpruntsDepasses >= nbEmpruntsEnCours) && (nbEmpruntsEnCours != 0)	
 > //should cover the case more late borrow than current borrow + remove case current == 0

### Package mediatheque.document

##### Document.class
 - l.198 metEmpruntable():
 > empruntable = true; // set enable borrow instead of false					


##### Livre.class
 - l. 53 constructor(): (not mandatory)
 > if(nombrePage < 1) // related to invariant
 - l. 78 emprunter():
 > nbEmpruntsTotal++; // should be auto-incremented for stats

##### Video.class
 - l. 58 constructor(): (not mandatory)
 > if(dureeFilm < 1) // related to invariant


### Package util

##### Datutil.class
 - l. 59 addDate():
 > greg.add(Calendar.DATE, nbjour); // remove -10 which cause a gap of 10 days
