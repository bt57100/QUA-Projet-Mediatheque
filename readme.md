# SOFTWARE QUALITY: Project 2017

## Instructions

 - Several bugs are to be fixed.
 - You should test the library project (Media2017 zip file).
 - You should send a test report in PDF and the test files as an Eclipse exported project in zip format.
 - All other format will be rejected.
 - Filenames should be <yourName>.pdf and <yourName>.zip.

## Bugs

### Package mediatheque

##### Mediatheque.class
 - l.135 modifierGenre():
 > if (g == null) // should throw exception not exist when null
 - l.235 modifierLocalisation():
 > Localisation inVector = chercherLocalisation(loc.getSalle(), loc.getRayon()); // should be (salle,rayon) not (rayon,rayon)
 - l.471 metEmpruntable():
 > doc.metEmpruntable(); // should set empruntable not consultable
 - l.278 chercherCatClient():
 > if (index >= 0) // should be return the value as soon as it is in the vector not only if it is at index 0
 - l.355 modifierCatClient():
 > // should either should modify c not co OR remove c from list, add co and return co not c  
 > if (!c.getNom().equals(name)) { c.modifierNom(name); }  
 > if (c.getNbEmpruntMax() != max) { c.modifierMax(max); }  
 > if (c.getCotisation() != cot) { c.modifierCotisation(cot); }  
 > if (c.getCoefDuree() != coefDuree) { c.modifierCoefDuree(coefDuree); }  
 > if (c.getCoefTarif() != coefTarif) { c.modifierCoefTarif(coefTarif);}  
 > if (c.getCodeReducUtilise() != codeReducUsed) { c.modifierCodeReducActif(codeReducUsed); }  
 - l.924 getClientAt():
 > cl = null; // should return null if the vector is shorter than the given index

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
 - l.117 constructor(): catClient uninitialized may cause NullPointerException ?
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
