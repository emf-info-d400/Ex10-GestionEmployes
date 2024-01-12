package gestionemployes.models;

/**
 * Classe représentant un bean Employé pour l'application MVC "Gestion
 * d'employés" du module D400.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @since 01.12.2023
 * @version 1.1.0
 */
public class Employe {

    /**
     * Attribut nom de l'employé non modifiable.
     */
    // VOTRE CODE ICI...

    /**
     * Attribut prénom de l'employé non modifiable.
     */
    // VOTRE CODE ICI...

    /**
     * Attribut date d'engagement de l'employé non modifiable.
     */
    // VOTRE CODE ICI...

    /**
     * Attribut l'employé est-il en vacances ?.
     */
    // VOTRE CODE ICI...

    /**
     * Attribut "l'employé est-il en train de travailler ?".
     */
    // VOTRE CODE ICI...

    /**
     * Constructeur de la classe Employé. Dans cette version du constructeur deux
     * informations ne sont pas fournies : si
     * l'employé est en vacances ou non et si l'employé est en train de travailler.
     * Ce constructeur part du principe
     * qu'il n'est pas en vacances et qu'il n'est pas en train de travailler. Le nom
     * de l'employé est transformé
     * complètement en majuscules. Le prénom de l'employé est transformé avec sa
     * première lettre en majuscules et le
     * reste en minuscules.
     *
     * @param nom      le nom de l'employé
     * @param prenom   le prénom de l'employé
     * @param engageLe la date à laquelle il a été engagé
     */
    public Employe(String nom, String prenom, Date engageLe) {
        // VOTRE CODE ICI...
    }

    /**
     * Constructeur de la classe Employé. Dans cette version du constructeur trois
     * informations ne sont pas fournies :
     * si l'employé est en vacances ou non, si l'employé est en train de travailler,
     * sa date d'engagement. Ce
     * constructeur part du principe qu'il n'est pas en vacances, qu'il n'est pas en
     * train de travailler, qu'il vient
     * d'être engagé à l'instant. Le nom de l'employé est transformé complètement en
     * majuscules. Le prénom de l'employé
     * est transformé avec sa première lettre en majuscules et le reste en
     * minuscules.
     *
     * @param nom    le nom de l'employé
     * @param prenom le prénom de l'employé
     */
    public Employe(String nom, String prenom) {
        // VOTRE CODE ICI...
    }

    /**
     * Surchage de la méthode toString() qui affiche le nom de l'employé, suivi d'un
     * espace, suivi du prénom de
     * l'employé.
     *
     * @return une chaîne de caractères formatée comme précisé ci-dessus
     */
    @Override
    public String toString() {
        // VOTRE CODE ICI...
    }

    /**
     * Getter du nom de l'employé.
     *
     * @return le nom de l'employé
     */
    public String getNom() {
        // VOTRE CODE ICI...
    }

    /**
     * Getter du prénom de l'employé.
     *
     * @return le prénom de l'employé
     */
    public String getPrenom() {
        // VOTRE CODE ICI...
    }

    /**
     * Getter de la date d'engagement de l'employé.
     *
     * @return la date d'engagement de l'employé
     */
    public Date getEngageLe() {
        // VOTRE CODE ICI...
    }

    /**
     * Getter de l'attribut "l'employé est-il en vacances ?".
     *
     * @return l'employé est-il en vacances ?
     */
    public boolean isEnVacances() {
        // VOTRE CODE ICI...
    }

    /**
     * Getter de l'attribut "l'employé est-il en vacances ?".
     *
     * @param enVacances l'employé est-il en vacances ?
     */
    public void setEnVacances(boolean enVacances) {
        // VOTRE CODE ICI...
    }

    /**
     * Getter de l'attribut "l'employé est-il en train de travailler ?".
     *
     * @return l'employé est-il en train de travailler ?
     */
    public boolean isEnTrainDeTravailler() {
        // VOTRE CODE ICI...
    }

    /**
     * Setter de l'attribut "l'employé est-il en train de travailler ?".
     *
     * @param enTrainDeTravailler l'employé est-il en train de travailler
     */
    public void setEnTrainDeTravailler(boolean enTrainDeTravailler) {
        // VOTRE CODE ICI...
    }
}
