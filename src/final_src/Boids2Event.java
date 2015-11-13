public class BoidsEvent extends Event
{
    private Boids boids;
    private EventManager manager;
    private int delai;

    public BoidsEvent(Boids boids, long date, EventManager manager, int delai)
    {
        super(date);
        this.boids = boids;
        this.manager = manager;
        this.delai = delai;
    }

    public void execute()
    {
        this.boids.step();
        this.manager.addEvent(new BoidsEvent(this.boids, super.getDate() + delai, this.manager, delai));
    }
}
