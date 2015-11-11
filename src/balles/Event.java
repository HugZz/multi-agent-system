public abstract class Event
{
    private long date;

    public Event(long date)
    {
        this.date = date;
    }

    public long getDate()
    {
        return this.date;
    }

    // Les fils de Event définissent réellement cette méthode.
    public abstract void execute();
}
