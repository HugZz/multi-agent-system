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
        // L'événement est ajouté à la queue suivant sa date d'execution.
        this.eventsParDate.add(toAdd);
    }

    /**
     * Incrémente le temps de 1 et vérifie s'il y a un évènement à executer.
     */
    public void next()
    {
        Event toExecute;
        // On incrémente le temps global.
        this.currentDate++;

        // On vérifie sur le premier élément de la liste (celui qui a la date d'execution
        // la plus petite) que sa date est inférieure à la date globale.
        // Dans ce cas, on l'execute.
        while(eventsParDate.peek().getDate() <= this.currentDate)
        {
            // poll permet de supprimer l'événement.
            toExecute = eventsParDate.poll();
            toExecute.execute();

            // Permet de ne pas refaire la condition de boucle si on vient
            // de supprimer le dernier événement.
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
        // On utilise la méthode déja présente dans la collection.
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
