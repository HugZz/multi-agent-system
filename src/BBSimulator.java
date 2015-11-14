import java.awt.Color;
import gui.*;
public class BBSimulator extends Simulator {
    private Balls[] balls;
    private static int r = 8;
	private static Color[] color;

    public Balls[] getBalls() {
        return this.balls;
    }

    public void setBalls(Balls[] b) {
        this.balls = b;
    }

    public BBSimulator(GUISimulator simulator, Balls[] balles, Event event, EventManager manager) {
        super(simulator, event, manager);
        this.setBalls(balles);
		this.setColor();
    }
	public void setColor(){
		Color [] c = new Color[this.getBalls().length];
		for (int i=0; i<this.getBalls().length ;i++ ) {
			c[i] = new Color(0,0,0);
			c[i] = super.getColorEtat(i);
		}
		this.color = c;
	}

    public void affiche() {
        for(int j = 0; j < this.balls.length; j++) {
            for(int i=0; i < this.getBalls()[j].getNbBalls(); i++) {
                super.getGUI().addGraphicalElement(new Oval(this.getBalls()[j].getPx(i),this.getBalls()[j].getPy(i),color[j],color[j],r));
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
