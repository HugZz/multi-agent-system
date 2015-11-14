import java.awt.Color;
import gui.*;
/**
 * Cette classe correspond à la partie "affichage" des
 * balles et des boids.
 * @author Lucas Mahieu
 * @author Hugues de Valon
 */
public class BBSimulator extends Simulator {
    /**
     * Groupes de balles.
     */
    private Balls[] balls;
    /**
     * Rayon d'une balle.
     */
    private static int r = 8;
    /**
     * Tableau des couleurs.
     */
	private static Color[] color;

    /**
     * Accesseur sur les balles.
     * @return Tableau des balles.
     */
    public Balls[] getBalls() {
        return this.balls;
    }

    /**
     * Modifieur des balles.
     * @param b Balles à utiliser
     */
    public void setBalls(Balls[] b) {
        this.balls = b;
    }

    /**
     * Constructeur : initialise le GUI, le simulateur d'évènements, le premier évènement
     * , les balles et la table des couleurs.
     * @param simulator
     * @param balles
     * @param event
     * @param manager
     */
    public BBSimulator(GUISimulator simulator, Balls[] balles, Event event, EventManager manager) {
        super(simulator, event, manager);
        this.setBalls(balles);
		this.setColor();
    }
    /**
     * Cette méthode permet d'initialiser la table des couleurs.
     */
	public void setColor(){
		Color [] c = new Color[this.getBalls().length];
		for (int i=0; i<this.getBalls().length ;i++ ) {
			c[i] = new Color(0,0,0);
			c[i] = super.getColorEtat(i);
		}
		this.color = c;
	}

    /**
     * Cette méthode affiche à l'écran les boids ou les balles.
     */
    public void affiche() {
        for(int j = 0; j < this.balls.length; j++) {
            for(int i=0; i < this.getBalls()[j].getNbBalls(); i++) {
                super.getGUI().addGraphicalElement(new Oval(this.getBalls()[j].getPx(i),this.getBalls()[j].getPy(i),color[j],color[j],r));
            }
        }
    }

    /**
     * Ré-initialise la position par défaut des balles/boids.
     */
    public void reInit()
    {
        for(int i = 0; i < this.balls.length; i++) {
            this.getBalls()[i].reInit();
        }
    }
}
