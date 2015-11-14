import java.awt.Color;
import gui.*;
public class BBSimulator extends Simulator {
    private Balls[] balls;
    private static int r = 10;

    public Balls[] getBalls() {
        return this.balls;
    }

    public void setBalls(Balls[] b) {
        this.balls = b;
    }

    public BBSimulator(GUISimulator simulator, Balls[] balles, Event event, EventManager manager) {
        super(simulator, event, manager);
        this.setBalls(balles);
    }

    public void affiche() {
        for(int j = 0; j < this.balls.length; j++) {
            for(int i=0; i < this.getBalls()[j].getNbBalls(); i++) {
                super.getGUI().addGraphicalElement(new Oval(this.getBalls()[j].getPx(i),this.getBalls()[j].getPy(i),Color.BLUE,Color.RED,r));
            }
        }
    }

    public void reInit()
    {
        for(int i = 0; i < this.balls.length; i++) {
            this.getBalls()[i].reInit();
        }
    }
}
