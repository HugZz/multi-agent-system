import java.awt.Point;
public class Cellules {
    private boolean [][] grille;
    private boolean [][] tmpGrille;
    private int nbL;
    private int nbC;
/*
 * Constructeur
 * @param n:  nbr de lignes
 * @param m:  nbr de col
 */
    public Cellules(int n, int m){ 
        boolean[][] g = new boolean [n][];
        for(int i=0; i<n; i++){
            g[i] = new boolean[m];
        }
        this.setNbL(n);
        this.setNbC(m);
        this.setGrille(g);
        this.setTmpGrille(g);
        this.reInit();
    }

    /*
     * Adder
     * @param n:  nbr de lignes à créer
     */
    public void setNbL(int n){
        this.nbL = n;
    }
    /*
     * Adder
     * @param m:  nbr de col à créer
     */
    public void setNbC(int m){
        this.nbC = m;
    }
    /*
     * Adder : de TmpGrille
     * @param g : Tableau 2D de boolean
     */
    public void setGrille(boolean [][] g){
        this.grille = g;
    }
    /*
     * Adder: de grille
     * @param g : Tableau 2D de boolean
     */
    public void setTmpGrille(boolean[][] g){
        this.tmpGrille = g;
    }
    /*
     *Getter à l'attribut nbL de Cellules
     */
    public int getNbL(){
        return this.nbL;
    }
    /*
     *Getter à l'attribut nbC de Cellules
     */
    public int getNbC(){
        return this.nbC;
    }
    /*
     *Accesseur à l'attribut grille de Cellules
     */
    public boolean[][] getGrille(){
        return this.grille;
    }
    /*
     *Accesseur à l'attribut TmGgrille de Cellules
     */
    public boolean[][] getTmpGrille(){
        return this.tmpGrille;
    }
    public void setCellule (boolean[][] g, int i, int k, boolean e) {
        g[i][k] = e;
    }
    /*
     * @param i: numero de la ligne dont on desire connaitre l'état
     * @param k: numero de la collone dont on desire connaitre l'état
     */
    public boolean getCellule(boolean[][] g, int i,int k) {
        return (g[i][k]); 
    }

    /*
     * @param dx : déplace toutes les balles de dx  : x <- x+dx
     * @param dy : déplace toutes les balles de dy  : y <- y+dy
     */
    public void actualiser (){
    }

    /*
     * Remplie la grille aléatoirement avec des true et des false
     */
    public void reInit(){
        for(int i=0; i<this.getNbL(); i++){
            for(int k=0;k<this.getNbC(); k++){
                this.setCellule( this.getGrille(), i, k, (boolean)((Math.random())>=0.5) );
                this.setCellule( this.getTmpGrille(), i, k, this.getCellule(this.getGrille(),i,k) );
            }
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("");
        for (int i=0; i<this.getNbL(); i++){
            for (int k=0; k<this.getNbC(); k++){
                sb.append(this.getCellule(this.getGrille(), i, k) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
   }
}
