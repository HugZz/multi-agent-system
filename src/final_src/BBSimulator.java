public class BBSimulator extends Simulator {
    private Balls balls;
    private static int r = 10;

    public Balls getBalls() {
        return this.balls;
    }

    public void setBalls(Balls b) {
        this.balls = b;
    }

    public BBSimulator(int n, GUISimulator simulator, Balles balles) {
        super(simulator);
        this.setBalls(balles);
    }

    public void affiche() {
        for(int i=0; i < this.getBalls().getNbBalls(); i++) {
            super.getGUI().addGraphicalElement(new Oval(this.getBalls().getPx(i),this.getBalls().getPy(i),Color.BLUE,Color.RED,r));
        }
    }

    public void reInit()
    {
        this.getBalls().reInit();
    }

    public void addEvent(Event toAdd)
    {
        super.getManager().addEvent(toAdd);
    }
}
