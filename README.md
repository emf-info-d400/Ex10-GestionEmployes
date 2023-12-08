# Exercice 11 : Gestion des employés
## durée : 240'
## Objectifs visés :
Maîtrise de MVC ; Maitriser les diagrammes de séquence ; Maitriser les diagrammes de classes ; Savoir se référer à la Javadoc.

## Travail à réaliser
Lisez avec attention les informations fournies ci-après sous diverses formes (diagramme de classe, javadoc, diagrammes de séquence, …) car vous y trouverez toutes les informations utiles afin de finaliser le projet Java NetBeans « **GestionEmployes** ».

## Résultat à obtenir
Si votre code est correctement implémenté selon les directives reçues, vous devriez obtenir une application fonctionnelle ressemblant à cela :

![interface](images/interface_final.png)

## Description des fonctionnalités de l'application
Les boutons permettant d’activer les différentes fonctionnalités de l’application sont automatiquement activés et désactivés de manière à ne permettre que les actions qui font sens (par exemple un employé qui est déjà en vacances ne peut plus être mis en vacances).

| Les boutons ci-dessous permettent de : | Confirmation en cas de réussite | Confirmation en cas d’échec|
|:---|:---:|:---:|
|![Engagement d'un employé](images/bouton1.png) <br>Permet d’engager l’employé dont le nom et prénom ont été saisis. |![Confirmation1](images/confirmation1.png)|![Echec1](images/echec1.png)| 
|![Licenciement d'un employé](images/bouton2.png) <br>Permet de licencier l’employé sélectionné dans la liste|![Confirmation2](images/confirmation2.png)|Le bouton est désactivé s’il n’y a aucun employé sélectionné. Aucune erreur ne devrait donc pouvoir se produire.|
|![Début des vacances](images/bouton3.png) <br>Permet de mettre en vacances l’employé sélectionné dans la liste|![Confirmation3](images/confirmation3.png)|Le bouton est désactivé s’il n’y a aucun employé sélectionné ou s’il est déjà en vacances. Aucune erreur ne devrait donc pouvoir se produire.|
|![Fin des vacances](images/bouton4.png) <br>Permet de remettre au travail l’employé sélectionné dans la liste|![Confirmation4](images/confirmation4.png)|Le bouton est désactivé s’il n’y a aucun employé sélectionné ou s’il n’est pas en vacances. Aucune erreur ne devrait donc pouvoir se produire.|
|![Timbrage début](images/bouton5.png) <br>Permet de saisir un timbrage de début de travail pour l’employé sélectionné dans la liste|![Confirmation5](images/confirmation5.png)|Le bouton est désactivé s’il n’y a aucun employé sélectionné ou s’il a déjà timbré son début de travail. Aucune erreur ne devrait donc pouvoir se produire.|
|![Timbrage fin](images/bouton6.png) <br>Permet de saisir un timbrage de fin de travail pour l’employé sélectionné dans la liste|![Confirmation6](images/confirmation6.png)|Le bouton est désactivé s’il n’y a aucun employé sélectionné ou s’il a déjà timbré sa fin de travail. Aucune erreur ne devrait donc pouvoir se produire.|
|![Quitter](images/bouton7.png) <br>Permet de quitter l’application|![Quitter](images/confirmation7.png)|L’application quittera si l’utilisateur répond « Oui ». S’il choisit « Non », la fenêtre sera fermée sans conséquences.|

**Autres boutons** :

![Rapport timbrage](images/bouton8.png) <br>Affiche un rapport de tous les timbrages de l’employé sélectionné dans la liste. Un résultat tel que ci-dessous sera produit dans la partie « Rapport » :
```
----------------------------------------------
Timbrages de TERRIEUR Alex
----------------------------------------------
Début du travail le 2016/04/24 21:22:56
Fin du travail le 2016/04/24 21:23:24
Début du travail le 2016/04/24 21:23:26
Fin du travail le 2016/04/24 21:23:33
Début du travail le 2016/04/24 21:23:34
Fin du travail le 2016/04/24 21:23:37
Début du travail le 2016/04/24 21:32:33
Fin du travail le 2016/04/24 21:32:43
Début du travail le 2016/04/24 21:45:51
Fin du travail le 2016/04/24 21:45:57
----------------------------------------------
En conséquence ses périodes de travail sont :
----------------------------------------------
2016/04/24 de 21:22:56 à 21:23:24 => 28 secondes
2016/04/24 de 21:23:26 à 21:23:33 => 7 secondes
2016/04/24 de 21:23:34 à 21:23:37 => 3 secondes
2016/04/24 de 21:32:33 à 21:32:43 => 10 secondes
2016/04/24 de 21:45:51 à 21:45:57 => 7 secondes
----------------------------------------------
Total du travail réalisé pour la paie : 00:00:55
```
---
![Rapport détails employés](images/bouton9.png) <br>Affiche un rapport de tous les employés de l’entreprise et leurs détails. Un résultat tel que ci-dessous sera produit dans la partie « Rapport » :
```
----------------------------------------------
Employés de l'entreprise
----------------------------------------------
BERNASCONI Maria, au travail, engagé le 2016/04/24
HARONI Mac, en vacances, engagé le 2016/04/24
D'OEUF John, au travail, engagé le 2016/04/24
TERRIEUR Alain, au travail, engagé le 2016/04/24
TERRIEUR Alex, au travail, engagé le 2016/04/24
```
---
![Rapport employés disponibles](images/bouton10.png) <br>Affiche un rapport de tous les employés de l’entreprise qui sont disponibles pour travailler. Un résultat tel que ci-dessous sera produit dans la partie « Rapport » :
```
----------------------------------------------
Employés disponibles
----------------------------------------------
BERNASCONI Maria
D'OEUF John
TERRIEUR Alain
TERRIEUR Alex
```
---
![Rapport employés vacances](images/bouton11.png) <br>Affiche un rapport de tous les employés de l’entreprise qui sont en vacances. Un résultat tel que ci-dessous sera produit dans la partie « Rapport » :
```
----------------------------------------------
Employés en vacances
----------------------------------------------
HARONI Mac
```
---


## Diagramme de classes

```mermaid 
classDiagram

class Application {
    main(String[] args) void$    
}

class ServiceEmploye {
    -Controller refController
    +ServiceEmploye()
    +employeEngager(Employe employe) boolean
    +employeLicencier(Employe employe) boolean
    +ajouterTimbrage(Timbrage timbrage) boolean
    +listerTimbragesDe(Employe employe) Timbrage[]
    +listerTousLesEmployes() Employe[]
    +listerEmployesDisponibles() Employe[]
    +listerEmployesEnVacances() Employe[]
    +calculerDureeEnHeuresMinutesSecondes(long temps) String
    +listerPeriodesDeTravailDe(Employe) PeriodeDeTravail[]
    +totalHMSDeTravailDe(Employe) String
    +getRefCtrl() Controller
    +setRefCtrl(Controller ctrl) void
}

class Controller {
    -Employe employeCourant
    -View refView
    -ServiceEmploye refService
    +Controller()
    +start() void
    +viewExiting() void
    +nouvelEmployeCourant(Employe employe) void
    +employeEngager(String nom, String prenom)
    +employeLicencier() void
    +employeEnVacances() void
    +employeAuTravail() void
    +employeTimbrageArrivee() void
    +employeTimbrageDepart() void
    +afficherTimbragesEmploye() void
    +afficherTousLesEmploye() void
    +afficherEmployesEnVacances() void
    +getRefServiceEmploye() ServiceEmploye
    +setRefServiceEmploye(ServiceEmploye service) void
    +getRefView() View
    +setRefView(View view) void
}

class View {
    -Controller refController
    +View()
    -viewExiting() void
    -initComponents() void
    -activerBoutons() void
    -insererTexteDansRapport(String texte) void
    +viewStart() void
    +afficherEmployes(Employe[] employes) void
    +debutNouveauRapport(String texte) void
    +nouvelleLigneDeRapport(String texte) void
    +finNouveauRapport(String texte) void
    +messageInformation(String message) void
    +messageErreur(String message) void
    +messageQuestion(String message) boolean
    +getRefCtrl() Controller
    +setRefCtrl(Controller ctrl)
    -jButtonQuitterActionPerformed(java.awt.event.ActionEvent event) void
    -jButtonEngagerActionPerformed(java.awt.event.ActionEvent event) void
    -jButtonLicencierActionPerformed(java.awt.event.ActionEvent event) void
    -jButtonEnVacancesActionPerformed(java.awt.event.ActionEvent event) void
    -jButtonAuTravailActionPerformed(java.awt.event.ActionEvent event) void
    -jButtonTimbrageArriveeActionPerformed(java.awt.event.ActionEvent event) void
    -jButtonTimbrageDepartActionPerformed(java.awt.event.ActionEvent event) void
    -jButtonAfficherTimbragesEmployeActionPerformed(java.awt.event.ActionEvent event) void
    -jButtonAfficherTousLesEmployesActionPerformed(java.awt.event.ActionEvent event) void
    -jButtonAfficherEmployesDisponiblesActionPerformed(java.awt.event.ActionEvent event) void
    -jButtonAfficherEmployesEnVacancesActionPerformed(java.awt.event.ActionEvent event) void
 }

Controller o--> "1" ServiceEmploye : -refController
ServiceEmploye o--> "1" Controller : -refService
View o--> "1" Controller : -refView
Controller o--> "1" View : -refController
```
## Diagramme de classes : le service "Employe" et les modèles

```mermaid 
classDiagram

class ServiceEmploye {
    -Entreprise entreprise
    -Controller refController
    +ServiceEmploye()
    +employeEngager(Employe employe) boolean
    +employeLicencier(Employe employe) boolean
    +ajouterTimbrage(Timbrage timbrage) boolean
    +listerTimbragesDe(Employe employe) Timbrage[]
    +listerTousLesEmployes() Employe[]
    +listerEmployesDisponibles() Employe[]
    +listerEmployesEnVacances() Employe[]
    +calculerDureeEnHeuresMinutesSecondes(long temps) String
    +listerPeriodesDeTravailDe(Employe) PeriodeDeTravail[]
    +totalHMSDeTravailDe(Employe) String
    +getRefCtrl() Controller
    +setRefCtrl(Controller ctrl) void
}

class Controller {
    -Employe employeCourant
    -View refView
    -ServiceEmploye refService
    +Controller()
    +start() void
    +viewExiting() void
    +nouvelEmployeCourant(Employe employe) void
    +employeEngager(String nom, String prenom)
    +employeLicencier() void
    +employeEnVacances() void
    +employeAuTravail() void
    +employeTimbrageArrivee() void
    +employeTimbrageDepart() void
    +afficherTimbragesEmploye() void
    +afficherTousLesEmploye() void
    +afficherEmployesEnVacances() void
    +getRefServiceEmploye() ServiceEmploye
    +setRefServiceEmploye(ServiceEmploye service) void
    +getRefView() View
    +setRefView(View view) void
}

class Entreprise {
    +MAX_EMPLOYE int$
    +MAX_TIMBRAGES int$
    -nbreTimbrages int
    -Employe[] employes
    +Entreprise()
    +ajouteEmploye(Employe employe) boolean
    +supprimeEmploye(Employe employe) boolean
    +ajouteTimbrage(Timbrage timbrage) boolean
    +nombreDeTimbragesDe(Employe employe) int
    +nombreEmployesDisponibles() int
    +nombreEmployesEnVacances() int
    +nombreEmployes() int
    +nombreTimbrages() int
    +trouveTimbragesDe(Employe employe) Timbrage[]
    +tousLesEmployes() Employe[]
    +tousLesEmployesDisponibles() Employe[]
    +tousLesEmployesEnVacances() Employe[]
    +getNbreTimbrages() int
}

class Employe {
    -String nom
    -String prenom
    -Date engageLe
    -boolean enVacances
    -boolean enTrainDeTravailler
    +Employe(String nom, String prenom, Date enageLe)
    +Employe(String nom, String prenom)
    +toString() String
    +getNom() String
    +getPrenom() String
    +getEngageLe() Date
    +isEnVacances() boolean
    +setEnVacances(boolean enVacances): void
    +isEnTrainDeTravailler() boolean
    +setEnTrainDeTravailler(boolean enTrainDeTravailler) void
}

class Timbrage {
    -Date quand
    -boolean debutDuTravail
    -Employe employe
    +Timbrage(Employe employe, Date quand, boolean debutDuTravail)
    +Timbrage(Employe employe, boolean debutDuTravail)
    +getEmploye() Employe
    +getQuand() Date
    +isDebutDuTravail() boolean
    +toString() String
}

class PeriodeDeTravail {
    -Timbrage debutPeriode
    -Timbrage finPeriode
    +PeriodeDeTravail(Timbrage debutPeriode)
    +PeriodeDeTravail(Timbrage debutPeriode, Timbrage finPeriode)
    +getDebutPeriode() Timbrage
    +setFinPeriode(Timbrage finPeriode) void
    +calculerDureeEnSecondes() long
    +toString() String
}

Controller o--> "1" ServiceEmploye : -refController
ServiceEmploye o--> "1" Controller : -refService
ServiceEmploye o--> "1" Entreprise : -entreprise
Controller o--> "1" Employe : -employeCourant
Entreprise o--> "0..*" Employe : -employes
Timbrage o--> "1" Employe : -employe
PeriodeDeTravail o--> "1" Timbrage : -debutPeriode
PeriodeDeTravail o--> "1" Timbrage : -finPeriode
```
### Structure des packages Java
Voici la structure des packages pour chaque classe du projet
```mermaid
classDiagram
namespace app {
    class Application
}
namespace ctrl {
    class Controller
}
namespace service {
    class ServiceEmploye
    class Entreprise
}
namespace models {
    class Employe
    class Timbrage
    class PeriodeDeTravail
}
namespace view {
    class View
}
```
### Diagramme de séquence
Voici le diagramme de séquence de la méthode de la méthode `main()` de la classe `Application` du package `app` :
```mermaid
sequenceDiagram
    participant main
    main->>Controller: new Controller()
    Controller-->>main: refController=
    main->>ServiceEmploye: new ServiceEmploye()
    ServiceEmploye-->>main: refService=
    main->>Controller: setRefServiceEmploye(refService)
    main->>View: new View()
    View-->>main: refView=
    main->>Controller: setRefView(refView)
    main->>View: setRefCtrl(refController)
    main->>ServiceEmploye: setRefCtrl(refController)
    main->>Controller: start()
```
### Javadoc
Vous pouvez cliquer sur [ce lien pour obtenir la JavaDoc en HTML](javadoc/index.html) de l'application **GestionEmployes**.
