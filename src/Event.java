/**
 * Représente de façon abstraire tous les événements.
 * @author Lucas Mahieu
 * @author Hugues de Valon
 */
public abstract class Event
{
    /**
     * Date d'execution de l'événement.
     */
    private long date;
    /**
     * Manager de tous les événements afin que les événements eux mêmes puissent
     * en rajouter.
     */
    private EventManager manager;

    /**
     * Constructeur : initialise date d'execution et manager des événements.
     * @param date Date d'execution de l'événement
     * @param manager Manager de tous les événements
     */
    public Event(long date, EventManager manager)
    {
        this.date = date;
        this.manager = manager;
    }

    /**
     * @return Renvoie la date d'execution de l'événement
     */
    public long getDate()
    {
        return this.date;
    }

    /**
     * @return Renvoie le manager d'événements.
     */
    public EventManager getManager() {
        return this.manager;
    }

    /**
     * Les fils définissent réellement l'action à executer à chaque
     * événement.
     */
    public abstract void execute();
}
