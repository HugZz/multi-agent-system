import java.awt.Point;
import java.util.Random;
/**
 * Cette classe permet de créer une grille contenant une valeur (int) correspondant à sont etat
 * Ce que contient chaque cellule peut être afficher textuelement
 * Mais peut aussi bien faire l'objet d'une simulation graphique grace à la classe CellulesSimulator
 * @see CellulesSimulator
 */
abstract public class Cellules {
    private int [][] grille;
    private int [][] tmpGrille;
    private int nbL;
    private int nbC;
    private int nbEtats;
/**
 * Constructeur de Cellules : créer une grille de n*m cellules, instancie les attribues nbL à n et nbC à m, et affecte à chaque cellule 1 état parmi ne 
 * @param n
 *      nbr de lignes
 * @param m
 *      nbr de colonnes
 * @param ne
 *      nbr de d'état que peut prendre une cellule    (1 < ne <=10)
 * @trows Attention 1 < ne <= 10
 */
    public Cellules(int n, int m, int ne){ 
        if (n<=0) {
            throw new RuntimeException("Attention le nombre de ligne doit être positif !!!");
        }
        if (m<=0) {
            throw new RuntimeException("Attention le nombre de colonnes doit être positif");
        }
        if (ne<=1 || ne>10 ) {
            throw new RuntimeException("Attention le nombre d'état peut être uniquement dans ]1:10] !!!");
        }
        int[][] g = new int [n][];
        for(int i=0; i<n; i++){
            g[i] = new int[m];
        }
        int[][] tmpG = new int [n][];
        for(int i=0; i<n; i++){
            tmpG[i] = new int[m];
        }
        this.setNbL(n);
        this.setNbC(m);
        this.setNbEtats(ne);
        this.setGrille(g);
        this.setTmpGrille(tmpG);
    }
    /**
     * Adder
     * @param n
     *      nbr de lignes à créer
     */
    public void setNbL(int n){
        this.nbL = n;
    }
    /**
     * Adder
     * @param m
     *      nbr de col à créer
     */
    public void setNbC(int m){
        this.nbC = m;
    }
    /**
     * Adder : de TmpGrille
     * @param g
     *      Tableau 2D de int
     */
    public void setGrille(int [][] g){
        this.grille = g;
    }
    /**
     * Adder: de grille
     * @param g
     *      Tableau 2D de boolean
     */
    public void setTmpGrille(int[][] tmpG){
        this.tmpGrille = tmpG;
    }
    /**
     *Getter à l'attribut nbL de Cellules
     */
    public int getNbL(){
        return this.nbL;
    }
    /**
     *Getter à l'attribut nbC de Cellules
     */
    public int getNbC(){
        return this.nbC;
    }
    /**
     *Accesseur à l'attribut grille de Cellules
     */
    public int[][] getGrille(){
        return this.grille;
    }
    /**
     *Accesseur à l'attribut TmGgrille de Cellules
     */
    public int[][] getTmpGrille(){
        return this.tmpGrille;
    }
    /**
     * Adder du nombre d'état possible par case
     * @param ne
     *      nombre d'état par possible par cellule 
     */
    public void setNbEtats(int ne){
        this.nbEtats = ne;
    }
    /**
     *Getter à l'attribut nbEtat de Cellules
     */
    public int getNbEtats(){
        return this.nbEtats;
    }
         /**
     * Changer l'état de la cellule (i,k) de la grille principale à e 
     * @param i
     *      Numero de la ligne dont on desire connaitre l'état
     * @param k
     *      Numero de la colonne dont on desire connaitre l'état
     * @param e
     *      Nouvelle état de la cellule (i,k)
     */
    public void setCellule (int i, int k, int e) {
        this.getGrille()[i][k] = e;
    }
    /**
     * Donne l'état de la cellule (i,k) de la grille principale
     * @param i
     *      Numero de la ligne dont on desire connaitre l'état
     * @param k
     *      Numero de la collone dont on desire connaitre l'état
     */
    public int getCellule(int i,int k) {
        return (this.getGrille()[i][k]); 
    }
    /**
     * Changer l'état de la cellule (i,k) de la grille temporaire à e
     * @param i
     *      Numero de la ligne dont on desire connaitre l'état
     * @param k
     *      Numero de la colonne dont on desire connaitre l'état
     * @param e
     *      Nouvelle état de la cellule (i,k)
     */
    public void setTmpCellule (int i, int k, int e) {
        this.getTmpGrille()[i][k] = e;
    }
    /**
     * Donne l'état de la cellule (i,k) de la grille temporaire 
     * @param i
     *      Numero de la ligne dont on desire connaitre l'état
     * @param k
     *      Numero de la colonne dont on desire connaitre l'état
     */
    public int getTmpCellule(int i,int k) {
        return (this.getTmpGrille()[i][k]); 
    }
   	abstract public void actualiser();
	
	abstract public void reInit();
	
        @Override
    public String toString(){
        String sb = "";
        for (int i=0; i<this.getNbL(); i++){
            for (int k=0; k<this.getNbC(); k++){
                sb += this.getCellule(i, k) + " ";
            }
            sb += "\n";
        }
        return sb;
   }
}
