Genre				l. 20	default attribut			nbEmprunts=0;							default nbEmprunts should be 0
Genre				l. 28	constructor()														uncomment //nbEmprunts=0; can remove the above pb
Genre				l. 36	emprunter()					nbEmprunts++;							should be increment 1 by 1 (not 2)

CategorieClient		l. 72 	modifierCotisation()		cotisation = cot;						remove auto-set to 4
Client				l.103	constructor()				catClient.getCodeReducUtilise()			remove ! new client haven't use code reduc
Client				l.117	constructor()														catClient unitialize can cause NullPointerException
Client				l.188	getNbEmpruntsEnRetard()		return nbEmpruntsDepasses;				always return 1 instead of nbEmpruntsDepasses
Client				l.290	emprunter()															should increment static nbEmpruntsTotal
Client				l.300	marquer()					nbEmpruntsDepasses >= nbEmpruntsEnCours	should cover the case more late borrow than current borrow + remove case == 0

Document 			l.198 	metEmpruntable()			empruntable = true;					
Livre				l. 53 	constructor()				nombrePage < 1 OU nombrePage <= 0 		related to invariant				?
Livre				l. 78 	emprunter()					nbEmpruntsTotal++; 							auto-increment for stats
Video				l. 58 	constructor()				dureeFilm < 1 OU dureeFilm <= 0 		related to invariant				?
	
Datutil				l. 59 	addDate()					greg.add(Calendar.DATE, nbjour);		remove -10
