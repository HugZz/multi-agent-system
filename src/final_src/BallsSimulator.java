import gui.*;
import java.awt.Color;

public class BallsSimulator implements gui.Simulable {
    private Balls balls;
    private GUISimulator guiBalls;
    private static int r=20 ;
    private EventManager manager;

    public BallsSimulator(int n, GUISimulator g){
        this.setBalls(new Balls(n));
        this.setGuiBalls(g);
        this.manager = new EventManager();
        this.manager.addEvent(new BallsEvent(this.balls, 1, manager));
    }
    public BallsSimulator(int n){
        this.setBalls(new Balls(n));
        this.manager = new EventManager();
        this.manager.addEvent(new BallsEvent(this.balls, 1, manager));
    }

    public Balls getBalls(){
        return this.balls;
    }
    public GUISimulator getGuiBalls(){
        return this.guiBalls;
    }

    public void setBalls(Balls b){
        this.balls = b;
    }
    public void setGuiBalls(GUISimulator g){
        this.guiBalls = g;
    }

    public void affiche()
    {
        for(int i=0; i<this.getBalls().getNbBalls(); i++){
            this.getGuiBalls().addGraphicalElement(new Oval(this.getBalls().getX(i),this.getBalls().getY(i),Color.BLUE,Color.RED,r));
        }
    }

    @Override
    public void next(){
        this.manager.next();
        this.getGuiBalls().reset();
        this.affiche();
        System.out.println(this.getBalls().toString());
    }

    @Override
    public void restart(){
        this.manager.restart();
        this.getBalls().reInit();
        this.manager.addEvent(new BallsEvent(this.balls, 1, manager));
        this.getGuiBalls().reset();
        this.affiche();
        System.out.println(this.getBalls().toString());
    }
}
