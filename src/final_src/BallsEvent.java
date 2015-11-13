public class BallsEvent extends Event
{
    private Balls balls;
    private EventManager manager;

    public BallsEvent(Balls balls, long date, EventManager manager)
    {
        super(date);
        this.balls = balls;
        this.manager = manager;
    }

    public void execute()
    {
        this.balls.execute();
        this.manager.addEvent(new BallsEvent(this.balls, super.getDate() + 1, this.manager));
    }
}
