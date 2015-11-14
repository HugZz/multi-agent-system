/**
 * Cette classe correspond à l'évènement executés par les cellules pour les trois jeux.
 * @author Lucas Mahieu
 * @author Hugues de Valon
 */
public class CellulesEvent extends Event {
    /**
     * Plateau des cellules
     */
    private Cellules plateau;

    /**
     * Constructeur : Initialise date d'execution, manager des évènements et
     * plateau de Celulles.
     * @param plateau Plateau de cellules
     * @param date Date d'execution de l'évènement
     * @param manager Manager des évènements
     */
    public CellulesEvent(Cellules plateau, long date, EventManager manager) {
        super(date, manager);
        this.plateau = plateau;
    }

    /**
     * Méthode d'execution des évènements et d'ajout d'un nouvel évènement.
     */
    public void execute() {
        this.plateau.actualiser();
        super.getManager().addEvent(new CellulesEvent(this.plateau, super.getDate() + 1, this.getManager()));
    }
}
