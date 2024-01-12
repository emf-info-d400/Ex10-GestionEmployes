package gestionemployes.models;

/**
 * Classe représentant un bean PeriodeDeTravail pour l'application MVC "Gestion
 * d'employés" du module D400.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @since 01.12.2023
 * @version 1.1.0
 */
public class PeriodeDeTravail {

    /**
     * Attribut timbrage représentant le début de la période travaillée non modifiable.
     */
    // VOTRE CODE ICI...

    /**
     * Attribut timbrage représentant la fin de la période travaillée.
     */
    // VOTRE CODE ICI...

    /**
     * Constructeur de la classe PeriodeDeTravail. Dans cette version du
     * constructeur qui ne reçoit qu'un seul timbrage,
     * on utilise également ce timbrage pour initialiser la fin de la période.
     *
     * @param debutPeriode timbrage représentant le début de la période travaillée
     */
    public PeriodeDeTravail(Timbrage debutPeriode) {
        // VOTRE CODE ICI...
    }

    /**
     * Constructeur de la classe PeriodeDeTravail.
     *
     * @param debutPeriode timbrage représentant le début de la période travaillée
     * @param finPeriode   timbrage représentant la fin de la période travaillée
     */
    public PeriodeDeTravail(Timbrage debutPeriode, Timbrage finPeriode) {
        // VOTRE CODE ICI...
    }

    /**
     * Getter de l'attribut timbrage du début de la période travaillée.
     *
     * @return le timbrage du début de la période travaillée
     */
    public Timbrage getDebutPeriode() {
        // VOTRE CODE ICI...
    }

    /**
     * Getter de l'attribut timbrage de la fin de la période travaillée.
     *
     * @return le timbrage de la fin de la période travaillée
     */
    public Timbrage getFinPeriode() {
        // VOTRE CODE ICI...
    }

    /**
     * Setter de l'attribut timbrage de la fin de la période travaillée.
     *
     * @param finPeriode le timbrage de la fin de la période travaillée
     */
    public void setFinPeriode(Timbrage finPeriode) {
        // VOTRE CODE ICI...
    }

    /**
     * Cette méthode calcule la durée en seconde qui sépare les deux timbrages de la
     * période de travail. La méthode part
     * du principe (=ne vérifie pas) que finPeriode est bien un timbrage plus récent
     * que debutPeriode. Pour calculer la
     * différence en secondes, l'algorithme suivant est utilisé. On commence par
     * soustraire la valeur retournée par
     * leurs méthodes getTime() respectives. Cela nous donne des milli-secondes. On
     * y rajoute 999 (afin de compter
     * complètement les secondes à peine entamées). Pour finir on divise le tout par
     * 1000 afin d'avoir des secondes.
     *
     * @return le nombre de secondes qui sépare les deux timbrages de cette période
     *         de travail
     */
    public long calculerDureeEnSecondes() {
        // VOTRE CODE ICI...
    }

    /**
     * Surchage de la méthode toString() qui affiche la date du timbrage de début,
     * puis les heures des deux timbrages et
     * le nombre de secondes écoulées entre les deux timbrages de cette période de
     * travail. Le format à utiliser est le
     * suivant (ici directement avec une date/heure d'exemple) : "2016/04/24 de
     * 17:15:20 à 17:15:24 =&gt; 4 secondes".
     *
     * @return une chaîne de caractères formatée comme précisé ci-dessus
     */
    @Override
    public String toString() {
        // VOTRE CODE ICI...
    }

}
