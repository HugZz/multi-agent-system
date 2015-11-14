/**
 * Représente de façon abstraire tous les évènements.
 * @author Lucas Mahieu
 * @author Hugues de Valon
 */
public abstract class Event
{
    /**
     * Date d'execution de l'évènement.
     */
    private long date;
    /**
     * Manager de tous les évènements afin que les évènements eux mêmes puissent
     * en rajouter.
     */
    private EventManager manager;

    /**
     * Constructeur : initialise date d'execution et manager des évènements.
     * @param date Date d'execution de l'évènement
     * @param manager Manager de tous les évènements
     */
    public Event(long date, EventManager manager)
    {
        this.date = date;
        this.manager = manager;
    }

    /**
     * @return Renvoie la date d'execution de l'évènement
     */
    public long getDate()
    {
        return this.date;
    }

    /**
     * @return Renvoie le manager d'évènements.
     */
    public EventManager getManager() {
        return this.manager;
    }

    // Les fils de Event définissent réellement cette méthode.
    /**
     * Les fils définissent réellement l'action à executer à chaque
     * évènement.
     */
    public abstract void execute();
}
