import gui.*;
import java.awt.Color;

public class BoidsSimulator implements gui.Simulable {
    private Boids[] boids;
    private GUISimulator guiBoids;
    private static final int r=10 ;
    private EventManager manager;
    private int[] delai;
    private int nombreGroupes;

    public BoidsSimulator(int n, int nombreGroupes, int[] delaiInit, GUISimulator g){
        this.nombreGroupes = nombreGroupes;
        this.delai = new int[nombreGroupes];
        this.boids = new Boids[nombreGroupes];
        for(int i = 0; i < nombreGroupes; i++) {
            boids[i] = new Boids(n);
            this.delai[i] = delaiInit[i];
        }
        this.setGuiBoids(g);
        this.manager = new EventManager();
        for(int i = 0; i < nombreGroupes; i++)
            this.manager.addEvent(new BoidsEvent(this.boids[i], 1, manager, this.delai[i]));
    }

    public Boids[] getBoids(){
        return this.boids;
    }
    public GUISimulator getGuiBoids(){
        return this.guiBoids;
    }

    public void setGuiBoids(GUISimulator g){
        this.guiBoids = g;
    }

    @Override
    public void next(){
        //this.getBoids().step();
        this.manager.next();
        this.getGuiBoids().reset();
        this.affiche();
    }
    @Override
    public void restart(){
        this.manager.restart();
        for(int i = 0; i < this.nombreGroupes; i++) {
            this.getBoids()[i].reInit();
            this.manager.addEvent(new BoidsEvent(this.boids[i], 1, manager, this.delai[i]));
        }
        this.getGuiBoids().reset();
        this.affiche();
    }
    public void affiche() {
        for(int j = 0; j < this.nombreGroupes; j++) {
            for(int i=0; i<this.boids[j].getNbBoids(); i++){
                this.getGuiBoids().addGraphicalElement(new Oval(this.getBoids()[j].getPx(i),
                            this.getBoids()[j].getPy(i),
                            Color.BLUE,
                            new Color((j * 250)%256, (j * 115)%256, (j * 100)%256),
                            r) 
                        );
            }
        }

    }
}
