import java.awt.Point;

public class Boids extends Balls
{
	private Vect2D [] vitessesB;
	private int nbBoids;
	//Pourcentage  voulu de l'action de la cohesion sur les boid : 1=1%
	private int kC = 2;
	//Pourcentage  voulu de l'action de la l'alignement  sur les boid : 1=1%
	private int kA = 13;
	//Distance d'action de la force de séparation
	private int dS = 6;
	// Vitesse qu'un boid ne pourra pas depasser
	private int vitesseLimite = 20;
	// Distance Maximum autour d'un boid dans la quelle les autre boids l'influe directement 
	private int distanceVision = 150;

	/**
	 * Constructeur
	 * @param n:  nbr de boids que l'on veut créer
	 */
	public Boids(int n){ 
		super(n);
		v = new Vect2D[n];
		for(int i=0; i<n; i++){
			v[i] = new Vect2D();
		}
		this.setVitessesB(v);
	}
	public void setParameters(int kC, int kA, int dS, int vMax, int vision) {
		this.kC = kC;
		this.kA = kA;
		this.dS = dS;
		this.vitesseLimite = vMax;
		this.distanceVision = vision;
	}

	public void setVitessesB(Vect2D[] v){
		this.vitessesB = v;
	}
	public Vect2D[] getVitessesB(){
		return this.vitessesB;
	}

	/**
	 * Donne la composante sur X de la vitesse du boid i
	 * @param i: numero de la boid dont on desire connaitre le x
	 */
	public int getVx(int i) {
		return (this.getVitessesB[i].getVx()); 
	}
	/**
	 * Donne la composante sur Y de la vitesse du boid i
	 * @param i: numero du boid dont on desire connaitre le y
	 */
	public int getVy(int i) {
		return (this.getVitessesB[i].getVy());
	}
	@Override
    /**
     * Methode qui calcule la nouvelle position et vitesse de chaque boid en fonction des autres 
     */
    public void actualiser()
    {
       //System.out.println("---------------------"); 
        Vect2D vc = new Vect2D(0,0);
        Vect2D va = new Vect2D(0,0);
        Vect2D vs = new Vect2D(0,0);
		int n = this.getNbBalls();
        Point[] tmpB = new Point[n];
        Vect2D[] tmpV = new Vect2D[n];
        for( int i=0; i<n; i++){
            vc = cohesion(this.getBalls()[i],this.getVitessesB()[i]);
            va = alignement(this.getBalls()[i],this.getVitessesB()[i]);
            vs = separation(this.getBalls()[i],this.getVitessesB()[i]);

            tmpV[i].setV(	this.getVx(i) + (int)(vc.getX()) + (int)(va.getX()) + (int)(vs.getX()) ,
							this.getVy(i) + (int)(vc.getY()) + (int)(va.getY()) + (int)(vs.getY()) ); 
            limiteV(tmpV[i]);
           // System.out.println("V" + i + "=( " + this.getFlock()[i].getVx() + ";" + this.getFlock()[i].getVy() + " )\n" ); 

            tmpB[i].setLocation(	this.getPx(i) + this.getVx(i) ,
									this.getPy(i) + this.getVy(i) ); 
            limiteP(tmpB[i]);
        }
        for(int j=0; j<n; j++){
            this.getVitessesB[j].setV( tmpV[j].getX(),tmpV[j].getY() );
            this.getBalls[j].setLocation( tmpB[j].getX(),tmpB[j].getY() );
        }
    }
    /**
     * Remet tous les boids dans leur état de départ
     */
    public void reInit()
    {
		super.reInit();
        for(int i=0; i<this.getNbBoids() ; i++){
            this.getVitessesB[i].setV( (int)(Math.Random()*5),(int)(Math.Random()*5));
        }
    }

    public void limiteP(Point p) 
    {
        int X = Xmax;
        int Y = Ymax;
        int x = Xmin;
        int y = Ymin;
        if (p.getX() > X ){
            p.setLocation(x+5,p.getY());
        }else if(p.getX() < x) {
            p.setLocation(X-5,p.getY());
        }
        if (p.getY() > Y ){
            p.setLocation(p.getX(),y+5);
        }else if(p.getY() < y) {
            p.setLocation(p.getX(),Y-5);
        }
    }

    public void limiteV(Vect2D v) 
    {
        double nv = 1.0;
        nv = Math.sqrt( (double)(v.getX()*v.getX()) + (double)(v.getY()*v.getY()) ); 
        if ( (int)nv > vitesseLimite ) {
            v.vDiv((int)nv) ;
            v.vMult(vitesseLimite);
        }
    }
    
    /**
     *  Implementation de la régle de cohesion des boids : calcule de la force à appliquer à b
     *  @param b 
     *          boid dont ont calcul la force de cohesion
     *  @return c
     *          Vecteur de force à appliqué au boid b à l'étape suivante
     */
    public Vect2D cohesion(Boid b) 
    {
        //System.out.println("--------------------------------------------------------------------------------");
        Vect2D c = new Vect2D(0,0);
        int cmpt = 0;
        int d = 0;
        Boid bi = new Boid();
        for(int i=0; i<this.getNbBoids(); i++ ) {
            bi = this.getFlock()[i];
         //   System.out.println("bi =(" + bi.getPx() + ";" + bi.getPy() + ")" );
            d = distance( bi.getP(), b.getP() );
         //   System.out.println("boid: " + i + " d=" + d + " de b");
            if ( (d > 0) && (d < distanceVision) ) {
                c.vAdd( bi.getP() );
         //       System.out.println("----" + "c=(" + c.getX() + ";" + c.getY() + ") \n" );
                cmpt++;
            }
        }
        if (cmpt > 0) {
            c.vDiv(cmpt);  // c = c/cmpt
           //     System.out.println("c = c/cmpt ----" + "c=(" + c.getX() + ";" + c.getY() + ") \n" );
            c.vSub( b.getP() );
           //     System.out.println("c = c-b.pos ----" + "c=(" + c.getX() + ";" + c.getY() + ") \n" );
            c.vMult(kC);
           //     System.out.println("c = c*kc----" + "c=(" + c.getX() + ";" + c.getY() + ") \n" );
            c.vDiv(100);
        }
       // System.out.println("cFINAL=(" + c.getX() + ";" + c.getY() + ")" );
       // System.out.println("\n\n");
        return c;
    }
    /**
     *  Implementation de la régle d'alignement des boids : calcule de la force à appliquer à b
     *  @param b 
     *          boid dont ont calcule la force d'alignement 
     *  @return c
     *          Vecteur de force à appliqué au boid b à l'étape suivante
     */
    public Vect2D alignement (Boid b) 
    {
        Vect2D a = new Vect2D(0,0);
        Boid bi = new Boid();
        int cmpt = 0;
        int d = 0;
        for(int i=0; i<this.getNbBoids(); i++ ) {
            bi = this.getFlock()[i];
            d = distance( bi.getP(), b.getP() ); 
            if ( (d>0) && (d<distanceVision) ) {
                a.vAdd( bi.getV() );
                cmpt++;
            }
        }
        if (cmpt > 0) {
            a.vDiv(cmpt); 
            a.vSub(b.getV());
            a.vMult(kA); 
            a.vDiv(100); 
        }
        return a;
    }

    /**
     *  Implementation de la régle de séparation des boids : calcule de la force à appliquer à b
     *  @param b 
     *          boid dont ont calcule la force de séparation
     *  @return c
     *          Vecteur de force à appliqué au boid b à l'étape suivante
     */
    public Vect2D separation (Boid b) 
    {
        Vect2D s = new Vect2D(0,0);
        Vect2D tmp = new Vect2D(0,0);
        Boid bi = new Boid();
        int d = 0 ;
        for(int i=0; i<this.getNbBoids(); i++ ) {
            bi = this.getFlock()[i];
            d = distance( bi.getP(), b.getP() ); 
            if ( (d>0) && (d<dS) ) {
                tmp.vAdd(b.getP());
                tmp.vSub(bi.getP());
                s.vSub( tmp );
            }
        }
        return s;
    }
    /**
     *  Renvoie la distance entière entre a et b : distance AB 
     *  @param a
     *          point de depart
     *  @param b 
     *          point d'arrivé
     *  @return distance entre depart et arrivé
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
