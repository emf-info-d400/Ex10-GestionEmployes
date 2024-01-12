package gestionemployes.models;

/**
 * Classe représentant un bean Timbrage pour l'application MVC "Gestion
 * d'employés" du module D400.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @since 01.12.2023
 * @version 1.1.0
 */
public class Timbrage {

    /**
     * Attribut employé concerné par ce timbrage non modifiable.
     */
    // VOTRE CODE ICI...

    /**
     * Attribut date/heure de ce timbrage non modifiable.
     */
    // VOTRE CODE ICI...

    /**
     * Attribut est-ce un timbrage d'arrivée au travail (ou de départ) ?
     * Attribut non modifiable.
     */
    // VOTRE CODE ICI...

    /**
     * Constructeur de la classe Timbrage fournissant une valeur à tous ses
     * attributs.
     *
     * @param employe        l'employé concerné par ce timbrage
     * @param quand          la date/heure du timbrage
     * @param debutDuTravail est-ce un timbrage d'arrivée au travail (ou de départ)
     *                       ?
     */
    public Timbrage(Employe employe, Date quand, boolean debutDuTravail) {
        // VOTRE CODE ICI...
    }

    /**
     * Constructeur de la classe Timbrage. Pour la date du timbrage qui n'est pas
     * fournie on part du principe qu'il
     * vient d'être effectué à l'instant.
     *
     * @param employe        l'employé concerné par ce timbrage
     * @param debutDuTravail est-ce un timbrage d'arrivée au travail (ou de départ)
     *                       ?
     */
    public Timbrage(Employe employe, boolean debutDuTravail) {
        // VOTRE CODE ICI...
    }

    /**
     * Getter de l'attribut employé concerné par ce timbrage.
     *
     * @return l'employé concerné par ce timbrage
     */
    public Employe getEmploye() {
        // VOTRE CODE ICI...
    }

    /**
     * Getter de l'attribut date/heure du timbrage.
     *
     * @return la date/heure du timbrage
     */
    public Date getQuand() {
        // VOTRE CODE ICI...
    }

    /**
     * Getter de l'attribut est-ce un timbrage d'arrivée au travail (ou de départ)
     * ?.
     *
     * @return est-ce un timbrage d'arrivée au travail (ou de départ) ?
     */
    public boolean isDebutDuTravail() {
        // VOTRE CODE ICI...
    }

    /**
     * Surchage de la méthode toString() qui affiche si c'est un timbrage de début
     * ou de fin de période de travail suivi
     * de la date et l'heure de ce timbrage. Le format à utiliser est le suivant
     * (ici directement donné avec deux
     * exemples pour les deux cas début/fin) : "Début du travail le 2016/04/24
     * 17:15:20" et "Fin du travail le
     * 2016/04/24 17:15:24".
     *
     * @return une chaîne de caractères formatée comme précisé ci-dessus
     */
    @Override
    public String toString() {
        // VOTRE CODE ICI...
    }

}
