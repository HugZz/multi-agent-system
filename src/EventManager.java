import java.util.PriorityQueue;

/**
 * EventManager gère tous les évènements des systèmes multi-agents.
 *
 * @author Lucas Mahieu
 * @author Hugues de Valon
 */

public class EventManager
{
    /**
     * Date actuelle d'execution.
     */
    private long currentDate;
    /**
     * Liste des évènements triés par leur date d'execution dans l'ordre croissant.
     */
    private PriorityQueue<Event> eventsParDate;

    /**
     * Constructeur : permet d'initialiser la date d'execution et de créer la liste.
     */
    public EventManager()
    {
        this.eventsParDate = new PriorityQueue<Event> (new ComparateurDate());
        this.currentDate = 0;
    }

    /**
     * Ajoute l'évènement à la liste.
     * @param toAdd Evenement à ajouter
     */
    public void addEvent(Event toAdd)
    {
        this.eventsParDate.add(toAdd);
    }

    /**
     * Incrémente le temps de 1 et vérifie s'il y a un évènement à executer.
     */
    public void next()
    {
        Event toExecute;
        this.currentDate++;
        //System.out.println("next ... date = " + this.currentDate);

        while(eventsParDate.peek().getDate() <= this.currentDate)
        {
            toExecute = eventsParDate.poll();
            toExecute.execute();

            if (this.isFinished())
                return;
        }
    }


    /**
     * Vérifie si il n'y a plus d'évènements à executer.
     * @return 1 si c'est fini, 0 sinon
     */
    public boolean isFinished()
    {
        return eventsParDate.isEmpty();
    }

    /**
     * Ré-initialise la date et la liste.
     */
    public void restart()
    {
        this.currentDate = 0;
        this.eventsParDate.clear();
    }
}
