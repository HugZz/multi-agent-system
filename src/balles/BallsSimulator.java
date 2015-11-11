import gui.*;
import java.awt.Color;

public class BallsSimulator implements gui.Simulable {
    private Balls balls;
    private GUISimulator guiBalls;
    private static int r=20 ;

    public BallsSimulator(int n, GUISimulator g){
        this.setBalls(new Balls(n));
        this.setGuiBalls(g);
    }
    public BallsSimulator(int n){
        this.setBalls(new Balls(n));
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
        this.getBalls().execute();
        this.getGuiBalls().reset();
        this.affiche();
        System.out.println(this.getBalls().toString());
    }
    @Override
    public void restart(){
        this.getBalls().reInit();
        this.getGuiBalls().reset();
        this.affiche();
        System.out.println(this.getBalls().toString());
    }
}
