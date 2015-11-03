import java.awt.Point;

public class Boids
{
    private Boid [] flock;
    private int nbBoids;
    //Pourcentage  voulu de l'action de la cohesion sur les boid : 1=1%
    private static final int kC = 1;
    //Pourcentage  voulu de l'action de la l'alignement  sur les boid : 1=1%
    private static final int kA = 13;
    //Pourcentage  voulu de l'action de la separation sur les boid : 1=1%
    private static final int kS = 25;
    // Vitesse qu'un boid ne pourra pas depasser
    private static final int vitesseLimite = 100;
    // Distance Maximum autour d'un boid dans la quelle les autre boids l'influe directement 
    private static final int distanceLimite = 1000;
    // Zone de l'écran dans lesquelles les boids doivent rester :
    private static final int Xmax = 510;
    private static final int Ymax = 510;
    private static final int Xmin = 10;
    private static final int Ymin = 10;

    /**
     * Constructeur
     * @param n:  nbr de boids que l'on veut créer
     */
    public Boids(int n){ 
        this.setNbBoids(n);
        this.setFlock();
    }
    /**
     * Modifieur de l'attribut nbBoids
     * @param n:  nbr de boids à créer
     */
    public void setNbBoids(int n){
        this.nbBoids = n;
    }
    /**
     *Modifieur : Crée un tableau de point qui représente les nbBoids boids
     */
    public void setFlock(){
        this.flock = new Boid[this.getNbBoids()];
        for(int i=0; i<this.getNbBoids(); i++){
            this.flock[i] = new Boid();
        }
    }
    public void setFlock(Boid [] f) {
        this.flock = f;
    }
    /**
     * Accesseur à l'attribut nbBoids de Boids
     * @return le nombre de boids du troupeau
     */
    public int getNbBoids(){
        return this.nbBoids;
    }
    /**
     * Accesseur à l'attribut flock de Boids
     * @return Le tableau de boids est retourné
     */
    public Boid [] getFlock(){
        return this.flock;
    }
    /**
     * Donne la composante sur X de la position du boid i
     * @param i: numero de la boid dont on desire connaitre le x
     */
    public int getPx(int i) {
        return (this.getFlock()[i].getPx()); 
    }
    /**
     * Donne la composante sur Y de la position du boid i
     * @param i: numero du boid dont on desire connaitre le y
     */
    public int getPy(int i) {
        return (this.getFlock()[i].getPy());
    }
    /**
     * Donne la composante sur X de la vitesse du boid i
     * @param i: numero de la boid dont on desire connaitre le x
     */
    public int getVx(int i) {
        return (this.getFlock()[i].getVx()); 
    }
    /**
     * Donne la composante sur Y de la vitesse du boid i
     * @param i: numero du boid dont on desire connaitre le y
     */
    public int getVy(int i) {
        return (this.getFlock()[i].getVy());
    }
    @Override
    public String toString(){
        String s = new String();
        for (int i=0; i<this.getFlock().length; i++){
            s += ("Boid " + i + ": " + "Pos("+ getPx(i) +";"+ getPy(i)+") Vit("+ getVx(i)+";"+getVy(i)+")" +"\n" );
        }
        s += "\n";
        return s;
   }

    /**
     * Methode qui calcule la nouvelle position et vitesse de chaque boid en fonction des autres 
     */
    public void step(){
        Vect2D vc = new Vect2D(0,0);
        Vect2D va = new Vect2D(0,0);
        Vect2D vs = new Vect2D(0,0);
        Boid [] tmpFlock = new Boid[this.getNbBoids()];
        for(int j=0; j<this.getNbBoids(); j++){
            tmpFlock[j] = new Boid();
        }
        for( int i=0; i<this.getNbBoids(); i++){
            vc = cohesion(this.getFlock()[i]);
          //  va = alignement(this.getFlock()[i]);
          //  vs = separation(this.getFlock()[i]);

            tmpFlock[i].setV( this.getFlock()[i].getVx() + (int)(vc.getX()) + (int)(va.getX()) + (int)(vs.getX()) ,
                                this.getFlock()[i].getVy() + (int)(vc.getY()) + (int)(va.getY()) + (int)(vs.getY()) ); 
          //  limiteV(tmpFlock[i]);

            tmpFlock[i].setP( this.getFlock()[i].getPx() + this.getFlock()[i].getVx() ,
                              this.getFlock()[i].getPy() + this.getFlock()[i].getVy() ); 
            limiteP(tmpFlock[i]);
        }
        for(int j=0; j<this.getNbBoids(); j++){
            this.getFlock()[j] = tmpFlock[j];
        }
    }
    /**
     * Remet tous les boids dans leur état de départ
     */
    public void reInit()
    {
        for(int i=0; i<this.getNbBoids() ; i++){
            this.getFlock()[i].setP((int)(Math.random()*500),(int)(Math.random()*500));
            this.getFlock()[i].setV(0,0);
        }
    }

    public void limiteP(Boid b) 
    {
        int X = Xmax;
        int Y = Ymax;
        int x = Xmin;
        int y = Ymin;
        if (b.getPx() > X ){
            b.getP().setLocation(x+5,b.getPy());
        }else if(b.getPx() < x) {
            b.getP().setLocation(X-5,b.getPy());
        }
        if (b.getPy() > Y ){
            b.getP().setLocation(b.getPx(),y+5);
        }else if(b.getPy() < y) {
            b.getP().setLocation(b.getPx(),Y-5);
        }
    }

    public void limiteV( Boid b) 
    {
        double nv = 1.0;
        nv = Math.sqrt( (double)(b.getVx()*b.getVx()) + (double)(b.getVy()*b.getVy()) ); 
        if ( (int)nv > vitesseLimite ) {
            b.setV( (b.getVx() / (int)nv) * vitesseLimite,
                    (b.getVy() / (int)nv) * vitesseLimite );
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
        Vect2D c = new Vect2D(0,0);
        int cmpt = 0;
        int d = 0;
        Boid bi = new Boid();
        for(int i=0; i<this.getNbBoids(); i++ ) {
            bi = this.getFlock()[i];
            System.out.println("bi =(" + bi.getPx() + ";" + bi.getPx() + ")" );
            d = distance( bi.getP(), b.getP() );
            if ( (d > 0) && (d < distanceLimite) ) {
                System.out.println("boid: " + i + " d="+d +"  c=(" + c.getX() + ";" + c.getY() + ")" );
                c.translate(bi.getPx() , bi.getPy() );
                System.out.println("----" + "c=(" + c.getX() + ";" + c.getY() + ") \n" );
                cmpt++;
            }
        }
        if (cmpt > 1) {
            c.move( (int)((c.getX()) / cmpt),
                    (int)((c.getY()) / cmpt) );

            c.move( ((int)(c.getX()) - b.getPx())/100,
                    ((int)(c.getY()) - b.getPy())/100 );
        }
        System.out.println("c=(" + c.getX() + ";" + c.getY() + ")" );
        System.out.println("\n\n");
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
        int cmpt = 0;
        for(Boid bi : this.getFlock() ) {
            if ( bi != b ) {
                if ( distance(bi.getP(),b.getP()) < distanceLimite ) {
                    a.translate(bi.getVx() , bi.getVy() );
                    cmpt++;
                }
            }
        }
        if (cmpt == 0) {
            cmpt = 1;
        }
        a.move( (int)(a.getX())/ cmpt,
                (int)(a.getY()) / cmpt );

        a.move( ((int)(a.getX()) - b.getVx())/8,
                ((int)(a.getY()) - b.getVy())/8 );
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
        for(Boid bi : this.getFlock() ) {
            if ( bi != b ) {
                if (distance(bi.getP(), b.getP() ) < kS) {
                    s.translate(b.getPx() - bi.getPx(),
                                b.getPy() - bi.getPy() );
                }
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
