import java.util.*

public class EventManager
{
    private long currentDate;
    private SortedSet<Event> eventsParDate;

    public EventManager(Event e)
    {
        this.eventsParDate = new TreeSet<Event> (new ComparateurDate());
        this.eventsParDate.add(e);
        this.currentDate = 0;
    }

    public void next()
    {
        this.currentDate++;
        for(Event index : eventsParDate)
        {
            if(index.getDate() <= this.currentDate)
            {
                index.execute();
            }
            else
                break;
        }
    }

    public boolean isFinished()
    {
        return (eventsParDate.last().getDate() <= this.currentDate)?true:false;
    }

    public void restart()
    {
        this.currentDate = 0;
    }
}




