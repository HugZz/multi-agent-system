import java.awt.Point;
public class Balls {
    private Point [] b;
    private int nbBalls;
    private  int [] sensX;
    private  int [] sensY;
/*
 * Constructeur
 * @param n:  nbr de balles que l'on veut créer
 */
    public Balls(int n){ 
        this.setNbBalls(n);
        this.setB();
        this.setSensX(n);
        this.setSensY(n);
    }

    public void setSensY(int n){
        this.sensY = new int[n];
        for(int i=0;i<n;i++) this.sensY[i]=1;
    }
    public void setSensX(int n){
        this.sensX = new int[n];
        for(int i=0;i<n;i++) this.sensX[i]=1;
    }

    /*
     * Modifieur
     * @param n:  nbr de balles à créer
     */
    public void setNbBalls(int n){
        this.nbBalls = n;
    }

    /*
     *Modifieur : Crée un tableau de point qui représente les nbBalls balles
     */
    public void setB(){
        b = new Point[this.getNbBalls()];
        for(int i=0; i<this.getNbBalls(); i++){
            b[i] = new Point(100*i+50,20);
        }
    }

    /*
     *Accesseur à l'attribut nbBalls de Balls
     */
    public int getNbBalls(){
        return this.nbBalls;
    }

    /*
     *Accesseur à l'attribut b de Balls
     */
    public Point[] getB(){
        return b;
    }

    /*
     * @param dx : déplace toutes les balles de dx  : x <- x+dx
     * @param dy : déplace toutes les balles de dy  : y <- y+dy
     */
    public void translate (int dx, int dy){
        for(int i=0; i<this.getNbBalls(); i++){
            if( this.getX(i)>500 ) {sensX[i]=-1;} 
            if( this.getX(i)<0 ) {sensX[i]=1;} 
            if( this.getY(i)>500 ) {sensY[i]=-1;} 
            if( this.getY(i)<0 ) {sensY[i]=1;} 
            this.getB()[i].translate(sensX[i]*dx,sensY[i]*dy);
        }
    }

    /*
     *Déplace toutes les balles dans leur position de départ
     */
    public void reInit(){
        for(int i=0; i<this.getNbBalls() ; i++){
            this.getB()[i].move(100*i+50,20);
        }
    }

    public void execute() {
        this.translate(11,8);
    }
    
    /*
     * @param i: numero de la balle dont on desire connaitre le x
     */
    public int getX(int i) {
        return (this.getB()[i].getLocation()).x; 
    }
    /*
     * @param i: numero de la balle dont on desire connaitre le y
     */
    public int getY(int i) {
        return (this.getB()[i].getLocation()).y;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("");
        for (int i=0; i<this.getB().length; i++){
            sb.append("Ball " + i + ": " + getB()[i].toString() +" -- " );
        }
        return sb.toString();
   }
}
