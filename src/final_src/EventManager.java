import java.util.PriorityQueue;

public class EventManager
{
    private long currentDate;
    private PriorityQueue<Event> eventsParDate;

    public EventManager()
    {
        this.eventsParDate = new PriorityQueue<Event> (new ComparateurDate());
        this.currentDate = 0;
    }

    public void addEvent(Event toAdd)
    {
        this.eventsParDate.add(toAdd);
    }

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

    public boolean isFinished()
    {
        return eventsParDate.isEmpty();
    }

    public void restart()
    {
        this.currentDate = 0;
        this.eventsParDate.clear();
    }
}
