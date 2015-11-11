public class BoidsEvent extends Event
{
    private Boids boids;
    private EventManager manager;

    public BoidsEvent(Boids boids, long date, EventManager manager)
    {
        super(date);
        this.boids = boids;
        this.manager = manager;
    }

    public void execute()
    {
        this.boids.step();
        this.manager.addEvent(new BoidsEvent(this.boids, super.getDate() + 1, this.manager));
    }
}
