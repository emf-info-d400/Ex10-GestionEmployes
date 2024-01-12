package gestionemployes.ctrl;

/**
 * Classe représentant le contrôleur de l'application MVC "Gestion d'employés"
 * du module D400.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @since 01.12.2023
 * @version 1.1.0
 */
public class Controller {

    /**
     * Référence au worker principal de l'application.
     */
    // VOTRE CODE ICI...

    /**
     * Référence à la vue principale de l'application.
     */
    // VOTRE CODE ICI...

    /**
     * Attribut qui représente l'employé courant (celui actuellement sélectionné
     * dans la vue).
     */
    // VOTRE CODE ICI...

    /**
     * Le constructeur de la classe Controller. Toujours initialiser TOUS les
     * attributs !
     */
    public Controller() {
        // VOTRE CODE ICI...
    }

    /**
     * Méthode de démarrage de l'application MVC. La méthode commence par appeler la
     * méthode viewStart() de la vue afin
     * de la démarrer.
     */
    public void start() {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode est appelée par la vue pour indiquer que l'application est en
     * train de se fermer. Cela permettrait
     * de sauvegarder l'état de l'application, mais ici il n'y a rien à faire..
     */
    public void viewExiting() {
    }

    /**
     * Méthode appelée par la vue lorsque la sélection courante dans la liste des
     * employé a été modifiée.
     *
     * @param employeSelectionne le nouvel employé courant ou null si aucun n'est
     *                           sélectionné
     */
    public void nouvelEmployeCourant(Employe employeSelectionne) {
        // VOTRE CODE ICI...
    }

    /**
     * Méthode appelée par la vue afin que soit créé un nouvel employé.
     *
     * @param nom    le nom du nouvel employé
     * @param prenom le prénom du nouvel employé
     */
    public void employeEngager(String nom, String prenom) {
        // VOTRE CODE ICI...
    }

    /**
     * Méthode appelée par la vue afin que l'employé courant soit licencié.
     */
    public void employeLicencier() {
        // VOTRE CODE ICI...
    }

    /**
     * Méthode appelée par la vue afin que l'employé courant soit mis en vacances.
     */
    public void employeEnVacances() {
        // VOTRE CODE ICI...
    }

    /**
     * Méthode appelée par la vue afin que l'employé courant soit mis au travail.
     */
    public void employeAuTravail() {
        // VOTRE CODE ICI...
    }

    /**
     * Méthode appelée par la vue afin que l'employé bénéficie d'un nouveau timbrage
     * de début de travail.
     */
    public void employeTimbrageArrivee() {
        // VOTRE CODE ICI...
    }

    /**
     * Méthode appelée par la vue afin que l'employé bénéficie d'un nouveau timbrage
     * de fin de travail.
     */
    public void employeTimbrageDepart() {
        // VOTRE CODE ICI... 
    }

    /**
     * Méthode appelée par la vue afin de produire le rapport "tous les timbrages de
     * l'employé sélectionné".
     */
    public void afficherTimbragesEmploye() {
        // VOTRE CODE ICI...
    }

    /**
     * Méthode appelée par la vue afin de produire le rapport "tous les employés de
     * l'entreprise et leur status".
     */
    public void afficherTousLesEmploye() {
        // VOTRE CODE ICI...
    }

    /**
     * Méthode appelée par la vue afin de produire le rapport "tous les employés de
     * l'entreprise qui sont disponibles".
     */
    public void afficherEmployesDisponibles() {
        // VOTRE CODE ICI...
    }

    /**
     * Méthode appelée par la vue afin de produire le rapport "tous les employés de
     * l'entreprise qui sont en vacances".
     */
    public void afficherEmployesEnVacances() {
        // VOTRE CODE ICI...
    }

    /**
     * Getter de la référence au service Employe de l'application.
     *
     * @return la référence au worker de l'application
     */
    public ServiceEmploye getRefService() {
        // VOTRE CODE ICI...
    }

    /**
     * Setter de la référence au service Employe de l'application.
     *
     * @param refService la référence au service Employe de l'application
     */
    public void setRefService(ServiceEmploye refService) {
        this.refService = refService;
    }

    /**
     * Getter de la référence à la vue de l'application.
     *
     * @return la référence à la vue de l'application
     */
    public View getRefView() {
        return refView;
    }

    /**
     * Setter de la référence à la vue de l'application.
     *
     * @param refView la référence à la vue de l'application
     */
    public void setRefView(View refView) {
        this.refView = refView;
    }

}
