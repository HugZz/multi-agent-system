public abstract class Event
{
    private long date;
    private EventManager manager;

    public Event(long date, EventManager manager)
    {
        this.date = date;
        this.manager = manager;
    }

    public long getDate()
    {
        return this.date;
    }

    public EventManager getManager() {
        return this.manager;
    }

    // Les fils de Event définissent réellement cette méthode.
    public abstract void execute();
}
