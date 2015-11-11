public class Cellules2Event extends Event
{
    private Cellules2 plateau;
    private EventManager manager;

    public Cellules2Event(Cellules2 plateau, long date, EventManager manager)
    {
        super(date);
        this.plateau = plateau;
        this.manager = manager;
    }

    public void execute()
    {
        this.plateau.actualiser();
        this.manager.addEvent(new Cellules2Event(this.plateau, super.getDate() + 1, this.manager));
    }
}

