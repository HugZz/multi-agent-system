import java.awt.Point;
/**
 * Balls est une classe qui contient un ensemble de nbBalls balles
 */
public class Balls {
	private Point [] balls;
	private int nbBalls;
	private  int [] sensX;
	private  int [] sensY;
	/**
	 * Délimiteur de l'écran
	 */
	protected static final int Xmax = 790;
	protected static final int Ymax = 490;
	protected static final int Xmin = 10;
	protected static final int Ymin = 10;

	/**
	 * Constructeur
	 * @param n:  nbr de balles que l'on veut créer
	 */
	public Balls(int n){ 
		this.setNbBalls(n);
		Point[] b = new Point[n];
		for(int i=0; i<n; i++){
			b[i] = new Point((int)(Math.random()*Xmax),(int)(Math.random()*Ymax));
		}
		this.setBalls(b);
		this.setSensX(n);
		this.setSensY(n);
	}

	private void setSensY(int n){
		this.sensY = new int[n];
		for(int i=0;i<n;i++) this.sensY[i]=1;
	}
	private void setSensX(int n){
		this.sensX = new int[n];
		for(int i=0;i<n;i++) this.sensX[i]=1;
	}

	/**
	 * Modifieur de nbBalls
	 * @param n  nombre de balles à créer
	 */
	public void setNbBalls(int n){
		if (n<1 || n>50) {
			throw new RuntimeException("Attention le nombre de balles doit être entre [1..500]");
		}
		else{
			this.nbBalls = n;
		}
	}

	/**
	 *Modifieur Crée un tableau de point qui représente les nbBalls balles
	 */
	public void setBalls(Point[] tabB){
		this.balls = tabB;
	}

	/**
	 * Accesseur à l'attribut nbBalls de Balls
	 * @return nbBalls
	 */
	public int getNbBalls(){
		return this.nbBalls;
	}

	/**
	 *Accesseur à l'attribut b de Balls
	 */
	public Point[] getBalls(){
		return this.balls;
	}
	/**
	 * Donne la position selon x de la balles i
	 * @param i numéro de la balle dont on désire connaitre le x
	 */
	public int getPx(int i) {
		if (i<0 || i>getNbBalls()) {
			throw new RuntimeException("Attention cette balles n'existe pas !");
		}
		return (this.getBalls()[i].getLocation()).x; 
	}
	/**
	 * Donne la position selon y de la balles i
	 * @param i: numero de la balle dont on desire connaitre le y
	 */
	public int getPy(int i) {
		if (i<0 || i>getNbBalls()) {
			throw new RuntimeException("Attention cette balles n'existe pas !");
		}
		return (this.getBalls()[i].getLocation()).y;
	}

	/**
	 * Déplace toutes les balles 
	 * @param dx : déplace toutes les balles de dx
	 * @param dy : déplace toutes les balles de dy
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

	/**
	 * Déplace toutes les balles dans une position aléatoire
	 */
	public void reInit(){
		for(int i=0; i<this.getNbBalls() ; i++){
			this.getBalls()[i].move((int)(Math.random()*Xmax),(int)(Math.random()*Ymax));
		}
	}
	/**
	 *	Actualiser la position des balles : les translate de 4 sur x et de 10 sur y
	 */
	public void actualiser() {
		this.translate(4,10);
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
