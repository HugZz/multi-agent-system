public class CellulesEvent extends Event {
    private Cellules plateau;

    public CellulesEvent(Cellules plateau, long date, EventManager manager) {
        super(date, manager);
        this.plateau = plateau;
    }

    public void execute() {
        this.plateau.actualiser();
        super.getManager().addEvent(new CellulesEvent(this.plateau, super.getDate() + 1, this.getManager()));
    }
}
