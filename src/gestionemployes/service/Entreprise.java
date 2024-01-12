package gestionemployes.service;

/**
 * Classe représentant une Entreprise pour l'application MVC "Gestion d'employés" du module D400.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @since 01.12.2023
 * @version 1.1.0
 */
public class Entreprise {

    /**
     * Constante qui représente le nombre maximum d'employé que l'entreprise sera en mesure de gérer.
     */
    // VOTRE CODE ICI...

    /**
     * Constante qui représente le nombre maximum de timbrages que l'entreprise sera en mesure de gérer.
     */
    // VOTRE CODE ICI...

    /**
     * Attribut représentant les employés de l'entreprise non modifiable.
     */
    // VOTRE CODE ICI...

    /**
     * Attribut représentant les timbrages de l'entreprise non modifiable.
     */
    // VOTRE CODE ICI...

    /**
     * Attribut représentant le nombre de timbrages présents dans l'attribut "timbrages".
     */
    // VOTRE CODE ICI...

    /**
     * Constructeur de la classe Entreprise.
     */
    public Entreprise() {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode tente d'ajouter l'employé fourni dans la liste des employés de l'entreprise.
     *
     * @param employe l'employé à ajouter
     *
     * @return vrai si et seulement si l'employé a trouvé une place dans la liste des employés
     */
    public boolean ajouteEmploye( Employe employe ) {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode tente de supprimer l'employé fourni de la liste des employés de l'entreprise.
     *
     * @param employe l'employé à supprimer
     *
     * @return vrai si et seulement si l'employé a pu être supprimé de la liste des employés
     */
    public boolean supprimeEmploye( Employe employe ) {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode tente d'ajouter le timbrage fourni à la liste des timbrages de l'entreprise.
     *
     * @param timbrage timbrage à ajouter
     *
     * @return vrai si et seulement si le timbrage a trouvé une place dans la liste des timbrages
     */
    public boolean ajouteTimbrage( Timbrage timbrage ) {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode compte le nombre de timbrages qui concernent l'employé fourni.
     *
     * @param employe l'employé pour lequel on veut compter le nombre de timbrage
     *
     * @return le nombre de timbrages qui concernent cet employé
     */
    public int nombreDeTimbragesDe( Employe employe ) {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode compte le nombre d'employés de l'entreprise qui sont disponibles, c'est-à-dire qui ne sont pas en
     * vacances.
     *
     * @return le nombre d'employés de l'entreprise qui sont disponibles
     */
    public int nombreEmployesDisponibles() {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode compte le nombre d'employés de l'entreprise qui sont en vacances.
     *
     * @return le nombre d'employés de l'entreprise qui sont en vacances
     */
    public int nombreEmployesEnVacances() {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode compte le nombre total d'employés de l'entreprise.
     *
     * @return le nombre total d'employés de l'entreprise
     */
    public int nombreEmployes() {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode retourne le nombre total de timbrages.
     *
     * @return le nombre total de timbrages
     */
    public int nombreTimbrages() {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode retourne la liste des timbrages de l'employé fourni. La liste retournée aura la bonne taille (donc
     * pas de null dans le tableau). Pensez à réutiliser les méthodes déjà écrites pour compter le nombre de timbrages.
     *
     * @param employe l'employé dont on veut la liste de tous les timbrages
     *
     * @return la liste des timbrages de cet employé fourni (un tableau de taille nulle si cet employé n'a aucun
     * timbrage)
     */
    public Timbrage[] trouveTimbragesDe( Employe employe ) {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode retourne la liste de tous les employés de l'entreprise. La liste retournée aura la bonne taille
     * (donc pas de null dans le tableau). Pensez à réutiliser les méthodes déjà écrites pour compter le nombre
     * d'employés.
     *
     * @return la liste de tous les employés de l'entreprise (un tableau de taille nulle s'il n'y a encore aucun
     * employé)
     */
    public Employe[] tousLesEmployes() {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode retourne la liste de tous les employés de l'entreprise qui sont disponibles. La liste retournée
     * aura la bonne taille (donc pas de null dans le tableau). Pensez à réutiliser les méthodes déjà écrites pour
     * compter le nombre d'employés disponibles.
     *
     * @return la liste de tous les employés de l'entreprise qui sont disponibles (un tableau de taille nulle s'il n'y
     * en a pas)
     */
    public Employe[] tousLesEmployesDisponibles() {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode retourne la liste de tous les employés de l'entreprise qui sont en vacances. La liste retournée
     * aura la bonne taille (donc pas de null dans le tableau). Pensez à réutiliser les méthodes déjà écrites pour
     * compter le nombre d'employés en vacances.
     *
     * @return la liste de tous les employés de l'entreprise qui en vacances (un tableau de taille nulle s'il n'y en a
     * pas)
     */
    public Employe[] tousLesEmployesEnVacances() {
        // VOTRE CODE ICI...
    }

    /**
     * Getter de l'attribut "nombre de timbrages".
     *
     * @return l'attribut "nombre de timbrages"
     */
    public int getNbreTimbrages() {
        // VOTRE CODE ICI...
    }



}
