public class BBEvent extends Event {
    private Balls balls;
    private int delai;

    public BBEvent(Balles balls, long date, EventManager manager, int delai) {
        super(date, manager);
        this.balls = balls;
        this.delai = delai;
    }

    public void execute() {
        this.balls.actualiser();
        super.getManager().addEvent(new BBEvent(this.balls, super.getDate() + this.delai, this.manager, this.delai));
    }
}
