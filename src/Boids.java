import java.awt.Point;

/**
 * Classe fille de Balls
 * Elle permet de créer des boids, qui sont des balles ayant une position ET une vitesse.
 * @see Balls
 */
public class Boids extends Balls
{
	/**
	 * Vitesse de chaque boids
	 */
	private Vect2D [] vitessesB;
	//Pourcentage  voulu de l'action de la cohesion sur les boid : 1=1%
	private int kC = 3;
	//Pourcentage  voulu de l'action de la l'alignement  sur les boid : 1=1%
	private int kA = 13;
	//Distance d'action de la force de séparation
	private int dS = 10;
	// Vitesse qu'un boid ne pourra pas depasser
	private int vitesseLimite = 10;
	// Distance Maximum autour d'un boid dans la quelle les autre boids l'influe directement 
	private int distanceVision = 150;

	/**
	 * Constructeur appelant le super.constructeur
	 * @param n:  nbr de boids que l'on veut créer
	 */
	public Boids(int n){ 
		super(n);
		Vect2D[] v = new Vect2D[n];
		for(int i=0; i<n; i++){
			v[i] = new Vect2D((int)(Xmax/2),(int)(Ymax/2) );
		}
		this.setVitessesB(v);
	}
	/**
	 * Méthode permettant de paramétrer un troupeau de boids
	 */
	public void setParameters(int kC, int kA, int dS, int vMax, int vision) {
		this.kC = kC;
		this.kA = kA;
		this.dS = dS;
		this.vitesseLimite = vMax;
		this.distanceVision = vision;
	}

	/**
	 * Setter de VitessesB
	 */
	public void setVitessesB(Vect2D[] v){
		this.vitessesB = v;
	}
	/**
	 * Getter dee VitessesB
	 * @return vitessesB
	 */
	public Vect2D[] getVitessesB(){
		return this.vitessesB;
	}

	/**
	 * Donne la composante sur X de la vitesse du boid i
	 * @param i: numéro de la boid dont on désire connaitre le x
	 */
	public int getVx(int i) {
		if (i<0 || i>super.getNbBalls()) {
			throw new RuntimeException("Attention ce boids n'existe pas");
		}
		return (int)(this.getVitessesB()[i].getX()) ; 
	}
	/**
	 * Donne la composante sur Y de la vitesse du boid i
	 * @param i: numéro du boid dont on désire connaitre le y
	 */
	public int getVy(int i) {
		if (i<0 || i>super.getNbBalls()) {
			throw new RuntimeException("Attention ce boids n'existe pas");
		}
		return (int)(this.getVitessesB()[i].getY());
	}
	@Override
	/**
	 * Méthode qui calcule la nouvelle position et vitesse de chaque boid en fonction des autres boids dans leur zone de vision 
	 */
	public void actualiser()
	{
		Vect2D vc = new Vect2D(0,0);
		Vect2D va = new Vect2D(0,0);
		Vect2D vs = new Vect2D(0,0);
		int n = this.getNbBalls();
		Point[] tmpB = new Point[n];
		Vect2D[] tmpV = new Vect2D[n];
		for (int i=0; i<n ;i++ ) {
			tmpB[i] = new Point();
			tmpV[i] = new Vect2D();
		}
		for( int i=0; i<n; i++){
			vc = cohesion(this.getBalls()[i],this.getVitessesB()[i]);
			va = alignement(this.getBalls()[i],this.getVitessesB()[i]);
			vs = separation(this.getBalls()[i],this.getVitessesB()[i]);

			tmpV[i].setV(	this.getVx(i) + (int)(vc.getX()) + (int)(va.getX()) + (int)(vs.getX()) ,
					this.getVy(i) + (int)(vc.getY()) + (int)(va.getY()) + (int)(vs.getY()) ); 

			limiteV(tmpV[i]);

			tmpB[i].setLocation(	this.getPx(i) + this.getVx(i) ,
					this.getPy(i) + this.getVy(i) ); 

			limiteP(tmpB[i],tmpV[i]);
		}
		for(int j=0; j<n; j++){
			this.getVitessesB()[j].setV( (int)tmpV[j].getX(),(int)tmpV[j].getY() );
			this.getBalls()[j].setLocation( (int)tmpB[j].getX(),(int)tmpB[j].getY() );
		}
	}
	@Override
	/**
	 * Remet tous les boids dans un état de départ aléatoire
	 */
	public void reInit()
	{
		super.reInit();
		for(int i=0; i<this.getNbBalls() ; i++){
			this.getVitessesB()[i].setV( (int)(Math.random()*5),(int)(Math.random()*5) );
		}
	}
	/**
	 * Méthode qui va limiter la position possible au boids à la zone de l'écran : Les boids sont enfermé entre 4 mures
	 */
	public void limiteP(Point p, Vect2D v) 
	{
		int X = Xmax;
		int Y = Ymax;
		int x = Xmin;
		int y = Ymin;
		if (p.getX() > X ){
			if (v.getX() > 0){
				v.setV((int) (-1*v.getX()), (int)v.getY());
			}
		}else if(p.getX() < x) {
			if (v.getX() < 0){
				v.setV((int)( -1*v.getX()), (int)v.getY());
			}
		}
		if (p.getY() > Y ){
			if (v.getY() > 0){
				v.setV((int) v.getX(),(int)(-1*v.getY()) );
			}
		}else if(p.getY() < y) {
			if (v.getY() < 0){
				v.setV((int)(v.getX()),(int)(-1*v.getY()) );
			}
		}
	}
	/**
	 * Méthode qui limitera la |vitesse| pour ne pas avoir de réaction trop brusque des boids
	 */
	public void limiteV(Vect2D v) 
	{
		double nv = 1.0;
		nv = Math.sqrt( (double)(v.getX()*v.getX()) + (double)(v.getY()*v.getY()) ); 
		if ( (int)nv > vitesseLimite ) {
			v.vMult(vitesseLimite);
			v.vDiv((int)nv) ;
		}
	}

	/**
	 *  Implémentation de la règle de cohésion des boids : calcul de la force à appliquer à b
	 *  @param b 
	 *          boid dont ont calcul la force de cohésion
	 *  @return c Vecteur de force à appliquer au boid b à l'étape suivante
	 */
	public Vect2D cohesion(Point p, Vect2D v) 
	{
		//System.out.println("--------------------------------------------------------------------------------");
		Vect2D c = new Vect2D(0,0);
		int cmpt = 0;
		int d = 0;
		Point pi = new Point();
		int n = this.getNbBalls();
		for(int i=0; i<n; i++ ) {
			pi = this.getBalls()[i];
			//   System.out.println("bi =(" + bi.getPx() + ";" + bi.getPy() + ")" );
			d = distance( pi, p );
			//   System.out.println("boid: " + i + " d=" + d + " de b");
			if ( (d > 0) && (d < distanceVision) ) {
				c.vAdd( pi );
				//       System.out.println("----" + "c=(" + c.getX() + ";" + c.getY() + ") \n" );
				cmpt++;
			}
		}
		if (cmpt > 0) {
			c.vDiv(cmpt);  // c = c/cmpt
			//     System.out.println("c = c/cmpt ----" + "c=(" + c.getX() + ";" + c.getY() + ") \n" );
			c.vSub( p);
			//     System.out.println("c = c-b.pos ----" + "c=(" + c.getX() + ";" + c.getY() + ") \n" );
			c.vMult( kC );
			//     System.out.println("c = c*kc----" + "c=(" + c.getX() + ";" + c.getY() + ") \n" );
			c.vDiv(100);
		}
		// System.out.println("cFINAL=(" + c.getX() + ";" + c.getY() + ")" );
		// System.out.println("\n\n");
		return c;
	}
	/**
	 *  Implémentation de la règle d'alignement des boids : calcul de la force à appliquer à b
	 *  @param b Boid dont ont calcule la force d'alignement 
	 *  @return a Vecteur de force à appliquer au boid b à l'étape suivante
	 */
	public Vect2D alignement(Point p, Vect2D v) 
	{
		Vect2D a = new Vect2D(0,0);
		Point pi = new Point();
		int cmpt = 0;
		int d = 0;
		int n = super.getNbBalls();
		for(int i=0; i<n; i++ ){
			pi = this.getBalls()[i];
			d = distance( pi, p ); 
			if ( (d>0) && (d<distanceVision) ) {
				a.vAdd( this.getVitessesB()[i] );
				cmpt++;
			}
		}
		if (cmpt > 0) {
			a.vDiv(cmpt); 
			a.vSub( v );
			a.vMult( kA ); 
			a.vDiv( 100 ); 
		}
		return a;
	}

	/**
	 *  Implémentation de la règle de séparation des boids : calcul de la force à appliquer à b
	 *  @param b Boid dont ont calcule la force de séparation
	 *  @return c Vecteur de force à appliquer au boid b à l'étape suivante
	 */
	public Vect2D separation(Point p, Vect2D v)
	{
		Vect2D s = new Vect2D(0,0);
		Vect2D tmp = new Vect2D(0,0);
		Point pi = new Point();
		int d = 0 ;
		int n = this.getNbBalls();
		for(int i=0; i<n; i++ ) {
			pi = this.getBalls()[i];
			d = distance( pi, p ); 
			if ( (d>0) && (d<dS) ) {
				tmp.setV(0,0);
				tmp.vAdd( pi );
				tmp.vSub( p );
				s.vSub( tmp );
			}
		}
		return s;
	}
	/**
	 *  Renvoie la distance entière entre a et b : distance AB 
	 *  @param a 
	 *  @param b
	 *  @return distance |AB|
	 */
	public int distance( Point a , Point b ) {
		double d = 0;
		double xa = (double)a.getX();
		double xb = (double)b.getX();
		double ya = (double)a.getY();
		double yb = (double)b.getY();
		d = Math.sqrt( (xb-xa)*(xb-xa) + (yb-ya)*(yb-ya) );
		return (int)d;
	}


} 
