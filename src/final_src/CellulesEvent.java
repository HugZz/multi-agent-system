public class CellulesEvent extends Event
{
    private Cellules plateau;
    private EventManager manager;

    public CellulesEvent(Cellules plateau, long date, EventManager manager)
    {
        super(date);
        this.plateau = plateau;
        this.manager = manager;
    }

    public void execute()
    {
        this.plateau.actualiser();
        this.manager.addEvent(new CellulesEvent(this.plateau, super.getDate() + 1, this.manager));
    }
}
