public class HabitationsEvent extends Event
{
    private Habitations plateau;
    private EventManager manager;

    public HabitationsEvent(Habitations plateau, long date, EventManager manager)
    {
        super(date);
        this.plateau = plateau;
        this.manager = manager;
    }

    public void execute()
    {
        this.plateau.actualiser();
        this.manager.addEvent(new HabitationsEvent(this.plateau, super.getDate() + 1, this.manager));
    }
}

