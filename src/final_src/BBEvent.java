public class BBEvent extends Event {
    private Balls[] balls;
    private int[] delai;
    private int groupe;

    public BBEvent(Balls[] balls, long date, EventManager manager, int[] delai, int groupe) {
        super(date, manager);
        this.balls = balls;
        this.delai = delai;
        this.groupe = groupe;
    }

    public void execute() {
        if (this.groupe == -1)
        {
            for(int i = 0; i < this.balls.length; i++) {
                this.balls[i].actualiser();
                super.getManager().addEvent(new BBEvent(this.balls, super.getDate() + this.delai[i], this.getManager(), this.delai, i));
            }
        }
        else
        {
            this.balls[this.groupe].actualiser();
            super.getManager().addEvent(new BBEvent(this.balls, super.getDate() + this.delai[this.groupe], this.getManager(), this.delai, this.groupe));
        }
    }
}
