import java.awt.Point;
public class Balls {
	private Point [] balls;
	private int nbBalls;
	private  int [] sensX;
	private  int [] sensY;

	protected static final int Xmax = 1010;
	protected static final int Ymax = 510;
	protected static final int Xmin = 10;
	protected static final int Ymin = 10;

	/*
	 * Constructeur
	 * @param n:  nbr de balles que l'on veut créer
	 */
	public Balls(int n){ 
		Point[] b = new Point[n];
		for(int i=0; i<n; i++){
			b[i] = new Point();
		}
		this.setNbBalls(n);
		this.setBalls(b);
		this.reInit();
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
	public void setBalls(Point[] tabB){
		this.balls = tabB;
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
	public Point[] getBalls(){
		return this.balls;
	}
	/*
	 * @param i: numero de la balle dont on desire connaitre le x
	 */
	public int getPx(int i) {
		return (this.getBalls()[i].getLocation()).x; 
	}
	/*
	 * @param i: numero de la balle dont on desire connaitre le y
	 */
	public int getPy(int i) {
		return (this.getBalls()[i].getLocation()).y;
	}

	/*
	 * @param dx : déplace toutes les balles de dx  : x <- x+dx
	 * @param dy : déplace toutes les balles de dy  : y <- y+dy
	 */
	public void translate (int dx, int dy){
		for(int i=0; i<this.getNbBalls(); i++){
			if( this.getPx(i)>Xmax ) {sensX[i]=-1;} 
			if( this.getPx(i)<Xmin ) {sensX[i]=1;} 
			if( this.getPy(i)>Ymax ) {sensY[i]=-1;} 
			if( this.getPy(i)<Ymin ) {sensY[i]=1;} 
			this.getBalls()[i].translate(sensX[i]*dx,sensY[i]*dy);
		}
	}

	/*
	 *Déplace toutes les balles dans leur position de départ
	 */
	public void reInit(){
		for(int i=0; i<this.getNbBalls() ; i++){
			this.getBalls()[i].move((int)(Math.random()*Xmax),(int)(Math.random()*Ymax));
		}
	}

	public void actualiser() {
		this.translate(11,8);
	}

	@Override
	public String toString(){
		String sb = ""; 
		for (int i=0; i<this.getBalls().length; i++){
			sb += "Ball " + i + ": " + getBalls()[i].toString() +" -- " ;
		}
		return sb;
	}
}
